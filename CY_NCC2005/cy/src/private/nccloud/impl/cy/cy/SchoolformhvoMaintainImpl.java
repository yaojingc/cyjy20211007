
package nccloud.impl.cy.cy;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.api.cy.rest.entity.crm.schoolform.RestSchoolFindQuery;
import nc.api.cy.rest.entity.crm.schoolform.RestSchoolFindSave;
import nc.api.cy.rest.entity.crm.schoolform.SchoolFindAggPOJO;
import nc.api.cy.rest.entity.crm.schoolform.SchoolFindInfoPOJO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.utils.commonConstant.EnumConstant;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.cy.pojo.RefPOJO;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.cy.schoolform.AggSchoolformHVO;
import nc.vo.cy.schoolform.pojo.SchoolFormCardReturnPOJO;
import nc.vo.cy.schoolform.pojo.SchoolFormDetailReturnPOJO;
import nc.vo.cy.schoolform.pojo.SchoolFormQueryPOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.impl.dao.SchoolFromBaseDao;
import nccloud.impl.pub.ace.AceAggbusiSchoolformHVOPubServiceImpl;
import nccloud.itf.cy.cy.ISchoolformhvoMaintain;

public class SchoolformhvoMaintainImpl extends AceAggbusiSchoolformHVOPubServiceImpl implements ISchoolformhvoMaintain {

	@Override
	public void delete(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggSchoolformHVO[] insert(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggSchoolformHVO[] update(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggSchoolformHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggSchoolformHVO[] save(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSchoolformHVO[] unsave(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSchoolformHVO[] approve(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSchoolformHVO[] unapprove(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public PagingPOJO querySchoolList(SchoolFormQueryPOJO queryvo) throws BusinessException {
		PagingPOJO revo = new PagingPOJO();
		List<SchoolFormDetailReturnPOJO> schoolflist = SchoolFromBaseDao.querySchoolList(queryvo);
		if (CyCommonUtils.isEmpty(schoolflist)) {
			return null;
		}
		for (SchoolFormDetailReturnPOJO schoolFormDetailReturnPOJO : schoolflist) {
			String pk_proposer = schoolFormDetailReturnPOJO.getProposer();
			if (CyCommonUtils.isNotEmpty(pk_proposer)) {
				RefPOJO rpojo = SchoolFromBaseDao.queryPetitionerByPk(pk_proposer);
				schoolFormDetailReturnPOJO.setPetitioner(rpojo);
				schoolFormDetailReturnPOJO.setProposer(null);
			}
			String ufregion = schoolFormDetailReturnPOJO.getUfregion();
			if (CyCommonUtils.isNotEmpty(ufregion)) {
				RefPOJO rpojo = new RefPOJO();
				rpojo.setValue(ufregion);
				rpojo.setLabel(EnumConstant.areatypeMap.get(ufregion));
				schoolFormDetailReturnPOJO.setRegion(rpojo);
				schoolFormDetailReturnPOJO.setUfregion(null);
			}
		}
		String school = queryvo.getSchool();
		String queryTotal = SchoolFromBaseDao.queryTotal(school);
		int total = Integer.parseInt(queryTotal);
		int pagesize = queryvo.getSize().intValue();
		revo.setTotal(total);
		UFDouble totalPages;
		int split = new UFDouble(total).div(new UFDouble(pagesize)).intValue();
		UFDouble mod = new UFDouble(total).mod(new UFDouble(pagesize));
		if (mod.sub(new UFDouble(0)).doubleValue() > 0) {
			totalPages = new UFDouble(split).add(new UFDouble(1));
		} else {
			totalPages = new UFDouble(split);
		}
		revo.setTotalPages(totalPages.intValue());
		revo.setPageSize(pagesize);
		revo.setCurrentPage(queryvo.getIndex().intValue());
		revo.setContent(schoolflist);
		return revo;
	}

	@Override
	public SchoolFormCardReturnPOJO querySchoolCard(String pk_schoolf) throws BusinessException {
		/*
		 * ??????????????????????????????????????????
		 */
		SchoolFormCardReturnPOJO cardpojo = SchoolFromBaseDao.querySchoolCard(pk_schoolf);
		if (CyCommonUtils.isEmpty(cardpojo)) {
			return new SchoolFormCardReturnPOJO();
		}
		String proposer = cardpojo.getProposer();
		if (CyCommonUtils.isNotEmpty(proposer)) {
			RefPOJO rpojo = SchoolFromBaseDao.queryPetitionerByPk(proposer);
			cardpojo.setPetitioner(rpojo);
			cardpojo.setProposer(null);
		}
		String ufregion = cardpojo.getUfregion();
		if (CyCommonUtils.isNotEmpty(ufregion)) {
			RefPOJO rpojo = new RefPOJO();
			rpojo.setValue(ufregion);
			rpojo.setLabel(EnumConstant.areatypeMap.get(ufregion));
			cardpojo.setRegion(rpojo);
			cardpojo.setUfregion(null);
		}
		return cardpojo;
	}
	
	/**
	 * ??????????????????????????????
	 * 
	 * @param restQuery ??????VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public ResponseList queryListPage(RestSchoolFindQuery restQuery) throws BusinessException {
		// 1?????????????????????
		SqlBuilder sql = new SqlBuilder();
		if(CyCommonUtils.isNotEmpty(restQuery.getSchool())) {
			sql.append("and def1 in (select pk_school from cy_schoolbasics where sname like '%" + restQuery.getSchool() + "%') ");
		}if(CyCommonUtils.isNotEmpty(restQuery.getBillmaker())) {
			sql.append("and creator", restQuery.getBillmaker());			
		}
		// 2?????????????????????
		@SuppressWarnings("rawtypes")
		ResponseList queryListData =CyCommonUtils.getInstance(ICrmService.class).queryListPage(sql.toString(),
				restQuery, AggSchoolformHVO.class, SchoolFindInfoPOJO.class);
		return queryListData;

	}
	
	/**
	 * ????????????????????????
	 * 
	 * @param pk ????????????
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public SchoolFindAggPOJO queryDeatil(String pk) throws BusinessException {

		if (CyCommonUtils.isEmpty(pk)) {
			throw new BusinessException("??????????????????");
		}
		// 1?????????NccAggvo
		AggSchoolformHVO aggvo = (AggSchoolformHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(pk,
				AggSchoolformHVO.class);

		// 2????????????NccAggvo?????????????????????????????????
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(aggvo, SchoolFindAggPOJO.class, false);
		SchoolFindAggPOJO workpojo = (SchoolFindAggPOJO) changeEntitySoPowerful.getRestObj();
		return workpojo;

	}

	/**
	 * ?????????????????????
	 * 
	 * @param restSave??????VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void billSave(RestSchoolFindSave restSave) throws BusinessException {
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restSave, AggSchoolformHVO.class,
				true);
		AggSchoolformHVO transformAggVO = (AggSchoolformHVO) changeEntitySoPowerful.getAggvo();// ??????????????????Aggvo
		UserInfoByDiworkPOJO userinfo = changeEntitySoPowerful.getUserInfo();
		AggSchoolformHVO aggVO = null;
		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
			// ??????
			aggVO = (AggSchoolformHVO) CyCommonUtils.getInstance(ICrmService.class)
					.queryDeatil(transformAggVO.getPrimaryKey(), AggSchoolformHVO.class);
			CrmUtil.editBillReplenishData(aggVO, transformAggVO, SchoolFindAggPOJO.class, userinfo.getUserMobile());
		} else {
			// ??????
			aggVO = transformAggVO;
			CrmUtil.addBillInitData(transformAggVO, userinfo.getUserMobile());
		}
		// 2???????????????????????????
		CyCommonUtils.getInstance(ICrmService.class).pfSaveBusiAction(aggVO.getParentVO().getBilltype(), aggVO, false);
	}
	
	/**
	 * ?????????????????????
	 * 
	 * @param restSave??????VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void commit(RestSchoolFindSave restSave) throws BusinessException {
		// 1??????????????????Vo?????????Aggvo
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restSave,
				AggSchoolformHVO.class, true);
		AggSchoolformHVO transformAggVO = (AggSchoolformHVO) changeEntitySoPowerful.getAggvo();// ??????????????????Aggvo

		AggSchoolformHVO aggVO = null;
		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
			// ??????
			aggVO = (AggSchoolformHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(transformAggVO.getPrimaryKey(),
					AggSchoolformHVO.class);
			// 2???????????????????????????
			CyCommonUtils.getInstance(ICrmService.class).pfBusiAction(aggVO.getParentVO().getBilltype(), "SAVE", aggVO);
		}
		else {
			throw new  BusinessException("????????????");
		}
	}

}
