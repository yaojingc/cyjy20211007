
package nccloud.impl.cy.cy;

import nccloud.impl.pub.ace.AceAggbusiTaskfeedbackHVOPubServiceImpl;
import nccloud.itf.cy.cy.ITaskfeedbackhvoMaintain ;

import org.apache.commons.lang3.StringUtils;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.api.cy.rest.entity.crm.taskfeedback.RestTaskfeedbackQuery;
import nc.api.cy.rest.entity.crm.taskfeedback.RestTaskfeedbackSave;
import nc.api.cy.rest.entity.crm.taskfeedback.TaskfeedbackAggPOJO;
import nc.api.cy.rest.entity.crm.taskfeedback.TaskfeedbackInfoPOJO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.taskfeedback.AggTaskfeedbackHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.UserVO;

public class TaskfeedbackhvoMaintainImpl extends AceAggbusiTaskfeedbackHVOPubServiceImpl implements ITaskfeedbackhvoMaintain  {

        @Override
        public void delete(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException {
                super.pubdeleteBills(clientFullVOs, originBills);
        }

        @Override
        public AggTaskfeedbackHVO[] insert(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException {
                return super.pubinsertBills(clientFullVOs, originBills);
        }

        @Override
        public AggTaskfeedbackHVO[] update(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException {
                return super.pubupdateBills(clientFullVOs, originBills);
        }

        @Override
        public AggTaskfeedbackHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException {
                return super.pubquerybills(queryScheme);
        }

        @Override
        public AggTaskfeedbackHVO[] save(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException {
                return super.pubsendapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggTaskfeedbackHVO[] unsave(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException {
                return super.pubunsendapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggTaskfeedbackHVO[] approve(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException {
                return super.pubapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggTaskfeedbackHVO[] unapprove(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException {
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
    	public ResponseList queryListPage(RestTaskfeedbackQuery restQuery) throws BusinessException {
    		// 1、查询条件拼装
    		SqlBuilder sql = new SqlBuilder();
    	
    		if (CyCommonUtils.isNotEmpty(restQuery.getAppointedman())) {
    			sql.append("and appointedman", restQuery.getAppointedman());
    		}
    		if (CyCommonUtils.isNotEmpty(restQuery.getBillmaker())) {
    			sql.append("and creator", restQuery.getBillmaker());
    		}
    		// 2、进行分页查询
    		@SuppressWarnings("rawtypes")
    		ResponseList queryListData = CyCommonUtils.getInstance(ICrmService.class).queryListPage(sql.toString(),
    				restQuery, AggTaskfeedbackHVO.class, TaskfeedbackInfoPOJO.class);
    		return queryListData;

    	}

    	/**
    	 * 星级评定(业绩申请)详情查询
    	 * 
    	 * @param pk  查询主键
    	 * @return
    	 * @throws BusinessException
    	 */
    	@Override
    	public TaskfeedbackAggPOJO queryDeatil(String pk) throws BusinessException {

    		if (CyCommonUtils.isEmpty(pk)) {
    			throw new BusinessException("主键不能为空");
    		}
    		// 1、查询NccAggvo
    		AggTaskfeedbackHVO aggvo = (AggTaskfeedbackHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(pk,
    				AggTaskfeedbackHVO.class);

    		// 2、将当前NccAggvo数据转换成接口实体数据
    		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(aggvo, TaskfeedbackAggPOJO.class, false);
    		TaskfeedbackAggPOJO workpojo = (TaskfeedbackAggPOJO) changeEntitySoPowerful.getRestObj();
    		return workpojo;

    	}

    	/**
    	 * 星级评定(业绩申请)更新
    	 * 
    	 * @param restYeji  保存VO
    	 * @return
    	 * @throws BusinessException
    	 */
    	@Override
    	public void billSave(RestTaskfeedbackSave restSave) throws BusinessException {
    		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restSave, AggTaskfeedbackHVO.class,
    				true);
    		AggTaskfeedbackHVO transformAggVO = (AggTaskfeedbackHVO) changeEntitySoPowerful.getAggvo();// 转换后得到的Aggvo
    		UserInfoByDiworkPOJO userinfo = changeEntitySoPowerful.getUserInfo();
    		AggTaskfeedbackHVO aggVO = null;
    		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
    			// 修改
    			aggVO = (AggTaskfeedbackHVO) CyCommonUtils.getInstance(ICrmService.class)
    					.queryDeatil(transformAggVO.getPrimaryKey(), AggTaskfeedbackHVO.class);
    			CrmUtil.editBillReplenishData(aggVO, transformAggVO, TaskfeedbackAggPOJO.class, userinfo.getUserMobile());
    		} else {
    			// 新增
    			aggVO = transformAggVO;
    			CrmUtil.addBillInitData(transformAggVO, userinfo.getUserMobile());
    		}
    		// 2、调用接口进行保存
    		CyCommonUtils.getInstance(ICrmService.class).pfSaveBusiAction(aggVO.getParentVO().getBilltype(), aggVO, false);
    	}
}
