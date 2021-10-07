package nccloud.impl.cy.cy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.classfilecy.ClassInfoPOJO;
import nc.api.cy.rest.entity.crm.classfilecy.RestClassSave;
import nc.api.cy.rest.entity.crm.schoolarchives.RestSchoolSave;
import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.classfilecy.ClasscyDetailVO;
import nc.vo.cy.classfilecy.ClasscyHVO;
import nc.vo.cy.classfilecy.pojo.ClassCyCardReturnPOJO;
import nc.vo.cy.classfilecy.pojo.ClassCyQueryPOJO;
import nc.vo.cy.pojo.RefPOJO;
import nc.vo.cy.pojo.SchoolPOJO;
import nc.vo.cy.studentfile.StudentBindVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.impl.dao.ClassCyBaseDao;
import nccloud.impl.pub.ace.AceAggbusiClasscyHVOPubServiceImpl;
import nccloud.itf.cy.cy.IClasscyhvoMaintain;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;

public class ClasscyhvoMaintainImpl extends AceAggbusiClasscyHVOPubServiceImpl implements IClasscyhvoMaintain {

	@Override
	public void delete(AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggClasscyHVO[] insert(AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggClasscyHVO[] update(AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggClasscyHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggClasscyHVO[] save(AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggClasscyHVO[] unsave(AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggClasscyHVO[] approve(AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills)
			throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggClasscyHVO[] unapprove(AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills)
			throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ResponseList queryClassCyList(ClassCyQueryPOJO queryvo) throws BusinessException {
		/*
		 * 查询列表班级信息rongleia
		 */
		String queryTotal = ClassCyBaseDao.queryTotal(queryvo);
		int total = Integer.parseInt(queryTotal);
		int pagesize = queryvo.getSize().intValue();
		int index = queryvo.getIndex().intValue();
		UFDouble totalPages;
		int split = new UFDouble(total).div(new UFDouble(pagesize)).intValue();
		UFDouble mod = new UFDouble(total).mod(new UFDouble(pagesize));
		if (mod.sub(new UFDouble(0)).doubleValue() > 0) {
			totalPages = new UFDouble(split).add(new UFDouble(1));
		} else {
			totalPages = new UFDouble(split);
		}
		List<ClasscyHVO> classcylist = ClassCyBaseDao.queryClassCyList(queryvo);
		if (CyCommonUtils.isEmpty(classcylist)) {
			return null;
		}
		List<AggClasscyHVO> listPageNccAggvo = new ArrayList<AggClasscyHVO>();
		for (ClasscyHVO classcyHVO : classcylist) {
			AggClasscyHVO aggvo = new AggClasscyHVO();
			aggvo.setParentVO(classcyHVO);
			listPageNccAggvo.add(aggvo);
		}
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful().pageListPage(ClassInfoPOJO.class,
				listPageNccAggvo, total, totalPages.intValue(), index, pagesize);
		ResponseList responseList = changeEntitySoPowerful.getPageList();
		if (responseList != null) {
			return responseList;
		}
		return null;
	}

	@Override
	public ClassCyCardReturnPOJO queryClassCyCard(String pk_classcy) throws BusinessException {
		/*
		 * 查询班级明细信息rongleia
		 */
		ClassCyCardReturnPOJO queryClassCyCard = ClassCyBaseDao.queryClassCyCard(pk_classcy);
		if (CyCommonUtils.isEmpty(queryClassCyCard)) {
			return new ClassCyCardReturnPOJO();
		}
		String pk_school = queryClassCyCard.getDef1();
		SchoolPOJO spojo = ClassCyBaseDao.querySchoolByPk(pk_school);
		queryClassCyCard.setSchool(spojo);
		queryClassCyCard.setDef1(null);
		String pk_teacher = queryClassCyCard.getUfteacher();
		RefPOJO teacher = ClassCyBaseDao.queryPetitionerByPk(pk_teacher);
		queryClassCyCard.setTeacher(teacher);
		queryClassCyCard.setUfteacher(null);
		String pk_gangboss = queryClassCyCard.getUfgangboss();
		RefPOJO gangboss = ClassCyBaseDao.queryPetitionerByPk(pk_gangboss);
		queryClassCyCard.setGangboss(gangboss);
		queryClassCyCard.setUfgangboss(null);
		String pk_super = queryClassCyCard.getUfsupervisor();
		RefPOJO supervisor = ClassCyBaseDao.queryPetitionerByPk(pk_super);
		queryClassCyCard.setSupervisor(supervisor);
		queryClassCyCard.setUfsupervisor(null);
		String pk_mark = queryClassCyCard.getUfmarketass();
		RefPOJO marketass = ClassCyBaseDao.queryPetitionerByPk(pk_mark);
		queryClassCyCard.setMarketass(marketass);
		queryClassCyCard.setUfmarketass(null);
		String pk_sale = queryClassCyCard.getUfsalesman();
		RefPOJO salesman = ClassCyBaseDao.queryPetitionerByPk(pk_sale);
		queryClassCyCard.setSalesman(salesman);
		queryClassCyCard.setUfsalesman(null);
		String pk_duty = queryClassCyCard.getUfdutypsn();
		RefPOJO dutypsn = ClassCyBaseDao.queryPetitionerByPk(pk_duty);
		queryClassCyCard.setDutypsn(dutypsn);
		queryClassCyCard.setUfdutypsn(null);
		String flag = queryClassCyCard.getUfpaperagre();
		RefPOJO paperagre = new RefPOJO();
		if ("Y".equals(flag)) {
			paperagre.setLabel("是");
			paperagre.setValue("Y");
		} else {
			paperagre.setLabel("否");
			paperagre.setValue("N");
		}
		queryClassCyCard.setPaperagre(paperagre);
		queryClassCyCard.setUfpaperagre(null);
		String flag2 = queryClassCyCard.getUfisaverage();
		RefPOJO isaverage = new RefPOJO();
		if ("Y".equals(flag2)) {
			isaverage.setLabel("是");
			isaverage.setValue("Y");
		} else {
			isaverage.setLabel("否");
			isaverage.setValue("N");
		}
		queryClassCyCard.setIsaverage(isaverage);
		queryClassCyCard.setUfisaverage(null);
		return queryClassCyCard;
	}

	/**
	 * 班级信息查询
	 * 
	 * @param pk
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> classcyDataQuery(Set<String> pk) {
		Map<String, String> retMap = new HashMap<String, String>();
		if (MMValueCheck.isEmpty(pk)) {
			return retMap;
		}
		IUAPQueryBS queryBS = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		try {
			SqlBuilder sql = new SqlBuilder();
			sql.append("SELECT distinct pk_classcy pk,clasname name FROM cy_classcy WHERE nvl(dr,0)=0 ");
			sql.append(" and pk_classcy ", pk.toArray(new String[0]));
			List<Map<String, String>> queryListMap = (List<Map<String, String>>) queryBS.executeQuery(sql.toString(),
					new MapListProcessor());
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
	
	public ClasscyHVO queryClasscyAndCheck(StudentBindVO sbind) throws BusinessException {
		String calSql = "SELECT * FROM cy_classcy where nvl(dr,0)=0 and bill_no = '" + sbind.getClassno() + "'";
		ClasscyHVO clvo = null;
		try {
			List<ClasscyHVO> listClassVO = (List<ClasscyHVO>) CyCommonUtils.getInstance(BaseDAO.class).executeQuery(calSql,
					new BeanListProcessor(ClasscyHVO.class));
			if (listClassVO.isEmpty()) {
				throw new BusinessException("班级编码：" + sbind.getClassno() + "未匹配到对应的班级信息");
			} else {
				clvo = listClassVO.get(0);
			}
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		String contractowner = clvo.getContractowner();
		if (MMValueCheck.isEmpty(contractowner)) {
			throw new BusinessException("班级编码：" + sbind.getClassno() + "对应的合同归属组织为空");
		}
		UFDouble amount = clvo.getPaystand();
		if (null == amount || (amount != null && amount.doubleValue() <= 0)) {
			throw new BusinessException("班级编码：" + clvo.getBill_no() + "对应的缴费标准为空或小于0");
		}
		
		return clvo;
	}
	
	
	
	
	/**
	 * 查询整张班级档案
	 * 表头可以获取到班级信息
	 * 表体存放了此班级的收款标准以及计划
	 */
	// 班级缓存
	private static Map<String,List<ClasscyDetailVO>> classMap = new HashMap<String, List<ClasscyDetailVO>>();
	
	
	public List<AggClasscyHVO> queryCyClassBillByCondition(String field,String fieldvalue[]) throws BusinessException {
		// 整单查询
		AbstractBill[] bills = CyCommonUtils.getInstance(ICyCommonSqlUtil.class)
				.queryBillsByCondition(AggClasscyHVO.class, field , fieldvalue);
		
		classMap.clear();
		if(CyCommonUtils.isNotEmpty(bills)) {
			Arrays.asList(bills).forEach(bill -> {
				try {
					// 获取班级档案表头主键
					String pk_cyclasshead = bill.getParentVO().getPrimaryKey();
					ClasscyDetailVO[] bodyvos = (ClasscyDetailVO[]) bill.getChildrenVO();
					classMap.put(pk_cyclasshead, Arrays.asList(bodyvos));
				} catch (BusinessException e) {
					throw new RuntimeException("班级查询异常："+e.getMessage());
				}
			});
			List<AggClasscyHVO> list = new ArrayList<AggClasscyHVO>();
			list.add((AggClasscyHVO) bills[0]);
			return list;
		}
		return null;
	}
	
	
	/**
	 * 获取班级整单表体部分，此方法只支持主键查询
	 * @param pk_cyclass_head
	 * @param pks
	 * @return
	 * @throws BusinessException
	 */
	public List<ClasscyDetailVO> getCyClassDetailFromClassMap(String pk_cyclass_head)  throws BusinessException {
		if (CyCommonUtils.isNotEmpty(classMap) && CyCommonUtils.isNotEmpty(pk_cyclass_head)) {
			List<ClasscyDetailVO> bodylist = classMap.get(pk_cyclass_head);
			List<ClasscyDetailVO> returnlist = new ArrayList<ClasscyDetailVO>();
			if (bodylist.isEmpty()) {
				throw new BusinessException("班级编码：" + pk_cyclass_head + "对应的表体数据为空");
			}
			bodylist.forEach(body -> {
				Integer dr = (Integer) body.getAttributeValue("dr");
				if(dr != 1) {
					returnlist.add(body);
				}
			});
			return returnlist;
		}
		return null;
	}
	
	/**
	 * 词源班级提交
	 * 
	 * @param restSave提交VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void commit(RestClassSave restSave) throws BusinessException {
		// 1、将接口实体Vo转换成Aggvo
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restSave,
				AggClasscyHVO.class, true);
		AggClasscyHVO transformAggVO = (AggClasscyHVO) changeEntitySoPowerful.getAggvo();// 转换后得到的Aggvo

		AggClasscyHVO aggVO = null;
		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
			// 修改
			aggVO = (AggClasscyHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(transformAggVO.getPrimaryKey(),
					AggClasscyHVO.class);
			// 2、调用接口进行提交
			CyCommonUtils.getInstance(ICrmService.class).pfBusiAction(aggVO.getParentVO().getBilltype(), "SAVE", aggVO);
		}
		else {
			throw new  BusinessException("提交失败");
		}
	}
	
	
	
}
