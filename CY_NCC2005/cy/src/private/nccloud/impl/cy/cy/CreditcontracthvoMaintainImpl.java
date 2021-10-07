
package nccloud.impl.cy.cy;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.ibm.icu.text.SimpleDateFormat;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.ct.ar.IArApprove;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.IdCardUtil;
import nc.utils.commonplugin.NoteDataUtils;
import nc.utils.invoice.NumberToCN;
import nc.utils.smsutil.SmsUtils;
import nc.utils.transfer.CyContractConstractor;
import nc.vo.ct.ar.entity.AggCtArVO;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.classfilecy.ClasscyDetailVO;
import nc.vo.cy.classfilecy.ClasscyHVO;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nc.vo.cy.creditcontract.CreditcontractDetailVO;
import nc.vo.cy.creditcontract.CreditcontractHVO;
import nc.vo.cy.creditcontract.pojo.CContractQueryPojo;
import nc.vo.cy.creditcontract.pojo.CContractQuerydelmsgsPojo;
import nc.vo.cy.creditcontract.pojo.CContractRetPojo;
import nc.vo.cy.sms.SmsPushPojo;
import nc.vo.cy.studentfile.AggStudentHVO;
import nc.vo.cy.studentfile.StudentBindVO;
import nc.vo.cy.studentfile.StudentHVO;
import nc.vo.cy.studentfile.StudentParentVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.pflow.PfUserObject;
import nccloud.impl.pub.ace.AceAggbusiCreditcontractHVOPubServiceImpl;
import nccloud.itf.cy.cy.IClasscyhvoMaintain;
import nccloud.itf.cy.cy.ICreditcontracthvoMaintain;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;
import nccloud.itf.cy.cy.IStudenthvoMaintain;

public class CreditcontracthvoMaintainImpl extends AceAggbusiCreditcontractHVOPubServiceImpl
		implements ICreditcontracthvoMaintain {

	private BaseDAO baseDAO = CyCommonUtils.getInstance(BaseDAO.class);

	private SqlBuilder builder = CyCommonUtils.getInstance(SqlBuilder.class);

	@Override
	public void delete(AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills)
			throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggCreditcontractHVO[] insert(AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills)
			throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggCreditcontractHVO[] update(AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills)
			throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggCreditcontractHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggCreditcontractHVO[] save(AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills)
			throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggCreditcontractHVO[] unsave(AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills)
			throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggCreditcontractHVO[] approve(AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills)
			throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggCreditcontractHVO[] unapprove(AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills)
			throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	/**
	 * 家长端创建虚拟合同(学费收款合同)
	 * 
	 * @throws BusinessException
	 */
	public AggCreditcontractHVO[] createContract(StudentBindVO contractCreateVO) throws BusinessException {
		// 获取学生身份证
		String stuIdCardNo = contractCreateVO.getIdcard();
		// 学生档案接口
		AggStudentHVO[] aggStuVOS = CyCommonUtils.getInstance(IStudenthvoMaintain.class).queryStuBillByIdNO("idcard",
				new String[] { stuIdCardNo });

		AggCreditcontractHVO[] retAggArr = null;

		if (CyCommonUtils.isNotEmpty(aggStuVOS) && aggStuVOS.length == 1) {
			StudentHVO studentHVO = aggStuVOS[0].getParentVO();
			// 获取词源班级参照主键
			String pk_classcy = studentHVO.getDef1();
			// 班级档案接口
			IClasscyhvoMaintain iclasscyhvoMaintain = CyCommonUtils.getInstance(IClasscyhvoMaintain.class);
			// 班级整单
			List<AggClasscyHVO> billlist = iclasscyhvoMaintain.queryCyClassBillByCondition("pk_classcy",
					new String[] { pk_classcy });
			// 班级表体
			List<ClasscyDetailVO> listClassDVO = iclasscyhvoMaintain.getCyClassDetailFromClassMap(pk_classcy);

			if (CyCommonUtils.isNotEmpty(billlist)) {
				AggClasscyHVO aggvo = billlist.get(0);
				ClasscyHVO parent = aggvo.getParentVO();

				AggCreditcontractHVO standardContract = null;
				standardContract = this.standardContractDataCreator(parent, studentHVO, listClassDVO);

				if (CyCommonUtils.isNotEmpty(standardContract)) {
					String vnote = standardContract.getParentVO().getVnote();
					if (!(CyCommonUtils.isNotEmpty(vnote) && "1".equals(vnote))) {
						// 合同保存
						retAggArr = CyCommonUtils.getInstance(ICreditcontracthvoMaintain.class)
								.insert(new AggCreditcontractHVO[] { standardContract }, null);

						if (CyCommonUtils.isNotEmpty(retAggArr)) {
							// 生成NCC虚拟合同成功后，回写学生档案表头def3为 “是”
							String stuSqlUpdate = " update cy_student set def3='是' where pk_student='"
									+ studentHVO.getPk_student() + "'";
							int executeUpdate = baseDAO.executeUpdate(stuSqlUpdate);
						}
					}
				} else {
					throw new BusinessException(" 合同创建异常，请联系管理员！ ");
				}
			}
		}
		return retAggArr;
	}

	/**
	 * 标准算法的合同创建 基于合同年限生成收款计划
	 */
	private AggCreditcontractHVO standardContractDataCreator(ClasscyHVO clvo, StudentHVO studentHVO,
			List<ClasscyDetailVO> listClassDVO) throws BusinessException {
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
				throw new BusinessException("班级编码：" + clvo.getBill_no() + "对应的开班年级" + openClassYear + "格式异常!(2021级)");
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
		// 学费收款合同数据组装
		AggCreditcontractHVO aggCCHVO = CyContractConstractor.ccBillManager(htnx, clvo, studentHVO, listClassDVO);

		return aggCCHVO;

	}

	/**
	 * 家长端合同展示详情 查询接口
	 */
	@Override
	public CContractRetPojo ccontractQuery(CContractQueryPojo queryvo) throws BusinessException {
		String sidcard = queryvo.getIdcard();
		if (StringUtils.isBlank(sidcard)) {
			throw new BusinessException("学生身份证号不能为空");
		}
		String sname = queryvo.getSname();
//		sql.append(" select pk_contract,cyct.bill_no billcode,cyct.def9 afterreduceamount,to_char(cyct.amount) amount,stu.idcard,stu.sname, ");
//		sql.append(" psndoc.name psndocname,stup.parentphone,stup.def2 parentname,stup.def3 parentidcard,stup.def4 relation, ");
//		sql.append(" cysch.sname schoolname,cycl.clasname,cycl.paystand, ");
//		sql.append(" case when length(cyct.startdate)>=10 then substr(cyct.startdate,0,10)   ");
//		sql.append(" else cyct.startdate end startdate,  ");
//		sql.append(" case when length(cyct.enddate)>=10 then substr(cyct.enddate,0,10)   ");
//		sql.append(" else cyct.enddate end enddate from cy_contract cyct  ");
//		sql.append(" left join cy_student stu on cyct.def2=stu.pk_student  ");
//		sql.append(" left join ");
//		sql.append(" ( SELECT rownum stprn,temp.* FROM ( ");
//		sql.append(" SELECT * FROM cy_student_parent sp  ");
//		sql.append(" WHERE pk_student in (SELECT pk_student FROM cy_student WHERE idcard", sidcard);
//		sql.append(" )  ORDER BY def3 asc )  temp) stup on stup.pk_student=stu.pk_student ");
//		sql.append(" left join cy_classcy cycl on cyct.def1 = cycl.pk_classcy  ");
//		sql.append(" left join cy_schoolbasics cysch on cysch.pk_school=cyct.def5 ");
//		sql.append(" left join bd_psndoc psndoc on psndoc.pk_psndoc=cycl.teacher  ");
//		sql.append(" where nvl(cyct.dr,0)=0 and nvl(stu.dr,0)=0 and nvl(stup.dr,0)=0 ");
//		sql.append(" and nvl(cycl.dr,0)=0 and nvl(cysch.dr,0)=0 and nvl(psndoc.dr,0)=0 ");
//		sql.append(" and stup.stprn='1' ");

		builder.reset();
		builder.append(" select * from( ");
		builder.append(
				" select pk_contract,cyct.bill_no billcode,cyct.def9 afterreduceamount,to_char(cyct.amount) amount,stu.idcard,stu.sname, ");
		builder.append(
				" psndoc.name psndocname,stup.parentphone,stup.def2 parentname,stup.def3 parentidcard,stup.def4 relation, ");
		builder.append(" rownum datarownum,cyct.def6,cysch.sname schoolname,cycl.clasname,cycl.paystand, ");
		builder.append(" case when length(cyct.startdate)>=10 then substr(cyct.startdate,0,10)   ");
		builder.append(" else cyct.startdate end startdate,  ");
		builder.append(" case when length(cyct.enddate)>=10 then substr(cyct.enddate,0,10)   ");
		builder.append(" else cyct.enddate end enddate from cy_contract cyct  ");
		builder.append(" left join cy_student stu on cyct.def2=stu.pk_student  ");
		builder.append(" left join ");
		builder.append(" ( SELECT rownum stprn,temp.* FROM ( ");
		builder.append(" SELECT * FROM cy_student_parent sp  ");
		builder.append(" WHERE pk_student in (SELECT pk_student FROM cy_student WHERE idcard", sidcard);
		builder.append(" )  ORDER BY def3 asc )  temp) stup on stup.pk_student=stu.pk_student ");
		builder.append(" left join cy_classcy cycl on cyct.def1 = cycl.pk_classcy  ");
		builder.append(" left join cy_schoolbasics cysch on cysch.pk_school=cyct.def5 ");
		builder.append(" left join bd_psndoc psndoc on psndoc.pk_psndoc=cycl.teacher  ");
		builder.append(" where nvl(cyct.dr,0)=0 and nvl(stu.dr,0)=0 and nvl(stup.dr,0)=0 ");
		builder.append(" and nvl(cycl.dr,0)=0 and nvl(cysch.dr,0)=0 and nvl(psndoc.dr,0)=0 ");
		builder.append(" and stup.stprn='1' ");
		if (StringUtils.isNotBlank(sidcard)) {
			builder.append(" and stu.idcard ", sidcard);
		} else {
			throw new BusinessException("学生身份证号不能为空");
		}
		if (StringUtils.isNotBlank(sname)) {
			builder.append(" and stu.sname ", sname);
		}
		builder.append(" order by def6 desc) maindatac ");
		builder.append(" where rownum = 1 ");
		List<CContractRetPojo> listCC = (List<CContractRetPojo>) baseDAO.executeQuery(builder.toString(),
				new BeanListProcessor(CContractRetPojo.class));
		if (CollectionUtils.isNotEmpty(listCC)) {
			if (listCC.size() == 1) {
				CContractRetPojo retpojo = listCC.get(0);
				// 查询到金额和表体pk
				CContractQuerydelmsgsPojo contmsgs = this.ccontractQuerymsgs(sidcard);
				if (CyCommonUtils.isNotEmpty(contmsgs)) {
					String pk_detail = contmsgs.getPk_detail();
					String planamount = contmsgs.getPlanamount();
					retpojo.setCurrentpayamount(planamount);// 本次应缴金额
					retpojo.setPk_paymantrow(pk_detail);// 本次付款的在学费收款合同上面的行主键
					retpojo.setPaystatus("未付清");
					retpojo.setBeforepayamount(this.queryPayBeforeAmount(sidcard));// 前期已付款金额
				} else {
					retpojo.setPaystatus("已付清");
				}
				UFDouble paystand = new UFDouble(retpojo.getPaystand()).setScale(2, 5);
				BigDecimal numberOfMoney = new BigDecimal(paystand.toString());
				String paystandCN = NumberToCN.number2CNMontrayUnit(numberOfMoney);
				retpojo.setPaystandCN(paystandCN);// 中文金额
				retpojo.setKsf(paystand.div(new UFDouble(600)).setScale(2, 5).toString());
				retpojo.setOthertype(paystand.div(new UFDouble(10)).setScale(2, 5).toString());
				String idcard = retpojo.getIdcard();
				if(IdCardUtil.validate18Idcard(idcard)) {
					// 设置性别
					retpojo.setSsex(IdCardUtil.getSexFromidCardNo(idcard));
					// 设置生日
					retpojo.setSbirthday(IdCardUtil.getBirthDayFromidCardNo(idcard));
				} else {
					throw new BusinessException("身份证信息有误，请联系管理员查看！");
				}
				String stDate = retpojo.getStartdate();
				if (StringUtils.isNotBlank(stDate)) {
					UFDate ufDate = new UFDate(stDate);
					retpojo.setYear(ufDate.getYear() + "");
					retpojo.setMonth(ufDate.getMonth() + "");
					retpojo.setDay(ufDate.getDay() + "");
				}
				return retpojo;
			} else {
				throw new BusinessException("匹配到多个单据");
			}
		}

		return null;
	}

	@Override
	public boolean ccontractUpdate(CContractQueryPojo queryvo) throws BusinessException {
		// 查询校验数据
		String pk = queryvo.getPk_contract();
		if (MMValueCheck.isEmpty(pk)) {
			throw new BusinessException("主键不能为空");
		}
		builder.reset();
		builder.append("select count(1) from cy_contract where nvl(dr,0)=0 and pk_contract", pk);
		int result = (int) baseDAO.executeQuery(builder.toString(), new ColumnProcessor());
		if (result == 0) {
			throw new BusinessException("未匹配到对应的单据");
		} else if (result > 1) {
			throw new BusinessException("匹配到多个单据");
		}
		// 更新数据
		builder.reset();
		builder.append("update cy_contract set startdate", new UFDateTime().toString());
		builder.append(" ,def4='已生效'  where pk_contract", queryvo.getPk_contract());
		int executeUpdate = baseDAO.executeUpdate(builder.toString());

		if (executeUpdate > 0) {
			return true;
		}

		return false;
	}

	/**
	 * 查询单张学费收款合同中的前期累计已缴费金额 注意这里要筛选出学费收款合同版本号为最大的
	 * 
	 * @return
	 */
	private String queryPayBeforeAmount(String idcard) throws BusinessException {
		builder.reset();
		builder.append(" SELECT * FROM (");
		builder.append(" SELECT contract.pk_contract ");
		builder.append(" FROM cy_contract contract ");
		builder.append(" join cy_student stu ");
		builder.append(" on contract.student = stu.pk_student ");
		builder.append(" WHERE ");
		builder.append("stu.idcard", idcard);
		builder.append(" ORDER BY contract.def6 desc) ");
		builder.append(" WHERE rownum = 1 ");
		UFDouble amountTotal = new UFDouble();
		try {
			List<CreditcontractHVO> headVO = (List<CreditcontractHVO>) baseDAO.executeQuery(builder.toString(),
					new BeanListProcessor(CreditcontractHVO.class));
			if (CyCommonUtils.isNotEmpty(headVO)) {
				// 整单查询
				AbstractBill[] bills = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryBillsByCondition(
						AggCreditcontractHVO.class, "pk_contract", new String[] { headVO.get(0).getPk_contract() });
				if (CyCommonUtils.isNotEmpty(bills) && bills instanceof AbstractBill[]) {
					AbstractBill[] aggStuVO = (AbstractBill[]) bills;
					AggCreditcontractHVO aggContractHVO = (AggCreditcontractHVO) aggStuVO[0];
					CreditcontractDetailVO[] childVO = (CreditcontractDetailVO[]) aggContractHVO.getChildrenVO();
					for (int i = 0; i < childVO.length; i++) {
						if (CyCommonUtils.isNotEmpty(childVO[i].getDef30())) {
							amountTotal= amountTotal.add(childVO[i].getPlanamount());
						}
					}
				}
			}
		} catch (Exception e) {
			throw new BusinessException("查询已付款金额异常 ：" + e.getMessage());
		}
		amountTotal = amountTotal.setScale(2, UFDouble.ROUND_HALF_UP); 
		return amountTotal.toString();
	}

	@Override
	public CContractQuerydelmsgsPojo ccontractQuerymsgs(String idcard) throws BusinessException {
		NoteDataUtils.addLog(idcard, "payparam4sql", false, null);
		String sql = "select * from (select pk_contract from cy_contract where def2 = (select pk_student from cy_student where idcard = '"
				+ idcard + "' and dr = '0') and dr = '0' order by def6 desc ) where rownum = '1'";
		NoteDataUtils.addLog(sql, "payparam5sql", false, null);
		Object executeQuery = baseDAO.executeQuery(sql, new ColumnProcessor());
		if (CyCommonUtils.isNotEmpty(executeQuery)) {
			String pk_contract = (String) executeQuery;
			String sql2 = "select * from (select pk_detail,startdate,planamount from cy_creditcontract_Detail where pk_contract = '"
					+ pk_contract + "' and def30 = '~' and dr = '0' order by startdate) where rownum = '1'";
			List<Object[]> yhzhobjss = (List<Object[]>) baseDAO.executeQuery(sql2, new ArrayListProcessor());
			CContractQuerydelmsgsPojo pojo = new CContractQuerydelmsgsPojo();
			if (yhzhobjss != null && yhzhobjss.size() > 0) {
				Object[] objs = yhzhobjss.get(0);
				String[] drdcs = new String[3];
				NoteDataUtils.addLog(objs[0].toString(), "payparam7sql", false, null);
				pojo.setPk_detail(objs[0] == null ? "" : objs[0].toString());
				pojo.setStartdate(objs[1] == null ? "" : objs[1].toString());
				pojo.setPlanamount(objs[2] == null ? "" : objs[2].toString());
			}
			NoteDataUtils.addLog(pojo.toString(), "payparam8sql", false, null);
			return pojo;
		}
		return null;
	}

	/**
	 * 学费收款合同的变更保存，如果已经生成标准的收款合同则同步调用标准收款合同的变更接口
	 * 
	 * @throws BusinessException
	 */
	public void contractModifyWithAr(AggCreditcontractHVO aggVO) throws BusinessException {

		AggCtArVO aggCtArVO = new AggCtArVO();

		PfUserObject pfUserObject = new PfUserObject();

		// 调用标准合同变更接口，会辅助生成变更记录
		NCLocator.getInstance().lookup(IArApprove.class).modify(new AggCtArVO[] { aggCtArVO }, pfUserObject);

	}

	/**
	 * 根据学生信息查询学费收款合同的最新版本 def2 学生主键 def6 虚拟合同版本 returnParam 返回值Integer （版本号）
	 */
	@SuppressWarnings("unchecked")
	public Integer queryLatestVersion(String pk_student) throws BusinessException {
		builder.reset();
		builder.append(" SELECT * FROM cy_contract ");
		builder.append(" WHERE ");
		builder.append("def2", pk_student);// 学费收款合同表头中的def2，参照的学生档案
		builder.append(" ORDER BY def6 desc ");

		List<CreditcontractHVO> list = (List<CreditcontractHVO>) baseDAO.executeQuery(builder.toString(),
				new BeanListProcessor(CreditcontractHVO.class));
		if (CyCommonUtils.isNotEmpty(list)) {
			CreditcontractHVO headVO = list.get(0);
			String oldversion = headVO.getDef6();
			int old = Integer.parseInt(oldversion);
			return (old + 1);
		}
		return 1;
	}

	@Override
	public String queryFormalContract(String id) throws BusinessException {
		builder.reset();
		builder.append("select pk_fct_ar from fct_ar where dr = '0' and vdef4 = '" + id + "'");
		String pk_contract = (String) baseDAO.executeQuery(builder.toString(), new ColumnProcessor());
		if (CyCommonUtils.isEmpty(pk_contract)) {
			return null;
		}
		return pk_contract;
	}

	List<SmsPushPojo> smsPushList = new ArrayList<SmsPushPojo>();

	/**
	 * 查询应该催缴费的合同信息，学生信息等
	 */
	public void queryDunningContract(String days) throws BusinessException {
		smsPushList.clear();
		/**
		 * 先查询学费收款合同版本号最新的单据，并过滤掉已经付款的单据，筛选出距离配置日志最近的行数据
		 */
		builder.reset();
		builder.append(" SELECT t.pk_contract ");
		builder.append(" from (select row_number() over(partition by def2 order by to_number(def6) desc) rn,contract.* ");
		builder.append(" from cy_contract contract ");
		builder.append(" WHERE nvl(dr, 0) = 0 ) t ");
		builder.append(" where t.rn <= 1  ");

		List<CreditcontractHVO> list = (List<CreditcontractHVO>) baseDAO.executeQuery(builder.toString(),
				new BeanListProcessor(CreditcontractHVO.class));

		String[] pks = CyCommonUtils.getPrimaryKeys(list.toArray(new CreditcontractHVO[list.size()]));

		AbstractBill[] billArray = CyCommonUtils.getInstance(ICyCommonSqlUtil.class)
				.queryBillsByCondition(AggCreditcontractHVO.class, "pk_contract", pks);

		Arrays.asList(billArray).forEach(item -> {
			try {
				this.processContractVO(item,days);
			} catch (BusinessException e) {
				NoteDataUtils.addLog(e.getMessage(), "催费短信推送异常", false, null);
			}
		});
		NoteDataUtils.addLog("1", "催费短信推送ing...", false, null);
		smsPushList.forEach(item -> {
			try {
				SmsUtils.sendSms(new String[] { item.getStuName() },//new String[] { item.getStuName(), item.getDeadLine(), item.getCurrentAmount() }
						new String[] { item.getPhoneNo() }, "510317");// 催费模板
			} catch (BusinessException e) {
				NoteDataUtils.addLog(e.getMessage(), "催费短信推送异常", false, null);
			}
		});
	}

	/**
	 * 处理所有的合同数据
	 * @throws BusinessException
	 */
	private void processContractVO(AbstractBill aggContractVO,String days) throws BusinessException {
		if (CyCommonUtils.isNotEmpty(aggContractVO) && aggContractVO instanceof AggCreditcontractHVO) {
			AggCreditcontractHVO aggCreditcontractHVO = (AggCreditcontractHVO) aggContractVO;
			// 合同上的学生主键
			String pk_student = aggCreditcontractHVO.getParentVO().getDef2();

			AbstractBill[] billArray = CyCommonUtils.getInstance(ICyCommonSqlUtil.class)
					.queryBillsByCondition(AggStudentHVO.class, "pk_student", new String[] { pk_student });

			if (CyCommonUtils.isNotEmpty(billArray)) {
				AggStudentHVO stuAggVO = (AggStudentHVO) billArray[0];
				String stuName = stuAggVO.getParentVO().getSname();
				String phoneNo = "";
				
				if (CyCommonUtils.isNotEmpty(stuAggVO.getChildren(StudentParentVO.class))) {
					phoneNo = (String) stuAggVO.getChildren(StudentParentVO.class)[0].getAttributeValue("parentphone");
				}

				CreditcontractDetailVO[] childVOs = (CreditcontractDetailVO[]) aggCreditcontractHVO.getChildrenVO();
				List<CreditcontractDetailVO> afterFilterVO = Arrays.asList(childVOs).stream()
						.filter(x -> CyCommonUtils.isEmpty(x.getDef30()))
						.sorted(Comparator.comparing(CreditcontractDetailVO::getEnddate))
						.collect(Collectors.toList());

				CreditcontractDetailVO detailVO = afterFilterVO.get(0);
				UFDateTime endDate = detailVO.getEnddate();
				UFDateTime dayBefore = endDate.getDateTimeBefore(Integer.parseInt(days));
				
				if(new UFDateTime().after(dayBefore)) {
					SmsPushPojo pushPojo = new SmsPushPojo();
					pushPojo.setPhoneNo(phoneNo);
					pushPojo.setStuName(stuName);
//					pushPojo.setDeadLine(detailVO.getEnddate().toStdString());
//					pushPojo.setCurrentAmount(detailVO.getPlanamount().toString());
					smsPushList.add(pushPojo);
				}
			}
		}
	}

	@Override
	public Boolean updateFctplan(String pk_plan, String amount, String rate) throws BusinessException {
		try {
			StringBuffer sql = new StringBuffer("");
			sql.append("update fct_ar_plan set ");
			sql.append("planmoney = '" + amount + "',orgmoney = '" + amount + "',groupmoney = '" + amount
					+ "',glomoney = '" + amount + "',planrate = '" + rate + "' where pk_fct_ar_plan = '" + pk_plan
					+ "'");
			CyCommonUtils.getInstance(BaseDAO.class).executeUpdate(sql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean updateFctbasic(String pk_plan, String jmhje, String bhsje, String se) throws BusinessException {
		try {
			StringBuffer sql = new StringBuffer("");
			sql.append("update fct_ar_b set ");
			sql.append("norigtaxmny = '" + jmhje + "',ntaxmny = '" + jmhje + "',norigmny = '" + bhsje + "',nmny = '"
					+ bhsje + "',Ntax = '" + se + "' where pk_fct_ar_b = '" + pk_plan + "'");
			CyCommonUtils.getInstance(BaseDAO.class).executeUpdate(sql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean updateFctLjjm(String pk_fct_ar, String ljjm) throws BusinessException {
		try {
			StringBuffer sql = new StringBuffer("");
			sql.append("update fct_ar set ");
			sql.append("vdef6 = '" + ljjm + "' where pk_fct_ar = '" + pk_fct_ar + "'");
			CyCommonUtils.getInstance(BaseDAO.class).executeUpdate(sql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
