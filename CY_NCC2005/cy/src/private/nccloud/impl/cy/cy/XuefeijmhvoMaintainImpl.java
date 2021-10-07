
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
	 * 根据条件分页查询数据
	 * 
	 * @param restXuefei 查询VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public ResponseList queryListPage(RestXuefeiQuery restXuefei) throws BusinessException {
		// 1、查询条件拼装
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
		// 2、进行分页查询
		@SuppressWarnings("rawtypes")
		ResponseList queryListData = CyCommonUtils.getInstance(ICrmService.class).queryListPage(sql.toString(),
				restXuefei, AggXuefeijmHVO.class, XuefeiInfoPOJO.class);
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
	public XuefeiAggPOJO queryDeatil(String pk) throws BusinessException {

		if (CyCommonUtils.isEmpty(pk)) {
			throw new BusinessException("主键不能为空");
		}
		// 1、查询NccAggvo
		AggXuefeijmHVO aggvo = (AggXuefeijmHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(pk,
				AggXuefeijmHVO.class);

		// 2、将当前NccAggvo数据转换成接口实体数据
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(aggvo, XuefeiAggPOJO.class, false);
		XuefeiAggPOJO workpojo = (XuefeiAggPOJO) changeEntitySoPowerful.getRestObj();
		return workpojo;

	}

	/**
	 * 学费减免更新
	 * 
	 * @param restXuefei更新VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void billSave(RestXuefeiSave restXuefei) throws BusinessException {
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restXuefei, AggXuefeijmHVO.class,
				true);
		AggXuefeijmHVO transformAggVO = (AggXuefeijmHVO) changeEntitySoPowerful.getAggvo();// 转换后得到的Aggvo
		UserInfoByDiworkPOJO userinfo = changeEntitySoPowerful.getUserInfo();
		AggXuefeijmHVO aggVO = null;
		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
			// 修改
			aggVO = (AggXuefeijmHVO) CyCommonUtils.getInstance(ICrmService.class)
					.queryDeatil(transformAggVO.getPrimaryKey(), AggXuefeijmHVO.class);
			CrmUtil.editBillReplenishData(aggVO, transformAggVO, XuefeiAggPOJO.class, userinfo.getUserMobile());
		} else {
			// 新增
			aggVO = transformAggVO;
			aggVO.getParentVO().setDef6("否");
			CrmUtil.addBillInitData(transformAggVO, userinfo.getUserMobile());
			UserVO user = CyCommonUtils.getInstance(ICrmService.class)
					.getUserInfoByUserMobile(userinfo.getUserMobile());
			aggVO.getParentVO().setCreator(user.getCuserid());
			aggVO.getParentVO().setCreationtime(new UFDateTime());
		}
		if (CyCommonUtils.isNotEmpty(restXuefei.getXuefeiinfo().getDef1())) {
			aggVO.getParentVO().setSaddress(restXuefei.getXuefeiinfo().getDef1().getSchooladdress());
		}
		// 2、调用接口进行保存
		CyCommonUtils.getInstance(ICrmService.class).pfSaveBusiAction(aggVO.getParentVO().getBilltype(), aggVO, false);
	}

	/**
	 * 学费减免提交
	 * 
	 * @param restXuefei更新VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void commit(RestXuefeiSave restXuefei) throws BusinessException {
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restXuefei, AggXuefeijmHVO.class,
				true);
		AggXuefeijmHVO transformAggVO = (AggXuefeijmHVO) changeEntitySoPowerful.getAggvo();// 转换后得到的Aggvo
		AggXuefeijmHVO aggVO = null;
		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
			// 通过主键查询详情
			aggVO = (AggXuefeijmHVO) CyCommonUtils.getInstance(ICrmService.class)
					.queryDeatil(transformAggVO.getPrimaryKey(), AggXuefeijmHVO.class);
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

	@Override
	public String feeAmountQuery(String pk) throws BusinessException {
		SqlBuilder sql = new SqlBuilder();
		sql.append("select sum(reducation) from cy_xuefeijm where def6 = '是' and dr = '0' and def2 = '" + pk + "'");
		Object executeQuery = CyCommonUtils.getInstance(IUAPQueryBS.class).executeQuery(sql.toString(),
				new ColumnProcessor());
		if (CyCommonUtils.isNotEmpty(executeQuery)) {
			return executeQuery.toString();
		}
		return "";
	}
}
