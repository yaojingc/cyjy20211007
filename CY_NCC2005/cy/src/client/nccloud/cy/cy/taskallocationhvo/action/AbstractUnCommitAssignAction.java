
package nccloud.cy.cy.taskallocationhvo.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import nc.bs.logging.Logger;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nccloud.vo.cy.cy.TaskallocationHVOConst;
import nc.vo.pf.pub.util.SQLUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.uap.pf.PfProcessBatchRetObject;
import nccloud.base.exception.ExceptionUtils;
import nccloud.framework.core.json.IJson;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;
import nccloud.pubitf.riart.pflow.CloudPFlowContext;
import nccloud.pubitf.riart.pflow.ICloudScriptPFlowService;
import nccloud.cy.cy.taskallocationhvo.cy.bean.BillOperatorParam;
import nccloud.cy.cy.taskallocationhvo.cy.util.CommonUtil;
import nccloud.web.workflow.approve.util.NCCFlowUtils;

public abstract class AbstractUnCommitAssignAction<T extends AbstractBill>
        implements ICommonAction {

  public Object callAutoUnApproveActionScript(AggTaskallocationHVO[] commitVOs,
                                              String billType) throws BusinessException {

    Map<Object, Object> extParam = new HashMap<Object, Object>();
    extParam.put("IS_RELOADBILL", true);

    // 调用收回即弃审接口
    CloudPFlowContext context = new CloudPFlowContext();
    context.seteParam(extParam);
    context.setActionName("UNSAVEBILL");
    context.setBillType(billType);
    context.setBillVos(commitVOs);

    Logger.debug("开始调用动作脚本 ActionName[" + "UNSAVEBILL" + "] BillType["
            + billType + "]...");
    ICloudScriptPFlowService service = CommonUtil.getCloudScriptPFlowService();
    // 收回即弃审
    Object[] result = service.exeScriptPFlow_UnSaveNoFlowBatch(context);

    if ((result == null) || (result.length == 0) || (result[0] == null)) {
      return null;
    }
    // 处理返回结果
    if (result[0] instanceof PfProcessBatchRetObject) {
      PfProcessBatchRetObject batchRetObject =
              (PfProcessBatchRetObject) result[0];
      String errMsg = batchRetObject.getExceptionMsg();
      // 没有异常信息则表明执行成功
      if ((errMsg != null) && !"".equals(errMsg)) {
        ExceptionUtils.wrapBusinessException(errMsg);
      }
      return batchRetObject.getRetObj();
    }
    Logger.debug(
            "调用动作脚本 ActionName[" + "UNSAVEBILL" + "] BillType[" + billType + "]结束");
    return result;
  }

  protected Object buildFontResult(BillOperatorParam operaParam,
                                   Object result) {
    AbstractBill[] resultVOs = (AbstractBill[]) result;
    if (resultVOs.length == 1) {
      BillCardOperator billCardOperator =
              new BillCardOperator(operaParam.getPageCode());
      BillCard card = billCardOperator.toCard(resultVOs[0]);
      return card;
    }
    return null;
  }

  protected Object callActionScript(AggTaskallocationHVO[] commitVOs,
                                    String billType) throws BusinessException {

    CloudPFlowContext context = new CloudPFlowContext();
    context.setActionName("UNSAVEBILL");
    context.setBillType(billType);
    context.setBillVos(commitVOs);

    Logger.debug("开始调用动作脚本 ActionName[" + "UNSAVEBILL" + "] BillType["
            + billType + "]...");
    ICloudScriptPFlowService service = CommonUtil.getCloudScriptPFlowService();
    Object[] result = service.exeScriptPFlow(context);
    if ((result == null) || (result.length == 0) || (result[0] == null)) {
      return null;
    }

    if (result[0] instanceof PfProcessBatchRetObject) {
      PfProcessBatchRetObject batchRetObject =
              (PfProcessBatchRetObject) result[0];
      String errMsg = batchRetObject.getExceptionMsg();
      if ((errMsg != null) && !"".equals(errMsg)) {
        ExceptionUtils.wrapBusinessException(errMsg);
      }
      return batchRetObject.getRetObj();
    }

    Logger.debug(
            "调用动作脚本 ActionName[" + "UNSAVEBILL" + "] BillType[" + billType + "]结束");
    return result;
  }

  /**
   * 获取前端请求参数
   *
   * @param request
   * @return
   */
  protected BillOperatorParam getRequestParam(IRequest request) {
    // 解析request
    String str = request.read();
    IJson json = JsonFactory.create();
    BillOperatorParam operaParam = json.fromJson(str, BillOperatorParam.class);
    return operaParam;
  }

  protected AggTaskallocationHVO[] queryBillsByPks(BillOperatorParam operaParam)
          throws BusinessException {
    AggTaskallocationHVO[] vos = null;
    String[] pks = operaParam.getPks();
    if (pks.length != 0) {
      // 根据参数查询结果
      IMDPersistenceQueryService service =
              CommonUtil.getMDPersistenceQueryService();
      String wheresql = SQLUtil
              .buildSqlForIn(TaskallocationHVOConst.CONST_PARENT_PRIMARYKEY, pks);
      Collection<AggTaskallocationHVO> bills = service.queryBillOfVOByCond(
              AggTaskallocationHVO.class, wheresql, true, false);
      if ((bills == null) || (bills.size() != pks.length)) {
        ExceptionUtils.wrapBusinessException("单据已被修改，请刷新页面后重试");
      }
      vos = bills.toArray(new AggTaskallocationHVO[0]);
    }
    return vos;
  }

  protected Object unCommit(AggTaskallocationHVO[] commitVOs)
          throws BusinessException {
    Object result = null;
    // 判断是否定义审批流
    boolean hasFlow = NCCFlowUtils.hasApproveflowDef(
            TaskallocationHVOConst.CONST_BILLTYPE_COST, commitVOs[0]);
    for (AggTaskallocationHVO vo : commitVOs) {
      Integer vbillstatus = (Integer) vo.getParent()
              .getAttributeValue(TaskallocationHVOConst.CONST_BILL_STATUS);
      if (hasFlow && (vbillstatus == BillStatusEnum.APPROVED.toIntValue())) {
        ExceptionUtils.wrapBusinessException("此单据有审批流，请先去审批中心 取消审批或者驳回后再进行操作");
      }
    }
    // 有，则走提交动作脚本
    if (hasFlow) {
      result = this.callActionScript(commitVOs,
              TaskallocationHVOConst.CONST_BILLTYPE_COST);
    }
    // 没有，则收回即弃审
    else {
      result = this.callAutoUnApproveActionScript(commitVOs,
              TaskallocationHVOConst.CONST_BILLTYPE_COST);
    }
    return result;
  }

}
