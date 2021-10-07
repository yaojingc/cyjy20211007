
package nccloud.impl.cy.cy;

import nccloud.impl.pub.ace.AceAggbusiJiedaiapplyHVOPubServiceImpl;
import nccloud.itf.cy.cy.IJiedaiapplyhvoMaintain;

import org.apache.commons.lang3.StringUtils;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.api.cy.rest.entity.crm.jiedaiapply.JiedaiAggPOJO;
import nc.api.cy.rest.entity.crm.jiedaiapply.JiedaiInfoPOJO;
import nc.api.cy.rest.entity.crm.jiedaiapply.RestJiedaiQuery;
import nc.api.cy.rest.entity.crm.jiedaiapply.RestJiedaiSave;
import nc.api.cy.rest.entity.crm.yejiapply.RestYejiSave;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.jiedaiapply.AggJiedaiapplyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.UserVO;

public class JiedaiapplyhvoMaintainImpl extends AceAggbusiJiedaiapplyHVOPubServiceImpl
		implements IJiedaiapplyhvoMaintain {

	@Override
	public void delete(AggJiedaiapplyHVO[] clientFullVOs, AggJiedaiapplyHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggJiedaiapplyHVO[] insert(AggJiedaiapplyHVO[] clientFullVOs, AggJiedaiapplyHVO[] originBills)
			throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggJiedaiapplyHVO[] update(AggJiedaiapplyHVO[] clientFullVOs, AggJiedaiapplyHVO[] originBills)
			throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggJiedaiapplyHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggJiedaiapplyHVO[] save(AggJiedaiapplyHVO[] clientFullVOs, AggJiedaiapplyHVO[] originBills)
			throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggJiedaiapplyHVO[] unsave(AggJiedaiapplyHVO[] clientFullVOs, AggJiedaiapplyHVO[] originBills)
			throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggJiedaiapplyHVO[] approve(AggJiedaiapplyHVO[] clientFullVOs, AggJiedaiapplyHVO[] originBills)
			throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggJiedaiapplyHVO[] unapprove(AggJiedaiapplyHVO[] clientFullVOs, AggJiedaiapplyHVO[] originBills)
			throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	/**
	 * 根据条件分页查询数据
	 * 
	 * @param restJiedai 查询VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public ResponseList queryListPage(RestJiedaiQuery restJiedai) throws BusinessException {
		// 1、查询条件拼装
		SqlBuilder sql = new SqlBuilder();
		if (CyCommonUtils.isNotEmpty(restJiedai.getSalesman())) {
			sql.append("and salesman in (select pk_psndoc from bd_psndoc where name like '%" + restJiedai.getSalesman()
					+ "%') ");
		}
		if (CyCommonUtils.isNotEmpty(restJiedai.getSchool())) {
			sql.append("and def1 in (select pk_school from cy_schoolbasics where sname like '%" + restJiedai.getSchool()
					+ "%') ");
		}
		if (CyCommonUtils.isNotEmpty(restJiedai.getBillmaker())) {
			sql.append("and creator", restJiedai.getBillmaker());
		}
		// 2、进行分页查询
		@SuppressWarnings("rawtypes")
		ResponseList queryListData = CyCommonUtils.getInstance(ICrmService.class).queryListPage(sql.toString(),
				restJiedai, AggJiedaiapplyHVO.class, JiedaiInfoPOJO.class);
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
	public JiedaiAggPOJO queryDeatil(String pk) throws BusinessException {

		if (CyCommonUtils.isEmpty(pk)) {
			throw new BusinessException("主键不能为空");
		}
		// 1、查询NccAggvo
		AggJiedaiapplyHVO aggvo = (AggJiedaiapplyHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(pk,
				AggJiedaiapplyHVO.class);

		// 2、将当前NccAggvo数据转换成接口实体数据
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(aggvo, JiedaiAggPOJO.class, false);
		JiedaiAggPOJO workpojo = (JiedaiAggPOJO) changeEntitySoPowerful.getRestObj();
		return workpojo;

	}

	/**
	 * 学费减免更新
	 * 
	 * @param restJiedai更新VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void billSave(RestJiedaiSave restJiedai) throws BusinessException {
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restJiedai, AggJiedaiapplyHVO.class,
				true);
		AggJiedaiapplyHVO transformAggVO = (AggJiedaiapplyHVO) changeEntitySoPowerful.getAggvo();// 转换后得到的Aggvo
		UserInfoByDiworkPOJO userinfo = changeEntitySoPowerful.getUserInfo();
		AggJiedaiapplyHVO aggVO = null;
		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
			// 修改
			aggVO = (AggJiedaiapplyHVO) CyCommonUtils.getInstance(ICrmService.class)
					.queryDeatil(transformAggVO.getPrimaryKey(), AggJiedaiapplyHVO.class);
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
		UserVO vo = CyCommonUtils.getInstance(ICrmService.class).getUserInfoByUserMobile(userinfo.getUserMobile());
		aggVO.getParentVO().setSalesman(vo.getPk_psndoc());

		// 2、调用接口进行保存
		CyCommonUtils.getInstance(ICrmService.class).pfSaveBusiAction(aggVO.getParentVO().getBilltype(), aggVO, false);
	}

	/**
	 * 客情接待申请单提交
	 * 
	 * @param restSave提交VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void commit(RestJiedaiSave restSave) throws BusinessException {
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restSave, AggJiedaiapplyHVO.class,
				true);
		AggJiedaiapplyHVO transformAggVO = (AggJiedaiapplyHVO) changeEntitySoPowerful.getAggvo();// 转换后得到的Aggvo
		AggJiedaiapplyHVO aggVO = null;
		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
			// 通过主键查询详情
			aggVO = (AggJiedaiapplyHVO) CyCommonUtils.getInstance(ICrmService.class)
					.queryDeatil(transformAggVO.getPrimaryKey(), AggJiedaiapplyHVO.class);
			if (CyCommonUtils.isNotEmpty(aggVO)) {
				CyCommonUtils.getInstance(ICrmService.class).pfBusiAction(aggVO.getParentVO().getBilltype(), "SAVE",
						aggVO);
			} else {
				throw new BusinessException("提交失败");
			}

		} else {
			throw new BusinessException("提交失败");
		}
	}
}
