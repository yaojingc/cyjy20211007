
package nccloud.cy.cy.classituationhvo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nc.vo.cy.classituation.AggClassituationHVO;


import nc.bs.logging.Logger;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nccloud.vo.cy.cy.ClassituationHVOConst;
import nc.vo.pf.change.PfUtilBaseTools;
import nc.vo.pf.pub.util.SQLUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.CarrierRuntimeException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.uap.pf.PfProcessBatchRetObject;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.core.json.IJson;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;
import nccloud.pubitf.riart.pflow.CloudPFlowContext;
import nccloud.pubitf.riart.pflow.ICloudScriptPFlowService;
import nccloud.cy.cy.classituationhvo.cy.bean.BillOperatorParam;
import nccloud.cy.cy.classituationhvo.cy.bean.CommitReturnInfo;
import nccloud.cy.cy.classituationhvo.cy.bean.MultipleResult;
import nccloud.cy.cy.classituationhvo.cy.bean.SingleResult;
import nccloud.cy.cy.classituationhvo.cy.util.CommonUtil;
import nccloud.web.workflow.approve.util.NCCFlowUtils;

public abstract class AbstractCommitAssignAction<T extends AbstractBill>
        implements ICommonAction {

  /**
   * 从异常中获取错误信息，异常可能包了很多层，需要一直往上找具体错误信息
   *
   * @param e
   * @return
   */
  public String getExceptionMsg(Throwable e) {
    String errMsg = (e.getMessage() == null ? "" : e.getMessage());
    if ("".equals(errMsg)) {
      if (e instanceof BusinessException) {
        return "";
      }
      else if (e instanceof CarrierRuntimeException) {
        return e.getMessage();
      }
      else {
        if (e.getCause() != null) {
          return this.getExceptionMsg(e.getCause());
        }
        return "";
      }
    }
    return errMsg;
  }

  protected Object buildFontResult(BillOperatorParam operaParam,
                                   Object result) {
    // 指派处理
    if (result instanceof CommitReturnInfo) {
      CommitReturnInfo returnvo = (CommitReturnInfo) result;
      return returnvo.getReturnMsg();
    }
    AbstractBill[] resultVOs = (AbstractBill[]) result;
    resultVOs = (T[]) result;
    if (resultVOs.length == 1) {
      BillCardOperator billCardOperator =
              new BillCardOperator(operaParam.getPageCode());
      BillCard card = billCardOperator.toCard(resultVOs[0]);
      return card;
    }
    return null;
  }

  protected Object callActionScript(T[] aggVOs, Object content, String billType)
          throws BusinessException {

    CloudPFlowContext context = new CloudPFlowContext();
    context.setActionName("SAVE");
    context.setBillType(billType);
    context.setBillVos(aggVOs);
    // 指派信息不为空，则添加指派信息
    if (content != null) {
      Map<Object, Object> contentParam = new HashMap<>();
      contentParam.put("content", content);
      context.seteParam(contentParam);
    }

    Logger.debug(
            "开始调用动作脚本 ActionName[" + "SAVE" + "] BillType[" + billType + "]...");
    ICloudScriptPFlowService service = CommonUtil.getCloudScriptPFlowService();
    Object[] result = service.exeScriptPFlow(context);

    if ((result == null) || (result.length == 0) || (result[0] == null)) {
      return null;
    }

    // begin 单提交指派功能
    if (result[0] instanceof Map) {
      Map map = (Map) result[0];
      String type = String.valueOf(map.get(PfUtilBaseTools.PARAM_WORKFLOWTYPE));
      if (PfUtilBaseTools.PARAM_APPROVEFLOW.equals(type)
              || PfUtilBaseTools.PARAM_WORKFLOW.equals(type)) {
        CommitReturnInfo returnInfo = new CommitReturnInfo();
        returnInfo.setReturnMsg(map);
        returnInfo.setAssignFlag(true);
        return returnInfo;
      }
    }
    // end 单提交指派功能

    // 多提交异常
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
            "调用动作脚本 ActionName[" + "SAVE" + "] BillType[" + billType + "]结束");
    return result;
  }

  /**
   * 提交即审批
   *
   * @param aggVO
   * @return
   * @throws BusinessException
   */
  protected Object callAutoApproveActionScript(T[] aggVOs, String billType)
          throws BusinessException {

    Map<Object, Object> extParam = new HashMap<Object, Object>();
    extParam.put("IS_RELOADBILL", true);

    // 调用提交即审批接口
    CloudPFlowContext context = new CloudPFlowContext();
    context.seteParam(extParam);
    context.setActionName("SAVE");
    context.setBillType(billType);
    context.setBillVos(aggVOs);

    Logger.debug(
            "开始调用动作脚本 ActionName[" + "SAVE" + "] BillType[" + billType + "]...");
    ICloudScriptPFlowService service = CommonUtil.getCloudScriptPFlowService();
    Object[] result = service.exeScriptPFlow_CommitNoFlowBatch(context);

    if ((result == null) || (result.length == 0) || (result[0] == null)) {
      return null;
    }
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
            "调用动作脚本 ActionName[" + "SAVE" + "] BillType[" + billType + "]结束");
    return result;
  }

  /**
   * 单提交指派处理
   *
   * @param commitVOs
   * @param userobj 指派信息
   * @return
   * @throws BusinessException
   */
  protected Object commit(T vo, Object userobj, String billType)
          throws BusinessException {
    Object result = null;
    // 判断是否定义审批流
    boolean hasFlow = NCCFlowUtils.hasApproveflowDef(billType, vo);
    // 有，则走提交动作脚本
    if (hasFlow) {
      result = this.callActionScript((T[]) new AbstractBill[] {
              vo
      }, userobj, billType);
    }
    // 没有，则提交即审批
    else {
      result = this.callAutoApproveActionScript((T[]) new AbstractBill[] {
              vo
      }, billType);
    }
    return result;
  }

  /**
   * 处理批量单据提交
   *
   * @param vos
   * @return
   * @throws BusinessException
   */
  protected MultipleResult doBatchService(T[] aggVOs, Object userobj,
                                          String billType) throws BusinessException {
    // 处理总数
    int total = aggVOs.length;
    // 失败条数
    int errorNum = 0;
    // 成功条数
    int successNum = 0;
    // 是否有处理成功
    boolean hasSuccFlag = false;
    // 是否有处理失败
    boolean hasErrFlag = false;
    // 提交指派个数
    int assignNum = 0;
    // 单笔处理成功返回结果
    Object result;
    // 错误详情
    List<String> errMsgDetail = new ArrayList<String>();
    // 处理结果集合
    List<SingleResult> resultList = new ArrayList<>();
    // 处理收回
    for (T vo : aggVOs) {
      // 单笔处理结果
      SingleResult singleResult;

      try {
        // 批量中含有提交指派的单据暂不支持批量
        if (assignNum > 0) {
          break;
        }
        // 执行业务操作
        result = this.commit(vo, userobj, billType);
        // 判断是否是带有指派功能
        if (result instanceof CommitReturnInfo) {
          CommitReturnInfo returnInfo = (CommitReturnInfo) result;
          if (returnInfo.getAssignFlag() == true) {
            assignNum++;
            break;
          }
        }
        // 构建成功结果
        singleResult = SingleResult.buildSuccessResult(vo.getPrimaryKey(), null,
                null, null);
        // 构建成功的结果
        hasSuccFlag = true;
        // 成功条数+1
        successNum++;
      }
      catch (Exception e) {
        // 有失败
        hasErrFlag = true;
        // 失败条数+1
        errorNum++;
        // 获取异常信息
        String errMess = this.getExceptionMsg(e);
        errMsgDetail.add(errMess);
        Logger.error(e.getMessage(), e);
        // 构建失败结果
        singleResult = SingleResult.buildErrResult(vo.getPrimaryKey(), null,
                null, errMess);
      }
      // 汇总处理结果
      resultList.add(singleResult);
    }
    // 批量中含有提交指派的单据暂不支持批量
    if (assignNum > 0) {
      throw new BusinessException(
              "批量数据中含有提交指派的单据，不支持批量操作，请重新选择批量数据操作");/*@res 批量数据中含有提交指派的单据，不支持批量操作，请重新选择批量数据操作  */
    }
    // 构建批量操作结果
    MultipleResult multipleResult = MultipleResult.buildResult(hasSuccFlag,
            hasErrFlag, resultList, errMsgDetail, total, errorNum, successNum);
    return multipleResult;
  }

  /**
   * 批量提交指派处理
   *
   * @param commitVOs
   * @param userobj 指派信息
   * @return
   * @throws BusinessException
   */

  protected Object doCommit(T[] commitVOs, Object userobj, String billType)
          throws BusinessException {
    Object result = null;
    if ((commitVOs == null) || (commitVOs.length == 0)) {
      return commitVOs;
    }
    if (commitVOs.length < 2) {
      result = this.commit(commitVOs[0], userobj, billType);
    }
    else {
      return this.doBatchService(commitVOs, userobj, billType);
    }
    return result;
  }

  protected BillOperatorParam getRequestParam(IRequest request) {
    // 解析request
    String str = request.read();
    IJson json = JsonFactory.create();
    BillOperatorParam operaParam = json.fromJson(str, BillOperatorParam.class);
    return operaParam;
  }

  protected AggClassituationHVO[] queryBillsByPks(BillOperatorParam operaParam)
          throws BusinessException {
    AggClassituationHVO[] vos = null;
    String[] pks = operaParam.getPks();
    if (pks.length != 0) {
      // 根据参数查询结果
      IMDPersistenceQueryService service =
              CommonUtil.getMDPersistenceQueryService();
      String wheresql = SQLUtil
              .buildSqlForIn(ClassituationHVOConst.CONST_PARENT_PRIMARYKEY, pks);
      Collection<AggClassituationHVO> bills = service.queryBillOfVOByCond(
         AggClassituationHVO.class, wheresql, true, false);
      if ((bills == null) || (bills.size() != pks.length)) {
        ExceptionUtils.wrapBusinessException("单据已被修改，请刷新页面后重试");
      }
      vos = bills.toArray(new AggClassituationHVO[0]);
    }
    return vos;
  }

}
