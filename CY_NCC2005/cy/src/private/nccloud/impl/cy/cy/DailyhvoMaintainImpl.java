
package nccloud.impl.cy.cy;

import org.apache.commons.lang3.StringUtils;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.api.cy.rest.entity.crm.daily.DailyAggPOJO;
import nc.api.cy.rest.entity.crm.daily.DailyInfoPOJO;
import nc.api.cy.rest.entity.crm.daily.RestDailyQuery;
import nc.api.cy.rest.entity.crm.daily.RestDailySave;
import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.classfilecy.ClasscyDetailVO;
import nc.vo.cy.classfilecy.ClasscyHVO;
import nc.vo.cy.daily.AggDailyHVO;
import nc.vo.cy.daily.DailyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.UserVO;
import nccloud.impl.pub.ace.AceAggbusiDailyHVOPubServiceImpl;
import nccloud.itf.cy.cy.IDailyhvoMaintain;

public class DailyhvoMaintainImpl extends AceAggbusiDailyHVOPubServiceImpl implements IDailyhvoMaintain {

	@Override
	public void delete(AggDailyHVO[] clientFullVOs, AggDailyHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggDailyHVO[] insert(AggDailyHVO[] clientFullVOs, AggDailyHVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggDailyHVO[] update(AggDailyHVO[] clientFullVOs, AggDailyHVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggDailyHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggDailyHVO[] save(AggDailyHVO[] clientFullVOs, AggDailyHVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggDailyHVO[] unsave(AggDailyHVO[] clientFullVOs, AggDailyHVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggDailyHVO[] approve(AggDailyHVO[] clientFullVOs, AggDailyHVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggDailyHVO[] unapprove(AggDailyHVO[] clientFullVOs, AggDailyHVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	/**
	 * 根据条件分页查询数据
	 * 
	 * @param restSave 查询VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public ResponseList queryListPage(RestDailyQuery restQuery) throws BusinessException {
		// 1、查询条件拼装
		SqlBuilder sql = new SqlBuilder();
		if (CyCommonUtils.isNotEmpty(restQuery.getReportdate())) {
			sql.append("and reportdate", restQuery.getReportdate());
		}
		if (CyCommonUtils.isNotEmpty(restQuery.getUrgency())) {
			sql.append("and def2", restQuery.getUrgency());
		}
		if (CyCommonUtils.isNotEmpty(restQuery.getBillmaker())) {
			UserVO vo = CyCommonUtils.getInstance(ICrmService.class).getUserInfoByUserMobile(restQuery.getBillmaker());
			sql.append("and def3", vo.getCuserid());
		}
		// 2、进行分页查询
		@SuppressWarnings("rawtypes")
		ResponseList queryListData = CyCommonUtils.getInstance(ICrmService.class).queryListPage(sql.toString(),
				restQuery, AggDailyHVO.class, DailyInfoPOJO.class);
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
	public DailyAggPOJO queryDeatil(String pk) throws BusinessException {

		if (CyCommonUtils.isEmpty(pk)) {
			throw new BusinessException("主键不能为空");
		}
		// 1、查询NccAggvo
		AggDailyHVO aggvo = (AggDailyHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(pk,
				AggDailyHVO.class);
		// 2、将当前NccAggvo数据转换成接口实体数据
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(aggvo, DailyAggPOJO.class, false);
		DailyAggPOJO workpojo = (DailyAggPOJO) changeEntitySoPowerful.getRestObj();
		return workpojo;

	}

	/**
	 * 更新
	 * 
	 * @param restSave 保存VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void billSave(RestDailySave restSave) throws BusinessException {
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restSave, AggDailyHVO.class, true);
		AggDailyHVO transformAggVO = (AggDailyHVO) changeEntitySoPowerful.getAggvo();// 转换后得到的Aggvo
		UserInfoByDiworkPOJO userinfo = changeEntitySoPowerful.getUserInfo();
		AggDailyHVO aggVO = null;
//    		CyCommonUtils.login();
		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
			// 修改
			aggVO = (AggDailyHVO) CyCommonUtils.getInstance(ICrmService.class)
					.queryDeatil(transformAggVO.getPrimaryKey(), AggDailyHVO.class);
			CrmUtil.editBillReplenishData(aggVO, transformAggVO, DailyAggPOJO.class, userinfo.getUserMobile());
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
		aggVO.getParentVO().setDef3(vo.getCuserid());
		// 2、调用接口进行保存
		CyCommonUtils.getInstance(ICrmService.class).pfSaveBusiAction(aggVO.getParentVO().getBilltype(), aggVO, false);
	}
}
