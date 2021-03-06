
package nccloud.impl.cy.cy;

import nccloud.impl.pub.ace.AceAggbusiXuefeijmHVOPubServiceImpl;
import nccloud.itf.cy.cy.IXuefeijmhvoMaintain;

import org.apache.commons.lang3.StringUtils;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.api.cy.rest.entity.crm.xuefeiapply.RestXuefeiQuery;
import nc.api.cy.rest.entity.crm.xuefeiapply.RestXuefeiSave;
import nc.api.cy.rest.entity.crm.xuefeiapply.XuefeiAggPOJO;
import nc.api.cy.rest.entity.crm.xuefeiapply.XuefeiInfoPOJO;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.xuefeiapply.AggXuefeijmHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.UserVO;

public class XuefeijmhvoMaintainImpl extends AceAggbusiXuefeijmHVOPubServiceImpl implements IXuefeijmhvoMaintain {

	@Override
	public void delete(AggXuefeijmHVO[] clientFullVOs, AggXuefeijmHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggXuefeijmHVO[] insert(AggXuefeijmHVO[] clientFullVOs, AggXuefeijmHVO[] originBills)
			throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggXuefeijmHVO[] update(AggXuefeijmHVO[] clientFullVOs, AggXuefeijmHVO[] originBills)
			throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggXuefeijmHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggXuefeijmHVO[] save(AggXuefeijmHVO[] clientFullVOs, AggXuefeijmHVO[] originBills)
			throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggXuefeijmHVO[] unsave(AggXuefeijmHVO[] clientFullVOs, AggXuefeijmHVO[] originBills)
			throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggXuefeijmHVO[] approve(AggXuefeijmHVO[] clientFullVOs, AggXuefeijmHVO[] originBills)
			throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggXuefeijmHVO[] unapprove(AggXuefeijmHVO[] clientFullVOs, AggXuefeijmHVO[] originBills)
			throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	/**
	 * ??????????????????????????????
	 * 
	 * @param restXuefei ??????VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public ResponseList queryListPage(RestXuefeiQuery restXuefei) throws BusinessException {
		// 1?????????????????????
		SqlBuilder sql = new SqlBuilder();
		if (CyCommonUtils.isNotEmpty(restXuefei.getSchool())) {
			sql.append("and def1 in (select pk_school from cy_schoolbasics where sname like '%" + restXuefei.getSchool()
					+ "%') ");
		}
		if (CyCommonUtils.isNotEmpty(restXuefei.getStudent())) {
			sql.append("and def2 in (select pk_student from cy_student where name like '%" + restXuefei.getStudent()
					+ "%') ");
		}
		if (CyCommonUtils.isNotEmpty(restXuefei.getClas())) {
			sql.append("and def3 in (select pk_classcy from cy_classcy where name like '%" + restXuefei.getClas()
					+ "%') ");
		}
		if (CyCommonUtils.isNotEmpty(restXuefei.getBillmaker())) {
			sql.append("and creator", restXuefei.getBillmaker());
		}
		// 2?????????????????????
		@SuppressWarnings("rawtypes")
		ResponseList queryListData = CyCommonUtils.getInstance(ICrmService.class).queryListPage(sql.toString(),
				restXuefei, AggXuefeijmHVO.class, XuefeiInfoPOJO.class);
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
	public XuefeiAggPOJO queryDeatil(String pk) throws BusinessException {

		if (CyCommonUtils.isEmpty(pk)) {
			throw new BusinessException("??????????????????");
		}
		// 1?????????NccAggvo
		AggXuefeijmHVO aggvo = (AggXuefeijmHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(pk,
				AggXuefeijmHVO.class);

		// 2????????????NccAggvo?????????????????????????????????
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(aggvo, XuefeiAggPOJO.class, false);
		XuefeiAggPOJO workpojo = (XuefeiAggPOJO) changeEntitySoPowerful.getRestObj();
		return workpojo;

	}

	/**
	 * ??????????????????
	 * 
	 * @param restXuefei??????VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void billSave(RestXuefeiSave restXuefei) throws BusinessException {
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restXuefei, AggXuefeijmHVO.class,
				true);
		AggXuefeijmHVO transformAggVO = (AggXuefeijmHVO) changeEntitySoPowerful.getAggvo();// ??????????????????Aggvo
		UserInfoByDiworkPOJO userinfo = changeEntitySoPowerful.getUserInfo();
		AggXuefeijmHVO aggVO = null;
		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
			// ??????
			aggVO = (AggXuefeijmHVO) CyCommonUtils.getInstance(ICrmService.class)
					.queryDeatil(transformAggVO.getPrimaryKey(), AggXuefeijmHVO.class);
			CrmUtil.editBillReplenishData(aggVO, transformAggVO, XuefeiAggPOJO.class, userinfo.getUserMobile());
		} else {
			// ??????
			aggVO = transformAggVO;
			aggVO.getParentVO().setDef6("???");
			CrmUtil.addBillInitData(transformAggVO, userinfo.getUserMobile());
			UserVO user = CyCommonUtils.getInstance(ICrmService.class)
					.getUserInfoByUserMobile(userinfo.getUserMobile());
			aggVO.getParentVO().setCreator(user.getCuserid());
			aggVO.getParentVO().setCreationtime(new UFDateTime());
		}
		if (CyCommonUtils.isNotEmpty(restXuefei.getXuefeiinfo().getDef1())) {
			aggVO.getParentVO().setSaddress(restXuefei.getXuefeiinfo().getDef1().getSchooladdress());
		}
		// 2???????????????????????????
		CyCommonUtils.getInstance(ICrmService.class).pfSaveBusiAction(aggVO.getParentVO().getBilltype(), aggVO, false);
	}

	/**
	 * ??????????????????
	 * 
	 * @param restXuefei??????VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void commit(RestXuefeiSave restXuefei) throws BusinessException {
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restXuefei, AggXuefeijmHVO.class,
				true);
		AggXuefeijmHVO transformAggVO = (AggXuefeijmHVO) changeEntitySoPowerful.getAggvo();// ??????????????????Aggvo
		AggXuefeijmHVO aggVO = null;
		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
			// ????????????????????????
			aggVO = (AggXuefeijmHVO) CyCommonUtils.getInstance(ICrmService.class)
					.queryDeatil(transformAggVO.getPrimaryKey(), AggXuefeijmHVO.class);
			if (CyCommonUtils.isNotEmpty(aggVO)) {
				CyCommonUtils.getInstance(ICrmService.class).pfBusiAction(aggVO.getParentVO().getBilltype(), "SAVE",
						aggVO);
			} else {
				throw new BusinessException("????????????");
			}

		} else {
			throw new BusinessException("????????????");
		}
	}

	@Override
	public String feeAmountQuery(String pk) throws BusinessException {
		SqlBuilder sql = new SqlBuilder();
		sql.append("select sum(reducation) from cy_xuefeijm where def6 = '???' and dr = '0' and def2 = '" + pk + "'");
		Object executeQuery = CyCommonUtils.getInstance(IUAPQueryBS.class).executeQuery(sql.toString(),
				new ColumnProcessor());
		if (CyCommonUtils.isNotEmpty(executeQuery)) {
			return executeQuery.toString();
		}
		return "";
	}
}
