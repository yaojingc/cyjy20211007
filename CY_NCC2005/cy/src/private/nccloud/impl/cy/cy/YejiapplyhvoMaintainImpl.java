
package nccloud.impl.cy.cy;

import nccloud.impl.pub.ace.AceAggbusiYejiapplyHVOPubServiceImpl;
import nccloud.itf.cy.cy.IStudenthvoMaintain;
import nccloud.itf.cy.cy.IYejiapplyhvoMaintain;

import org.apache.commons.lang3.StringUtils;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;
import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.api.cy.rest.entity.crm.xuefeiapply.RestXuefeiSave;
import nc.api.cy.rest.entity.crm.yejiapply.RestYejiQuery;
import nc.api.cy.rest.entity.crm.yejiapply.RestYejiSave;
import nc.api.cy.rest.entity.crm.yejiapply.YejiAggPOJO;
import nc.api.cy.rest.entity.crm.yejiapply.YejiInfoPOJO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.yejiapply.AggYejiapplyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.UserVO;

public class YejiapplyhvoMaintainImpl extends AceAggbusiYejiapplyHVOPubServiceImpl implements IYejiapplyhvoMaintain {

	@Override
	public void delete(AggYejiapplyHVO[] clientFullVOs, AggYejiapplyHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggYejiapplyHVO[] insert(AggYejiapplyHVO[] clientFullVOs, AggYejiapplyHVO[] originBills)
			throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggYejiapplyHVO[] update(AggYejiapplyHVO[] clientFullVOs, AggYejiapplyHVO[] originBills)
			throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggYejiapplyHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggYejiapplyHVO[] save(AggYejiapplyHVO[] clientFullVOs, AggYejiapplyHVO[] originBills)
			throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggYejiapplyHVO[] unsave(AggYejiapplyHVO[] clientFullVOs, AggYejiapplyHVO[] originBills)
			throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggYejiapplyHVO[] approve(AggYejiapplyHVO[] clientFullVOs, AggYejiapplyHVO[] originBills)
			throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggYejiapplyHVO[] unapprove(AggYejiapplyHVO[] clientFullVOs, AggYejiapplyHVO[] originBills)
			throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	/**
	 * 根据条件分页查询数据
	 * 
	 * @param restYeji 查询VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public ResponseList queryListPage(RestYejiQuery restQuery) throws BusinessException {
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
		if (CyCommonUtils.isNotEmpty(restQuery.getOpenclas())) {
			sql.append("and def2", restQuery.getOpenclas());
		}
		if (CyCommonUtils.isNotEmpty(restQuery.getBillmaker())) {
			sql.append("and creator", restQuery.getBillmaker());
		}
		// 2、进行分页查询
		@SuppressWarnings("rawtypes")
		ResponseList queryListData = CyCommonUtils.getInstance(ICrmService.class).queryListPage(sql.toString(),
				restQuery, AggYejiapplyHVO.class, YejiInfoPOJO.class);
		return queryListData;

	}

	/**
	 * 星级评定(业绩申请)详情查询
	 * 
	 * @param pk 查询主键
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public YejiAggPOJO queryDeatil(String pk) throws BusinessException {

		if (CyCommonUtils.isEmpty(pk)) {
			throw new BusinessException("主键不能为空");
		}
		// 1、查询NccAggvo
		AggYejiapplyHVO aggvo = (AggYejiapplyHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(pk,
				AggYejiapplyHVO.class);

		// 2、将当前NccAggvo数据转换成接口实体数据
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(aggvo, YejiAggPOJO.class, false);
		YejiAggPOJO workpojo = (YejiAggPOJO) changeEntitySoPowerful.getRestObj();
		return workpojo;

	}

	/**
	 * 星级评定(业绩申请)更新
	 * 
	 * @param restYeji 保存VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void billSave(RestYejiSave restSave) throws BusinessException {
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restSave, AggYejiapplyHVO.class,
				true);
		AggYejiapplyHVO transformAggVO = (AggYejiapplyHVO) changeEntitySoPowerful.getAggvo();// 转换后得到的Aggvo
		UserInfoByDiworkPOJO userinfo = changeEntitySoPowerful.getUserInfo();
		AggYejiapplyHVO aggVO = null;
		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
			// 修改
			aggVO = (AggYejiapplyHVO) CyCommonUtils.getInstance(ICrmService.class)
					.queryDeatil(transformAggVO.getPrimaryKey(), AggYejiapplyHVO.class);
			CrmUtil.editBillReplenishData(aggVO, transformAggVO, YejiAggPOJO.class, userinfo.getUserMobile());
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
	 * 星级评定(业绩申请)提交
	 * 
	 * @param restSave提交VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void commit(RestYejiSave restSave) throws BusinessException {
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restSave, AggYejiapplyHVO.class,
				true);
		AggYejiapplyHVO transformAggVO = (AggYejiapplyHVO) changeEntitySoPowerful.getAggvo();// 转换后得到的Aggvo
		AggYejiapplyHVO aggVO = null;
		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
			// 通过主键查询详情
			aggVO = (AggYejiapplyHVO) CyCommonUtils.getInstance(ICrmService.class)
					.queryDeatil(transformAggVO.getPrimaryKey(), AggYejiapplyHVO.class);
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
