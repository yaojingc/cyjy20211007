
package nccloud.impl.cy.cy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import nc.api.cy.rest.entity.TableCountQueryVO;
import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.pf.IplatFormEntry;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.encode.Digest;
import nc.vo.cy.classfilecy.ClasscyHVO;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nc.vo.cy.miniprogram.MiniUserInfoPojo;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.cy.studentfile.AggStudentHVO;
import nc.vo.cy.studentfile.StudentBindVO;
import nc.vo.cy.studentfile.StudentHVO;
import nc.vo.cy.studentfile.StudentParentVO;
import nc.vo.cy.studentfile.pojo.StudentClassMsg;
import nc.vo.cy.studentfile.pojo.StudentQueryPOJO;
import nc.vo.cy.studentfile.pojo.StudentqVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.impl.pub.ace.AceAggbusiStudentHVOPubServiceImpl;
import nccloud.itf.cy.cy.IClasscyhvoMaintain;
import nccloud.itf.cy.cy.ICreditcontracthvoMaintain;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;
import nccloud.itf.cy.cy.IStudenthvoMaintain;

public class StudenthvoMaintainImpl extends AceAggbusiStudentHVOPubServiceImpl implements IStudenthvoMaintain {

	BaseDAO baseDao = CyCommonUtils.getInstance(BaseDAO.class);

	SqlBuilder builder = CyCommonUtils.getInstance(SqlBuilder.class);

	@Override
	public void delete(AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggStudentHVO[] insert(AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggStudentHVO[] update(AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggStudentHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggStudentHVO[] save(AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggStudentHVO[] unsave(AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggStudentHVO[] approve(AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills)
			throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggStudentHVO[] unapprove(AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills)
			throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	/**
	 * 家长端小程序 用户名(身份证号)+密码 登录
	 */
	public MiniUserInfoPojo miniLogin(MiniUserInfoPojo loginVO) throws BusinessException {
		try {
			// 得到用户的输入的用户名（身份证号）
			String stuIdcard = loginVO.getIdCardNo();
			// 得到用户在前台输入的明文密码编译MD5后的
			String md5After = Digest.md5AsHex(loginVO.getPassWord());
			// 根据身份证号整单查询学生档案
			AggStudentHVO[] aggStuVOS = queryStuBillByIdNO("idcard", new String[] { stuIdcard });
			// 判断是否存在此学生
			if (CyCommonUtils.isNotEmpty(aggStuVOS)) {
				String password = aggStuVOS[0].getParentVO().getDef20();
				// 密文对比
				if (CyCommonUtils.isNotEmpty(password) && password.equals(md5After)) {
					loginVO.setStatus(true);
					loginVO.setMsg("登录成功");
				} else {
					loginVO.setStatus(false);
					loginVO.setMsg("密码错误，请重新输入！");
				}
			} else {
				loginVO.setStatus(false);
				loginVO.setMsg("用户不存在，请先注册! ");
			}
			return loginVO;
		} catch (Exception e) {
			loginVO.setStatus(false);
			loginVO.setMsg("登录异常，请联系管理员 ！ " + e.getMessage());
		}
		return loginVO;
	}

	/**
	 * 家长端小程序 忘记密码/更改密码
	 */
	public MiniUserInfoPojo miniPwUpdate(MiniUserInfoPojo loginVO) throws BusinessException {
		try {
			// 绑定的手机号
			String phoneNumber = loginVO.getPhoneNumber();
			// 身份证号
			String stuIdcard = loginVO.getIdCardNo();
			/**
			 * 先根据 身份证号+手机号 查询学生档案存在且唯一
			 */
			builder.reset();
			builder.append(" SELECT t1.pk_student ");
			builder.append(" FROM cy_student t1 ");
			builder.append(" join cy_student_parent t2 ");
			builder.append(" on t1.pk_student = t2.pk_student ");
			builder.append(" WHERE ");
			builder.append("t1.idcard", stuIdcard);
			builder.append(" AND ");
			builder.append("t2.parentphone", phoneNumber);
			List<StudentHVO> list = (List<StudentHVO>) baseDao.executeQuery(builder.toString(),
					new BeanListProcessor(StudentHVO.class));

			if (CyCommonUtils.isNotEmpty(list) && list.size() == 1) {
				StudentHVO stuheadVO = list.get(0);
				// 得到用户在前台输入的明文密码编译MD5后的
				String newMd5PassWord = Digest.md5AsHex(loginVO.getPassWord());
				// 根据身份证号整单查询学生档案
				AggStudentHVO[] aggStuVOS = queryStuBillByIdNO("idcard", new String[] { stuIdcard });
				// 判断是否存在此学生
				if (CyCommonUtils.isNotEmpty(aggStuVOS)) {
					// 直接将密文更新到学生档案def20
					builder.reset();
					builder.append(" UPDATE cy_student SET ");
					builder.append("def20", newMd5PassWord);
					builder.append(" WHERE ");
					builder.append("pk_student", stuheadVO.getPk_student());
					int index = baseDao.executeUpdate(builder.toString());
					if (index > 0) {
						loginVO.setStatus(true);
						loginVO.setMsg("密码修改成功  ！ ");
					}
				}
			} else {
				throw new BusinessException("您输入的身份证号或手机号有误，请重新输入或重新注册！");
			}
		} catch (Exception e) {
			loginVO.setStatus(false);
			loginVO.setMsg("密码修改异常，请联系管理员 ！ ");
		}
		return loginVO;
	}

	/**
	 * 通过身份证号查询整单学生档案
	 * 
	 * @throws BusinessException
	 */
	public AggStudentHVO[] queryStuBillByIdNO(String idno, String[] idnovalues) throws BusinessException {
		// 整单查询
		AbstractBill[] bills = CyCommonUtils.getInstance(ICyCommonSqlUtil.class)
				.queryBillsByCondition(AggStudentHVO.class, idno, idnovalues);

		if (CyCommonUtils.isNotEmpty(bills) && bills instanceof AbstractBill[]) {
			AbstractBill[] aggStuVO = (AbstractBill[]) bills;
			AggStudentHVO aggStudentHVO = (AggStudentHVO) aggStuVO[0];

			return new AggStudentHVO[] { aggStudentHVO };
		}
		return null;
	}

	/**
	 * 家长端创建学生档案包装接口
	 */
	public AggCreditcontractHVO[] bindStuManager(StudentBindVO sbind) throws BusinessException {
		// 1. 匹配获取班级信息
		ClasscyHVO clvo = CyCommonUtils.getInstance(IClasscyhvoMaintain.class).queryClasscyAndCheck(sbind);
		// 获取开班年级
		String openclas = clvo.getDef12();
		// 开班年级匹配自定义档案
		String openClassYear = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryDefdocName(openclas, "年级");

		int opeanYear = 0;
		if (StringUtils.isBlank(openclas)) {
			throw new BusinessException("班级编码：" + clvo.getBill_no() + "对应的开班年级为空");
		} else {
			try {
				opeanYear = Integer.parseInt(openClassYear);
			} catch (Exception e) {
				throw new BusinessException("班级编码：" + clvo.getBill_no() + "对应的开班年级" + openClassYear + "格式异常!（2021级）");
			}
		}

		if (opeanYear == 0) {
			throw new BusinessException("班级编码：" + clvo.getBill_no() + "对应的开班年级" + openClassYear + "格式异常!（2021级）");
		}
		int currYear = new UFDateTime().getYear();
		int htnx = new Integer(3 - (currYear - opeanYear));

		if (htnx > 3 || htnx <= 0) {
			throw new BusinessException(
					"合同年限异常：" + htnx + "年!\n班级编码：" + clvo.getBill_no() + "对应的开班年级" + openClassYear + "格式异常!（2021级）");
		}
		// 2. 匹配学生信息，有则取，无则新增后取
		StudentHVO stu = this.studentDataManager(sbind, clvo);

		if (CyCommonUtils.isNotEmpty(stu)) {
			// 学生档案创建成功后，开始创建学费收款合同
			AggCreditcontractHVO[] retAggArr = CyCommonUtils.getInstance(ICreditcontracthvoMaintain.class)
					.createContract(sbind);
			return retAggArr;
		}
		return null;
	}

	/**
	 * 家长端创建学生档案子方法 保存即审批，会主动触发BP中的功能自动推送到客户档案中
	 */
	private StudentHVO studentDataManager(StudentBindVO sbind, ClasscyHVO clvo) throws BusinessException {
		StudentHVO stu = null;
		String stuSql = " SELECT * FROM cy_student where nvl(dr,0)=0 and idcard='" + sbind.getIdcard()
				+ "' order by ts desc";
		try {
			List<StudentHVO> listStu = (List<StudentHVO>) baseDao.executeQuery(stuSql,
					new BeanListProcessor(StudentHVO.class));
			if (listStu.isEmpty()) {
				IStudenthvoMaintain istuService = NCLocator.getInstance().lookup(IStudenthvoMaintain.class);
				AggStudentHVO agghvo = new AggStudentHVO();
				StudentHVO newStu = new StudentHVO();
				newStu.setBillstatus(-1);
				newStu.setApprovestatus(-1);
				newStu.setPk_group(AppletsParams.Pk_Group);
				newStu.setPk_org(AppletsParams.Pk_Org);
				newStu.setCygrade(clvo.getPk_classcy());
				newStu.setSchoolname(clvo.getDef1());
				newStu.setDef1(clvo.getPk_classcy());
				newStu.setDef2(clvo.getDef1());
				newStu.setDef3("否");
				newStu.setDef10(clvo.getDef10());// 大区
				newStu.setDef11(clvo.getDef11());// 省份
				newStu.setDef14(clvo.getDef12());// 开班年级
				newStu.setDef20(Digest.md5AsHex(sbind.getPassword()));
				newStu.setRegion(clvo.getRegion());
				newStu.setSname(sbind.getSname());
				newStu.setIdcard(sbind.getIdcard());
				newStu.setEmail(sbind.getMail());
				newStu.setIsuseing(new UFBoolean(true));
				newStu.setDbilldate(new UFDate());
				// 第一次绑定新增一行家长联系信息
				StudentParentVO parentvo = new StudentParentVO();
				parentvo.setRowno("1");
				parentvo.setParentphone(sbind.getPhoneno());
				parentvo.setDef1(sbind.getOpenid());
				parentvo.setDef2(sbind.getParentname());
				parentvo.setDef3(sbind.getParentid());
				parentvo.setDef4(sbind.getRelation());// 与学生的关系
				agghvo.setParentVO(newStu);
				agghvo.setChildren(StudentParentVO.class, new StudentParentVO[] { parentvo });
				IplatFormEntry iplatFormEntry = NCLocator.getInstance().lookup(IplatFormEntry.class);
				AggStudentHVO[] retVO = (AggStudentHVO[]) iplatFormEntry.processAction("SAVEBASE", "XSDA", null, agghvo,
						null, new HashMap());

				// 顺势调用学生档案审批
				IBillQueryService service = NCLocator.getInstance().lookup(IBillQueryService.class);
				AggStudentHVO aggvo = service.querySingleBillByPk(AggStudentHVO.class,
						retVO[0].getParentVO().getPk_student());
				AggStudentHVO[] res1 = (AggStudentHVO[]) iplatFormEntry.processAction("SAVE", "XSDA", null, aggvo, null,
						new HashMap());
				Object res2 = iplatFormEntry.processAction("APPROVE", "XSDA", null, res1[0], null, new HashMap());
				stu = retVO[0].getParentVO();
			} else {
				throw new BusinessException("此身份证号在学生档案中已存在 ");
			}
		} catch (Exception e) {
			throw new BusinessException("处理学生档案时异常：" + e.getMessage());
		}
		if (stu == null) {
			throw new BusinessException("根据学生姓名：" + sbind.getSname() + "及学生身份证号：" + sbind.getIdcard() + "未匹配到对应的学生信息");
		}
		return stu;
	}

	/**
	 * 单纯通过学生身份证号查询学生档案上的学生信息
	 */
	public StudentQueryPOJO queryStudentByIdcard2(String idcard) throws BusinessException {
//		builder.reset();
//		builder.append(" SELECT stu.sname, ");
//		builder.append(" stu.idcard, ");
//		builder.append(" stu.email, ");
//		builder.append(" cla.bill_no cygrade, ");
//		builder.append(" stuparent.def2 parentname, ");
//		builder.append(" stuparent.def3 parentid ");
//		builder.append(" FROM cy_student stu ");
//		builder.append(" LEFT JOIN cy_classcy cla ");
//		builder.append(" on stu.cygrade = cla.pk_classcy ");
//		builder.append(" LEFT JOIN cy_student_parent stuparent ");
//		builder.append(" on stu.pk_student = stuparent.pk_student ");
//		builder.append(" WHERE ");
//		builder.append("idcard", idcard);

		String sql = " SELECT stu.sname,  stu.idcard,  stu.email,  cla.bill_no cygrade,  stuparent.def2 parentname,  stuparent.def3 parentid , stuparent.parentphone parentphone  ,sch.sname schoolname  "
				+ "  ,(select name from bd_region where  pk_region = sch.Province) province  "
				+ "  ,(select name from bd_region where  pk_region = sch.City) city  " + "  from cy_student stu  "
				+ "  left join cy_classcy cla  on stu.cygrade = cla.pk_classcy  "
				+ "  left join cy_student_parent stuparent  on stu.pk_student = stuparent.pk_student  "
				+ "   left join cy_schoolBasics sch on  sch.pk_school = stu.def2  " + "  WHERE stu.idcard='" + idcard
				+ "' ";

		List<StudentQueryPOJO> list = (List<StudentQueryPOJO>) baseDao.executeQuery(sql,
				new BeanListProcessor(StudentQueryPOJO.class));
		if (CyCommonUtils.isNotEmpty(list)) {
			if (list.size() == 1) {
				StudentQueryPOJO result = list.get(0);
				return result;
			} else {
				for (StudentQueryPOJO stuQueryPOJO : list) {
					String pname = stuQueryPOJO.getParentname();
					String pid = stuQueryPOJO.getParentid();
					if (CyCommonUtils.isNotEmpty(pname) || CyCommonUtils.isNotEmpty(pid)) {
						return stuQueryPOJO;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 学生档案查询针对家长端 根据学生身份证号 + 家长手机号 查询
	 */
	public String queryStudentByIdcard(String idcard, String parentphone, String openid) throws BusinessException {
		try {
			IUAPQueryBS queryBS = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			StringBuffer sql = new StringBuffer("");
			sql.append("select pk_student from cy_student where idcard = '" + idcard + "' and dr = '0'");
			Object result = queryBS.executeQuery(sql.toString(), new ColumnProcessor());
			if (CyCommonUtils.isNotEmpty(result)) {
				// 查询手机号和身份证号是否绑定
				StringBuffer sql2 = new StringBuffer("");
				sql2.append("select pk_student from cy_student_parent where parentphone = '" + parentphone
						+ "' and pk_student = '" + result + "' ");
				Object result2 = queryBS.executeQuery(sql2.toString(), new ColumnProcessor());
				if (CyCommonUtils.isNotEmpty(result2) && result2.toString().equals(result.toString())) {
					/**
					 * 表示学生存在并且家长手机号也绑定了，需要将openid写到家长联系方式页签
					 */
					int i = this.updateOpenidToStu(idcard, parentphone, openid);
					return "1";
				} else {
					return "0";
				}
			} else {
				return "-1";
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			return "2";
		}
	}

	/**
	 * 更新openid到学生档案中的家长联系方式页签
	 * 
	 * @param openid 将openid存放在家长页签的def1字段
	 */
	private int updateOpenidToStu(String idcard, String parentphone, String openid) {
		try {
			int i = baseDao.executeUpdate(
					" UPDATE cy_student_parent SET def1='" + openid + "' WHERE parentphone = '" + parentphone + "'");
			return i;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 家长联系方式子集新增
	 */
	public String addToParentTab(String idcard, String parentphone, String openid) throws BusinessException {
		IUAPQueryBS queryBS = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		StringBuffer sql = new StringBuffer("");
		sql.append("select pk_student from cy_student where idcard = '" + idcard + "' and dr = '0'");
		Object result = queryBS.executeQuery(sql.toString(), new ColumnProcessor());
		if (CyCommonUtils.isNotEmpty(result)) {
			StudentParentVO parentvo = new StudentParentVO();
			parentvo.setPk_student(result.toString());
			parentvo.setParentphone(parentphone);
			parentvo.setDef1(openid);
			String insert_pk = baseDao.insertVO(parentvo);
			return insert_pk;
		}
		return null;
	}

	public String updateEmailByID(StudentQueryPOJO queryvo) throws BusinessException {
		String idcard = queryvo.getIdcard();
		String email = queryvo.getEmail();
		String parentphone = queryvo.getParentphone();
		if (CyCommonUtils.isNotEmpty(idcard) && CyCommonUtils.isNotEmpty(email)
				&& CyCommonUtils.isNotEmpty(parentphone)) {
			try {

				int i = baseDao
						.executeUpdate(" UPDATE cy_student SET email='" + email + "' WHERE idcard = '" + idcard + "'");
				if (i > 0) {
//					return "操作成功";
				}
				// 新增修改手机号
				int j = baseDao.executeUpdate("  update cy_student_parent set parentphone ='" + parentphone
						+ "' where pk_student=( select  pk_student  from cy_student where idcard='" + idcard + "' ) ");
				if (j > 0) {
					return "操作成功";
				}
			} catch (DAOException e) {
				return "操作异常";
			}
		}
		return "用户身份信息异常";
	}

	/**
	 * 学生信息查询
	 * 
	 * @param pk
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, String> studentDataQuery(Set<String> pks) {
		Map<String, String> retMap = new HashMap<String, String>();
		if (MMValueCheck.isEmpty(pks)) {
			return retMap;
		}
		IUAPQueryBS queryBS = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		try {
			builder.reset();
			builder.append(" SELECT distinct pk_student pk,sname name FROM cy_student WHERE nvl(dr,0)=0 ");
			builder.append(" and pk_student ", pks.toArray(new String[0]));
			List<Map<String, String>> queryListMap = (List<Map<String, String>>) queryBS
					.executeQuery(builder.toString(), new MapListProcessor());
			if (MMValueCheck.isNotEmpty(queryListMap)) {
				for (Map<String, String> map : queryListMap) {
					retMap.put(map.get("pk"), map.get("name"));
				}
			}
		} catch (Exception e) {
			return retMap;
		}
		return retMap;

	}

	/**
	 * 根据条件分页查询数据
	 * 
	 * @param restWorkplan 查询VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	@SuppressWarnings("unchecked")
	public ResponseList<T> queryForApp(StudentQueryPOJO stuquery) throws BusinessException {
		// 1、查询条件拼装
		SqlBuilder condition = new SqlBuilder();
		SqlBuilder sqlb = new SqlBuilder();
		condition.append("select rownum as rn,pk_student,sname,bill_no,idcard from cy_student where dr = '0'  ");
		if (CyCommonUtils.isNotEmpty(stuquery.getSname())) {
			condition.append(" and sname like '%" + stuquery.getSname() + "%'");
			sqlb.append(" and sname like '%" + stuquery.getSname() + "%'");
		}
		if (CyCommonUtils.isNotEmpty(stuquery.getIdcard())) {
			condition.append("and idcard", stuquery.getIdcard());
			sqlb.append("and idcard", stuquery.getIdcard());
		}

		int pageindex = stuquery.getIndex();// 当前页
		int pagesize = stuquery.getSize();// 每页大小
		String sort = stuquery.getSort();// 排序条件
		// 拼装分页查询sql
		String sqlList = null;
		int bpage = (pageindex - 1) * (pagesize) + 1;// 开始
		int epage = pageindex * pagesize;// 结束
		String sqlwhere = " select pk_student,sname,bill_no,idcard from ( " + condition.toString() + " and rownum <="
				+ epage;
		if (!StringUtils.isBlank(sort)) {
			sqlList = sqlwhere + " order by " + sort + ") where 1=1 and rn >= " + bpage;
		} else {
			sqlList = sqlwhere + " order by ts desc) where 1 =1 and rn >= " + bpage;
		}
		ResponseList<T> resp = new ResponseList<T>();
		List<T> listvos = new ArrayList<T>();
		// 2、进行分页查询
		listvos = (List<T>) baseDao.executeQuery(sqlList.toString(), new BeanListProcessor(StudentqVO.class));
		resp.setContent(listvos);
		TableCountQueryVO vo = new TableCountQueryVO("cy_student", sqlb.toString());
		PagingPOJO r = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryTablePageInfo(vo);
		Integer total = 0;
		if (CyCommonUtils.isNotEmpty(r)) {
			total = r.getTotal();
		}
		resp.setTotal(total);
		int totalPage = CyCommonUtils.getTotalPage(pagesize, total);
		resp.setContent(listvos);
		resp.setTotal(total);
		resp.setTotalPages(totalPage);
		resp.setCurrentPage(pageindex);
		resp.setPageSize(pagesize);
		return resp;

	}

	/**
	 * 
	 * @Override 根据学生编码查询班级跟学生主键
	 */
	@SuppressWarnings("unchecked")
	public StudentClassMsg queryMsgByCode(String code) throws BusinessException {
		builder.reset();
		builder.append(" select pk_student , def1 cyclass,def4 xzclass");
		builder.append(" from cy_student ");
		builder.append(" where ");
		builder.append("bill_no", code);
		builder.append(" and dr = '0' ");

		List<StudentClassMsg> list = (List<StudentClassMsg>) baseDao.executeQuery(builder.toString(),
				new BeanListProcessor(StudentClassMsg.class));
		if (CyCommonUtils.isNotEmpty(list)) {
			return list.get(0);
		} else {
			return null;
		}
	}
}
