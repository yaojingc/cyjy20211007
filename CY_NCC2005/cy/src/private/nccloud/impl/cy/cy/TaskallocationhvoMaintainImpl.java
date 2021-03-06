
package nccloud.impl.cy.cy;

import nccloud.impl.pub.ace.AceAggbusiTaskallocationHVOPubServiceImpl;
import nccloud.itf.cy.cy.ITaskallocationhvoMaintain;

import org.apache.commons.lang3.StringUtils;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.api.cy.rest.entity.crm.taskallocation.RestTaskallocationQuery;
import nc.api.cy.rest.entity.crm.taskallocation.RestTaskallocationSave;
import nc.api.cy.rest.entity.crm.taskallocation.TaskallocationAggPOJO;
import nc.api.cy.rest.entity.crm.taskallocation.TaskallocationInfoPOJO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.UserVO;

public class TaskallocationhvoMaintainImpl extends AceAggbusiTaskallocationHVOPubServiceImpl
		implements ITaskallocationhvoMaintain {

	@Override
	public void delete(AggTaskallocationHVO[] clientFullVOs, AggTaskallocationHVO[] originBills)
			throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggTaskallocationHVO[] insert(AggTaskallocationHVO[] clientFullVOs, AggTaskallocationHVO[] originBills)
			throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggTaskallocationHVO[] update(AggTaskallocationHVO[] clientFullVOs, AggTaskallocationHVO[] originBills)
			throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggTaskallocationHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggTaskallocationHVO[] save(AggTaskallocationHVO[] clientFullVOs, AggTaskallocationHVO[] originBills)
			throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggTaskallocationHVO[] unsave(AggTaskallocationHVO[] clientFullVOs, AggTaskallocationHVO[] originBills)
			throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggTaskallocationHVO[] approve(AggTaskallocationHVO[] clientFullVOs, AggTaskallocationHVO[] originBills)
			throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggTaskallocationHVO[] unapprove(AggTaskallocationHVO[] clientFullVOs, AggTaskallocationHVO[] originBills)
			throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	/**
	 * ??????????????????????????????
	 * 
	 * @param restTask ??????VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public ResponseList queryListPage(RestTaskallocationQuery restTask) throws BusinessException {
		// 1?????????????????????
		SqlBuilder sql = new SqlBuilder();
		if (CyCommonUtils.isNotEmpty(restTask.getSalesman())) {
			sql.append("and salesman in (select pk_psndoc from bd_psndoc where name like '%" + restTask.getSalesman()
					+ "%') ");
		}
		if (CyCommonUtils.isNotEmpty(restTask.getBillmaker())) {
			sql.append("and creator", restTask.getBillmaker());
		}
		// 2?????????????????????
		@SuppressWarnings("rawtypes")
		ResponseList queryListData = CyCommonUtils.getInstance(ICrmService.class).queryListPage(sql.toString(),
				restTask, AggTaskallocationHVO.class, TaskallocationInfoPOJO.class);
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
	public TaskallocationAggPOJO queryDeatil(String pk) throws BusinessException {

		if (CyCommonUtils.isEmpty(pk)) {
			throw new BusinessException("??????????????????");
		}
		// 1?????????NccAggvo
		AggTaskallocationHVO aggvo = (AggTaskallocationHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(pk,
				AggTaskallocationHVO.class);

		// 2????????????NccAggvo?????????????????????????????????
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(aggvo, TaskallocationAggPOJO.class,
				false);
		TaskallocationAggPOJO workpojo = (TaskallocationAggPOJO) changeEntitySoPowerful.getRestObj();
		return workpojo;

	}

	/**
	 * ??????????????????
	 * 
	 * @param restTask??????VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void billSave(RestTaskallocationSave restTask) throws BusinessException {
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restTask, AggTaskallocationHVO.class,
				true);
		AggTaskallocationHVO transformAggVO = (AggTaskallocationHVO) changeEntitySoPowerful.getAggvo();// ??????????????????Aggvo
		UserInfoByDiworkPOJO userinfo = changeEntitySoPowerful.getUserInfo();
		AggTaskallocationHVO aggVO = null;
		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
			// ??????
			aggVO = (AggTaskallocationHVO) CyCommonUtils.getInstance(ICrmService.class)
					.queryDeatil(transformAggVO.getPrimaryKey(), AggTaskallocationHVO.class);
			CrmUtil.editBillReplenishData(aggVO, transformAggVO, TaskallocationAggPOJO.class, userinfo.getUserMobile());
		} else {
			// ??????
			aggVO = transformAggVO;
			CrmUtil.addBillInitData(transformAggVO, userinfo.getUserMobile());
			UserVO user = CyCommonUtils.getInstance(ICrmService.class)
					.getUserInfoByUserMobile(userinfo.getUserMobile());
			aggVO.getParentVO().setCreator(user.getCuserid());
			aggVO.getParentVO().setCreationtime(new UFDateTime());
		}
		UserVO vo = CyCommonUtils.getInstance(ICrmService.class).getUserInfoByUserMobile(userinfo.getUserMobile());
		if(CyCommonUtils.isEmpty(aggVO.getParentVO().getSalesman())) {
			aggVO.getParentVO().setSalesman(vo.getPk_psndoc());
		}
		// 2???????????????????????????
		CyCommonUtils.getInstance(ICrmService.class).pfSaveBusiAction(aggVO.getParentVO().getBilltype(), aggVO, false);
	}
}
