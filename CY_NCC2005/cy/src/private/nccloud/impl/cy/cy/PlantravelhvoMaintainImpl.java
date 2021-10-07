
package nccloud.impl.cy.cy;

import org.apache.commons.lang3.StringUtils;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.api.cy.rest.entity.crm.jiedaiapply.JiedaiAggPOJO;
import nc.api.cy.rest.entity.crm.plantravel.PlantravelAggPOJO;
import nc.api.cy.rest.entity.crm.plantravel.PlantravelInfoPOJO;
import nc.api.cy.rest.entity.crm.plantravel.RestPlantravelQuery;
import nc.api.cy.rest.entity.crm.plantravel.RestPlantravelSave;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.plantravel.AggPlantravelHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.UserVO;
import nccloud.impl.pub.ace.AceAggbusiPlantravelHVOPubServiceImpl;
import nccloud.itf.cy.cy.IPlantravelhvoMaintain;

public class PlantravelhvoMaintainImpl extends AceAggbusiPlantravelHVOPubServiceImpl implements IPlantravelhvoMaintain {

	@Override
	public void delete(AggPlantravelHVO[] clientFullVOs, AggPlantravelHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggPlantravelHVO[] insert(AggPlantravelHVO[] clientFullVOs, AggPlantravelHVO[] originBills)
			throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggPlantravelHVO[] update(AggPlantravelHVO[] clientFullVOs, AggPlantravelHVO[] originBills)
			throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggPlantravelHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggPlantravelHVO[] save(AggPlantravelHVO[] clientFullVOs, AggPlantravelHVO[] originBills)
			throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggPlantravelHVO[] unsave(AggPlantravelHVO[] clientFullVOs, AggPlantravelHVO[] originBills)
			throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggPlantravelHVO[] approve(AggPlantravelHVO[] clientFullVOs, AggPlantravelHVO[] originBills)
			throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggPlantravelHVO[] unapprove(AggPlantravelHVO[] clientFullVOs, AggPlantravelHVO[] originBills)
			throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	/**
	 * 根据条件分页查询数据
	 * 
	 * @param restQuery 查询VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public ResponseList queryListPage(RestPlantravelQuery restQuery) throws BusinessException {
		// 1、查询条件拼装
		SqlBuilder sql = new SqlBuilder();
		if (CyCommonUtils.isNotEmpty(restQuery.getSalesman())) {
			sql.append("and salesman in (select pk_psndoc from bd_psndoc where name like '%" + restQuery.getSalesman()
					+ "%') ");
		}
		if (CyCommonUtils.isNotEmpty(restQuery.getSchool())) {
			sql.append("and def1 in (select pk_school from cy_schoolbasics where sname like '%" + restQuery.getSchool()
					+ "%') ");
		}
		if (CyCommonUtils.isNotEmpty(restQuery.getBillmaker())) {
			sql.append("and creator", restQuery.getBillmaker());
		}
		// 2、进行分页查询
		@SuppressWarnings("rawtypes")
		ResponseList queryListData = CyCommonUtils.getInstance(ICrmService.class).queryListPage(sql.toString(),
				restQuery, AggPlantravelHVO.class, PlantravelInfoPOJO.class);
		return queryListData;

	}

	/**
	 * 根据主键查询详情
	 * 
	 * @param pk 查询主键
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public PlantravelAggPOJO queryDeatil(String pk) throws BusinessException {

		if (CyCommonUtils.isEmpty(pk)) {
			throw new BusinessException("主键不能为空");
		}
		// 1、查询NccAggvo
		AggPlantravelHVO aggvo = (AggPlantravelHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(pk,
				AggPlantravelHVO.class);

		// 2、将当前NccAggvo数据转换成接口实体数据
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(aggvo, PlantravelAggPOJO.class,
				false);
		PlantravelAggPOJO workpojo = (PlantravelAggPOJO) changeEntitySoPowerful.getRestObj();
		return workpojo;

	}

	/**
	 * 学费减免更新
	 * 
	 * @param restQuery更新VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void billSave(RestPlantravelSave restSave) throws BusinessException {
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restSave, AggPlantravelHVO.class,
				true);
		AggPlantravelHVO transformAggVO = (AggPlantravelHVO) changeEntitySoPowerful.getAggvo();// 转换后得到的Aggvo
		UserInfoByDiworkPOJO userinfo = changeEntitySoPowerful.getUserInfo();
		AggPlantravelHVO aggVO = null;
		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
			// 修改
			aggVO = (AggPlantravelHVO) CyCommonUtils.getInstance(ICrmService.class)
					.queryDeatil(transformAggVO.getPrimaryKey(), AggPlantravelHVO.class);
			CrmUtil.editBillReplenishData(aggVO, transformAggVO, JiedaiAggPOJO.class, userinfo.getUserMobile());
		} else {
			// 新增
			aggVO = transformAggVO;
			CrmUtil.addBillInitData(transformAggVO, userinfo.getUserMobile());
			UserVO user = CyCommonUtils.getInstance(ICrmService.class)
					.getUserInfoByUserMobile(userinfo.getUserMobile());
			aggVO.getParentVO().setCreator(user.getCuserid());
			aggVO.getParentVO().setCreationtime(new UFDateTime());
		}
		// 调用接口进行保存
		CyCommonUtils.getInstance(ICrmService.class).pfSaveBusiAction(aggVO.getParentVO().getBilltype(), aggVO, false);
	}
}
