
package nccloud.impl.cy.cy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.api.cy.rest.entity.crm.schoolform.RestSchoolFindSave;
import nc.api.cy.rest.entity.crm.teacherform.RestTeacherSave;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.generator.IdGenerator;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonConstant.EnumConstant;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.NoteDataUtils;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.classfilecy.ClasscyHVO;
import nc.vo.cy.pojo.GlbdefVO;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.cy.schoolarchives.SchoolBasicsHVO;
import nc.vo.cy.schoolform.AggSchoolformHVO;
import nc.vo.cy.teacherform.AggTeacherformHVO;
import nc.vo.cy.teacherform.TeacherformHVO;
import nc.vo.cy.teacherform.pojo.BaseRef;
import nc.vo.cy.teacherform.pojo.CContent;
import nc.vo.cy.teacherform.pojo.CContentRet;
import nc.vo.cy.teacherform.pojo.DataContent;
import nc.vo.cy.teacherform.pojo.LContent;
import nc.vo.cy.teacherform.pojo.LContentRet;
import nc.vo.cy.teacherform.pojo.QueryParmPOJO;
import nc.vo.cy.teacherform.pojo.SchoolRef;
import nc.vo.cy.teacherform.pojo.TeacherAssign;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.lang.UFLiteralDate;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.UserVO;
import nccloud.cy.cy.studenthvo.cy.util.CommonUtil;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.impl.dao.SchoolBaseDao;
import nccloud.impl.pub.ace.AceAggbusiTeacherformHVOPubServiceImpl;
import nccloud.itf.cy.cy.ITeacherformhvoMaintain;

public class TeacherformhvoMaintainImpl extends AceAggbusiTeacherformHVOPubServiceImpl
		implements ITeacherformhvoMaintain {

	@Override
	public void delete(AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggTeacherformHVO[] insert(AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills)
			throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggTeacherformHVO[] update(AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills)
			throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggTeacherformHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggTeacherformHVO[] save(AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills)
			throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggTeacherformHVO[] unsave(AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills)
			throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggTeacherformHVO[] approve(AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills)
			throws Exception {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggTeacherformHVO[] unapprove(AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills)
			throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}
	
	/**
	 *  节点查询未筛选dr,故物理删除
	 */
	@Override
	public void glbdefDelete(String sql) throws BusinessException {
		BaseDAO dao = new BaseDAO();
		int retcount = dao.executeUpdate(sql);
	}
	
	@Override
	public void glbdefUpdate(GlbdefVO[] glbdefVO,String[] fieldNames) throws BusinessException {
		BaseDAO dao = new BaseDAO();
		int retcount = dao.updateVOArray(glbdefVO, fieldNames);
	}
	

	@Override
	public String glbdefInsert(GlbdefVO glbdefVO) throws BusinessException {
		BaseDAO dao = new BaseDAO();
		String retGLBdef = dao.insertVO(glbdefVO);
		return retGLBdef;
	}

	@Override
	public DataContent<LContentRet> queryTeacherList(QueryParmPOJO querypojo)
			throws BusinessException, IllegalArgumentException, IllegalAccessException {
		DataContent<LContentRet> listvo = new DataContent<LContentRet>();
		List<LContent> teachList = queryData(querypojo);
		if (CollectionUtils.isEmpty(teachList)) {
//			throw new BusinessException("未查询到数据");
			return null;
		}

		Set<String> schoolSet = new HashSet<String>();
		Set<String> classSet = new HashSet<String>();
		for (LContent lContent : teachList) {
			schoolSet.add(lContent.getPkschool());
			classSet.add(lContent.getPkclasscy());
		}
		// 查询获取 学校、班级数据
		Map<String, Map<String, String>> schoolMap = querySchoolData(schoolSet);
		Map<String, Map<String, String>> classMap = queryClassData(classSet);

		String queryTotal = queryCountAll(querypojo);
		int total = Integer.parseInt(queryTotal);
		int pagesize = querypojo.getSize().intValue();
		listvo.setTotal(total);
		UFDouble totalPages;
		int split = new UFDouble(total).div(new UFDouble(pagesize)).intValue();
		UFDouble mod = new UFDouble(total).mod(new UFDouble(pagesize));
		if (mod.sub(new UFDouble(0)).doubleValue() > 0) {
			totalPages = new UFDouble(split).add(new UFDouble(1));
		} else {
			totalPages = new UFDouble(split);
		}
		listvo.setTotalPages(totalPages.intValue());
		listvo.setPageSize(pagesize);
		listvo.setCurrentPage(querypojo.getIndex().intValue());
		
		List<LContentRet> listret = new ArrayList<LContentRet>();
		for (LContent ict : teachList) {
			Class object = ict.getClass();
			// 获取对象属性
			Field[] fields = object.getDeclaredFields();
			for (Field field : fields) {
				String fieldname = field.getName();
				field.setAccessible(true); // 私有属性必须设置访问权限
				String value = field.get(ict) == null ? "" : field.get(ict).toString();
				Map<String, String> smap = schoolMap.get(value);
				Map<String, String> cmap = classMap.get(value);
				if (fieldname.equals("pkschool")) {
					if(null != smap) {
						SchoolRef scref = new SchoolRef();
						scref.setCode(smap.get("bill_no"));
						scref.setLabel(smap.get("sname"));
						scref.setSchoolregion(smap.get("schoolregion"));
						scref.setSchooladdress(smap.get("schooladdress"));
						scref.setValue(value);
						ict.setAttributeValue("school", scref);
					}
					ict.setAttributeValue(fieldname, null);
				} else if (fieldname.equals("pkclasscy")) {
					if(null != cmap) {
						BaseRef ref = new BaseRef();
						ref.setCode(cmap.get("bill_no"));
						ref.setLabel(cmap.get("clasname"));
						ref.setValue(value);
						ict.setAttributeValue("classcy", ref);
					}
					ict.setAttributeValue(fieldname, null);
				} else if (StringUtils.isNotBlank(value) && "dbilldate".equals(fieldname)) {
					ict.setAttributeValue(fieldname, value.substring(0, 10));
				} else if (StringUtils.isNotBlank(value)) {
					ict.setAttributeValue(fieldname, value);
				}
				
			}
			LContentRet ret = new LContentRet();
			BeanUtils.copyProperties(ict, ret);
			
			listret.add(ret);
		}
		listvo.setContent(listret);
		return listvo;
	}

	// 查询匹配班级信息
	public static Map<String, Map<String, String>> queryClassData(Set<String> classSet) throws DAOException {
		Map<String, Map<String, String>> retMap = new HashMap<String, Map<String, String>>();
		if (classSet.isEmpty()) {
			return retMap;
		}
		SqlBuilder sql = new SqlBuilder();
		sql.append(" select pk_classcy,clasname,bill_no ");
		sql.append(" from cy_classcy where nvl(dr,0) = 0 and pk_classcy", classSet.toArray(new String[0]));
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		List<Map<String, String>> listMap = (List<Map<String, String>>) dao.executeQuery(sql.toString(),
				new MapListProcessor());
		if (CollectionUtils.isNotEmpty(listMap)) {
			for (Map<String, String> map : listMap) {
				retMap.put(map.get("pk_classcy"), map);
			}
		}
		return retMap;
	}

	// 查询匹配学校信息
	public static Map<String, Map<String, String>> querySchoolData(Set<String> schoolSet) throws DAOException {
		Map<String, Map<String, String>> retMap = new HashMap<String, Map<String, String>>();
		if (schoolSet.isEmpty()) {
			return retMap;
		}
		SqlBuilder sql = new SqlBuilder();
		sql.append(" select pk_school,bill_no,sname,region schoolregion,saddress schooladdress ");
		sql.append(" from cy_schoolBasics where nvl(dr,0) = 0 and pk_school", schoolSet.toArray(new String[0]));
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		List<Map<String, String>> listMap = (List<Map<String, String>>) dao.executeQuery(sql.toString(),
				new MapListProcessor());
		if (CollectionUtils.isNotEmpty(listMap)) {
			for (Map<String, String> map : listMap) {
				retMap.put(map.get("pk_school"), map);
			}
		}
		return retMap;
	}

	public static String queryCountAll(QueryParmPOJO querypojo) {
		SqlBuilder sql = new SqlBuilder();
		BaseDAO dao = new BaseDAO();
		String sname = querypojo.getSname();
		String billType = querypojo.getBillType();
		try {
			sql.append("select count(1)  from cy_teacherform tfm join cy_schoolbasics scl on tfm.def1=scl.pk_school  where nvl(tfm.dr,0) = 0 and nvl(scl.dr,0) = 0 ");
			if (StringUtils.isNotBlank(sname)) {
				sql.append(" and scl.sname like '%" + sname + "%'");
			}
			if (StringUtils.isNotEmpty(billType)) {
				sql.append(" and tfm.teachertype = '" + billType + "'");
			}
			Object result = dao.executeQuery(sql.toString(), new ColumnProcessor());
			if (result == null) {
				return "0";
			}
			return result.toString();
		} catch (BusinessException e) {
			e.printStackTrace();
			return "0";
		}
	}

	public static List<LContent> queryData(QueryParmPOJO querypojo) throws DAOException {
		SqlBuilder sql = new SqlBuilder();
		int index = querypojo.getIndex().intValue();// 第几页
		int size = querypojo.getSize().intValue();// 每页数据量
		UFDouble start = (new UFDouble(index).sub(new UFDouble(1))).multiply(new UFDouble(size)).add(new UFDouble(1));
		UFDouble end = new UFDouble(index).multiply(new UFDouble(size));
		String sname = querypojo.getSname();
		String sort = querypojo.getSort();
		String billType = querypojo.getBillType();
		sql.append(" select pk_teacherform,pkschool,pkclasscy,dbilldate,ageclass from (");
		sql.append(" select rownum rn,tfm.pk_teacherform,tfm.def1 pkschool,tfm.def2 pkclasscy,tfm.dbilldate,tfm.ageclass ");
		sql.append(" from cy_teacherform tfm join cy_schoolbasics scl on tfm.def1=scl.pk_school where nvl(tfm.dr,0) = 0 and nvl(scl.dr,0) = 0 ");
		sql.append(" and rownum <=" + end.doubleValue());
		if (StringUtils.isNotEmpty(sname)) {
			sql.append(" and scl.sname like '%" + sname + "%'");
		}
		if (StringUtils.isNotEmpty(billType)) {
			sql.append(" and tfm.teachertype = '" + billType + "'");
		}
		if (StringUtils.isNotEmpty(sort)) {
			sql.append(" order by tfm." + querypojo.getSort());
		}else {
			sql.append(" order by tfm.ts desc ");
		}
		sql.append(" )  where 1 =1 and rn >= " + start.doubleValue());
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		List<LContent> listvos = (List<LContent>) dao.executeQuery(sql.toString(),
				new BeanListProcessor(LContent.class));
		return listvos;
	}

	@Override
	public CContentRet queryTeacherDetail(QueryParmPOJO querypojo)
			throws BusinessException, IllegalArgumentException, IllegalAccessException {
		DataContent<CContentRet> dc = new DataContent<CContentRet>();
		List<TeacherformHVO> teachList = queryDataCC(querypojo);
		if (CollectionUtils.isEmpty(teachList)) {
//			throw new BusinessException("未查询到数据");
			return null;
		}
		if (CollectionUtils.isNotEmpty(teachList) && teachList.size() > 1) {
			throw new BusinessException("查询到多条数据");
		}

		// 查询获取 学校、班级数据
		Set<String> schoolSet = new HashSet<String>();
		Set<String> classSet = new HashSet<String>();
		for (TeacherformHVO tfvo : teachList) {
			schoolSet.add(tfvo.getSchool());
			classSet.add(tfvo.getClasscy());
		}
		Map<String, Map<String, String>> schoolMap = querySchoolData(schoolSet);
		Map<String, Map<String, String>> classMap = queryClassData(classSet);

		// teachList 转换成 CCount
//		List<CContentRet> listcc = new ArrayList<CContentRet>();
		TeacherformHVO tfvo = teachList.get(0);
		CContent cct = new CContent();
		CContentRet cctret = new CContentRet();
		Class tfClass = tfvo.getClass();
		// 获取对象属性
		Field[] fields = tfClass.getDeclaredFields();
		for (Field field : fields) {
			String fieldname = field.getName();
			field.setAccessible(true); // 私有属性必须设置访问权限
			String value = field.get(tfvo) == null ? "" : field.get(tfvo).toString();
			Map<String, String> smap = schoolMap.get(value);
			Map<String, String> cmap = classMap.get(value);
			if (field.getType().getName().equals("nc.vo.pub.lang.UFBoolean")) {
				BaseRef ref = new BaseRef();
				ref.setLabel(value.equals("Y") ? "是" : "否");
				ref.setValue(value.equals("Y") ? "Y" : "N");
				cct.setAttributeValue(fieldname, ref);
			} else if ((field.getType().getName().equals("nc.vo.pub.lang.UFDateTime")
					|| field.getType().getName().equals("nc.vo.pub.lang.UFDate")) && StringUtils.isNotBlank(value)) {
				cct.setAttributeValue(fieldname, value.substring(0, 10));
			} else if (StringUtils.isNotBlank(value)) {
				if (fieldname.equals("school")) {
					if(null != smap) {
						SchoolRef scref = new SchoolRef();
						scref.setCode(smap.get("bill_no"));
						scref.setLabel(smap.get("sname"));
						scref.setSchoolregion(smap.get("schoolregion"));
						scref.setSchooladdress(smap.get("schooladdress"));
						scref.setValue(value);
						cct.setAttributeValue("school", scref);
					}
				} else if (fieldname.equals("classcy")) {
					if(null != cmap) {
						BaseRef ref = new BaseRef();
						ref.setCode(cmap.get("bill_no"));
						ref.setLabel(cmap.get("clasname"));
						ref.setValue(value);
						cct.setAttributeValue("classcy", ref);
					}
				} else if (fieldname.equals("teachertype")) {
					value = value.equals("") ? "0" : value;
					String lable = EnumConstant.teachertypeMap.get(value);
					if(StringUtils.isNotBlank(lable)) {
						BaseRef ref = new BaseRef();
						ref.setLabel(lable);
						ref.setValue(value);
						cct.setAttributeValue(fieldname, ref);
					}
				} else if (fieldname.equals("teacherapply")) {
					value = value.equals("") ? "0" : value;
					String lable = EnumConstant.teacherapplyMap.get(value);
					if (StringUtils.isNotBlank(lable)) {
						BaseRef ref = new BaseRef();
						ref.setLabel(lable);
						ref.setValue(value);
						cct.setAttributeValue(fieldname, ref);
					}
				} else if (fieldname.equals("paycompany")) {
					value = value.equals("1") ? "1" : "0";
					String lable = EnumConstant.paycompanyMap.get(value);
					if (StringUtils.isNotBlank(lable)) {
						BaseRef ref = new BaseRef();
						ref.setLabel(EnumConstant.paycompanyMap.get(value));
						ref.setValue(value);
						cct.setAttributeValue(fieldname, ref);
					}
				} else if ((fieldname.equals("predictpsn") || fieldname.equals("teachernum"))
						&& StringUtils.isNotBlank(value)) {
					cct.setAttributeValue(fieldname, value+"");
				} else {
					cct.setAttributeValue(fieldname, value);
				}
			}
		}
		
		BeanUtils.copyProperties(cct, cctret);
		return cctret;
	}

	public static List<TeacherformHVO> queryDataCC(QueryParmPOJO querypojo) throws DAOException {
		SqlBuilder sql = new SqlBuilder();
		String pk_teacherform = querypojo.getPk_teacherform();
		sql.append(" select pk_teacherform,ageclass,dbilldate,def1 school,def2 classcy,nvl(teachertype,'0') teachertype, ");
		sql.append(" startdate,enddate,classnum,teacherapply,ctmeeting,ptmeeting, ");
		sql.append(" stmeeting,teacherinfos,predictpsn,teachernum,putup,paycompany,vnote ");
		sql.append(" from cy_teacherform where nvl(dr,0) = 0 ");

		if (StringUtils.isNotEmpty(pk_teacherform)) {
			sql.append(" and pk_teacherform", pk_teacherform);
		}
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		List<TeacherformHVO> listvos = (List<TeacherformHVO>) dao.executeQuery(sql.toString(),
				new BeanListProcessor(TeacherformHVO.class));
		return listvos;
	}

	/**
	 * 讲师分配提交
	 * 
	 * @param restSave提交VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void commit(RestTeacherSave restTeacherQuery) throws BusinessException {
		// 1、将接口实体Vo转换成Aggvo
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restTeacherQuery, AggTeacherformHVO.class, true);
		AggTeacherformHVO transformAggVO = (AggTeacherformHVO) changeEntitySoPowerful.getAggvo();//转换后得到的Aggvo
		UserInfoByDiworkPOJO userinfo = changeEntitySoPowerful.getUserInfo();
		
		AggTeacherformHVO aggVO = null;
		if (CyCommonUtils.isNotEmpty(transformAggVO.getPrimaryKey())) {
			// 查询
			aggVO = (AggTeacherformHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(transformAggVO.getPrimaryKey(), AggTeacherformHVO.class);
			if(CyCommonUtils.isNotEmpty(aggVO)) {
				CyCommonUtils.getInstance(ICrmService.class).pfBusiAction(aggVO.getParentVO().getBilltype(), "SAVE", aggVO);	
			}
			else {
				throw new  BusinessException("提交失败");
			}
		}
		else {
			throw new  BusinessException("提交失败");
		}
	}
	

	/**
	 * 讲师分配
	 * 
	 * @param restSave提交VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void assign(TeacherAssign teachervo) throws BusinessException {
		try {
		AggTeacherformHVO aggVO = null;
		if (CyCommonUtils.isEmpty(teachervo.getPk_psndoc())) {
			throw new BusinessException("未选中待分配的人员信息");
		}
		if (CyCommonUtils.isEmpty(teachervo.getPk_teacherform())) {
			throw new BusinessException("未选中要分配的单据");
		}
		String pk_teacherform=teachervo.getPk_teacherform();
		// 根据前端传递的pks查询数据,获取单据
		aggVO=(AggTeacherformHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(pk_teacherform, AggTeacherformHVO.class);
		if (CyCommonUtils.isEmpty(aggVO)) {
			throw new BusinessException("未匹配到对应的单据");
		}
		TeacherformHVO thvo = aggVO.getParentVO();
		if (CyCommonUtils.isEmpty(thvo)) {
			throw new BusinessException("未匹配到对应的单据");
		}
	
		String pk_psndoc=teachervo.getPk_psndoc();
		String def6 = thvo.getDef6();// 是否分配 Y/N
		String bill_no = thvo.getBill_no();
		Integer approvestatus = thvo.getApprovestatus();
		UFDateTime startdate = thvo.getStartdate();
		UFDateTime enddate = thvo.getEnddate();

			if (CyCommonUtils.isEmpty(startdate) || CyCommonUtils.isEmpty(enddate)) {
				throw new BusinessException("单据：" + bill_no + " 开始时间或结束时间为空");
			}
			if (CyCommonUtils.isEmpty(approvestatus)
					|| (CyCommonUtils.isNotEmpty(approvestatus) && approvestatus.intValue() != 1)) {
				throw new BusinessException("单据：" + bill_no + " 不为审批态，不能分配");
			}
			// 查询获取学校信息
			String def1 = thvo.getDef1();// 学校
			if (CyCommonUtils.isEmpty(def1)) {
				throw new BusinessException("选择单据中学校信息为空");
			}
			String def2 = thvo.getDef2();// 班级
			if (CyCommonUtils.isEmpty(def2)) {
				throw new BusinessException("选择单据中班级信息为空");
			}

			AggSchoolBasicsHVO aggVOsch = null;
			aggVOsch=(AggSchoolBasicsHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(def1, AggSchoolBasicsHVO.class);
			NoteDataUtils.addLog(aggVOsch.getParentVO().getSname(),"cs02", false, "");
			if (CyCommonUtils.isEmpty(aggVOsch)) {
				throw new BusinessException("选择单据中学校信息未匹配到对应的学校档案");
			}
			SchoolBasicsHVO schoolhvo = aggVOsch.getParentVO();
			if (CyCommonUtils.isEmpty(schoolhvo)) {
				throw new BusinessException("选择单据中学校信息未匹配到对应的学校档案");
			}

			// 2. 查询获取班级信息
			Set<String> clapks = new HashSet<String>();
			clapks.add(def2);
			Map<String, ClasscyHVO> clmap = null;
			AggClasscyHVO aggVOclas = null;
			aggVOclas=(AggClasscyHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(def2, AggClasscyHVO.class);
			if (CyCommonUtils.isEmpty(aggVOclas)) {
				throw new BusinessException("选择单据中班级信息未匹配到对应的班级档案");
			}
			ClasscyHVO clHVO = aggVOclas.getParentVO();
			if (CyCommonUtils.isEmpty(clHVO)) {
				throw new BusinessException("选择单据中班级信息未匹配到对应的班级档案");
			}
			String userId = InvocationInfoProxy.getInstance().getUserId();
			String sname = schoolhvo.getSname();
			String clasname = clHVO.getClasname();
			// 人员档案子表数据处理
			// 查询人员页签数据，判断是否已生成，生成则修改，否则新增; 人员主键+学校+班级+开始时间
			List<GlbdefVO> glbdefVOs = queryPsndocGlbdef(pk_teacherform, pk_psndoc, sname, clasname, startdate);
//			NoteDataUtils.addLog( glbdefVOs.toString(),"cs04", false, "");
			ITeacherformhvoMaintain itService = NCLocator.getInstance().lookup(ITeacherformhvoMaintain.class);
			if (CyCommonUtils.isNotEmpty(glbdefVOs)) {
				for (GlbdefVO glbdefVO : glbdefVOs) {
					glbdefVO.setGlbdef1(schoolhvo.getDef11());// 大区
					glbdefVO.setGlbdef2(schoolhvo.getProvince());// 省份
					glbdefVO.setGlbdef3(schoolhvo.getSaddress());// 地区
					// glbdefVO.setGlbdef4(glbdef1); // 学校
					glbdefVO.setGlbdef5(thvo.getDef4());// 年级
					// glbdefVO.setGlbdef6(glbdef1);// 班级
					glbdefVO.setGlbdef7(thvo.getClassnum() + "");// 班级人数
					glbdefVO.setBegindate(new UFLiteralDate(startdate.toString().substring(0, 10)));
					glbdefVO.setEnddate(new UFLiteralDate(enddate.toString().substring(0, 10)));
					glbdefVO.setModifiedtime(new UFDateTime());
					glbdefVO.setModifier(userId);
					glbdefVO.setStatus(VOStatus.UPDATED);
				}
				itService.glbdefUpdate(glbdefVOs.toArray(new GlbdefVO[0]), new String[] { "glbdef1", "glbdef2",
						"glbdef3", "glbdef5", "glbdef7", "modifiedtime", "modifier" });
			} else {
				GlbdefVO glbdefVO = new GlbdefVO();
				glbdefVO.setCreationtime(new UFDateTime());
				glbdefVO.setCreator(userId);
				glbdefVO.setPk_psndoc_sub(getNewPK());
				glbdefVO.setPk_psndoc(pk_psndoc);
				glbdefVO.setGlbdef1(schoolhvo.getDef11());// 大区
				glbdefVO.setGlbdef2(schoolhvo.getProvince());// 省份
				glbdefVO.setGlbdef3(schoolhvo.getSaddress());// 地区
				glbdefVO.setGlbdef4(sname); // 学校
				glbdefVO.setGlbdef5(thvo.getDef4());// 年级
				glbdefVO.setGlbdef6(clasname);// 班级
				glbdefVO.setGlbdef7(thvo.getClassnum() + "");// 班级人数
				glbdefVO.setGlbdef8(pk_teacherform);// 老师申请单主键
				glbdefVO.setBegindate(new UFLiteralDate(startdate.toString().substring(0, 10)));
				glbdefVO.setEnddate(new UFLiteralDate(enddate.toString().substring(0, 10)));
				glbdefVO.setStatus(VOStatus.NEW);

				itService.glbdefInsert(glbdefVO);

			}

			// 3. 回写班级信息
			clHVO.setTeacher(pk_psndoc);
			clHVO.setStatus(VOStatus.UPDATED);
			VOUpdate<ClasscyHVO> classUpdate = new VOUpdate<ClasscyHVO>();
			classUpdate.update(new ClasscyHVO[] { clHVO }, new String[] { "teacher" });

			// 4. 回写老师/讲师申请单
			thvo.setTeacher(pk_psndoc);
			thvo.setDef6("Y");// 已分配
			thvo.setStatus(VOStatus.UPDATED);
			VOUpdate<TeacherformHVO> teacherUpdate = new VOUpdate<TeacherformHVO>();
			teacherUpdate.update(new TeacherformHVO[] { thvo }, new String[] { "teacher", "def6" });
		} catch (BusinessException ex) {
			// 处理异常信息
			Logger.error(ex);
			ExceptionUtils.wrapException(ex);
		}

		
	}
	


	@SuppressWarnings("unchecked")
	private List<GlbdefVO> queryPsndocGlbdef(String pk_teacherfrom, String pk_psndoc, String xxdef1, String bjdef2,
			UFDateTime startdate) {
		// pk+人员主键+学校+班级+开始时间
		IUAPQueryBS iuapSevice = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		SqlBuilder sql = new SqlBuilder();
		sql.append(" SELECT * FROM hi_psndoc_glbdef1 WHERE nvl(dr,0)=0 ");
		sql.append(" and glbdef8 ", pk_teacherfrom);
		sql.append(" and pk_psndoc ", pk_psndoc);
		sql.append(" and glbdef4 ", xxdef1);
		sql.append(" and glbdef6 ", bjdef2);
		sql.append(" and begindate ", startdate.toString().substring(0, 10));

		List<GlbdefVO> listVo = new ArrayList<GlbdefVO>();
		try {
			listVo = (List<GlbdefVO>) new BaseDAO().executeQuery(sql.toString(), new BeanListProcessor(GlbdefVO.class));
		} catch (BusinessException e) {
			Logger.error("查询人员数据时异常：" + e);
		}

		return listVo;
	}

	// pk生成器
	private String getNewPK() {
		IdGenerator idGenerator = new SequenceGenerator();
		return idGenerator.generate();
	}
}
