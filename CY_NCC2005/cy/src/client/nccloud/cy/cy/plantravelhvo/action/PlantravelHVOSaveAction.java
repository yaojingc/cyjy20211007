
package nccloud.cy.cy.plantravelhvo.action;

import java.util.Collection;

import nc.bs.logging.Logger;
import nccloud.vo.cy.cy.PlantravelHVOConst;
import nc.vo.cy.plantravel.AggPlantravelHVO;
import nc.vo.cy.plantravel.PlantravelHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCardOperator;
import nccloud.pubitf.riart.pflow.CloudPFlowContext;
import nccloud.pubitf.riart.pflow.ICloudScriptPFlowService;
import nccloud.cy.cy.plantravelhvo.cy.util.CommonUtil;

/**
 * 主子表保存
 * @version  @since v3.5.6-1903
 */
public class PlantravelHVOSaveAction implements ICommonAction {

  @Override
  public Object doAction(IRequest paramIRequest) {
    try {
    	ExtBillCardOperator extbillCardOperator = new ExtBillCardOperator();// 多页签
      // 1、获取AGGVO （request转换主子VO）
      AggPlantravelHVO vo = extbillCardOperator.toBill(paramIRequest);
      this.doBefore(vo);
      // 2、调用单据的保存动作脚本（savebase），得到保存后结果
      AggPlantravelHVO rtnObj = this.callActionScript(vo);
      // 3、处理返回结果（包含功能：根据模板转换前端BillCard，参照翻译，显示公式处理）
      Object billcard = extbillCardOperator.toCard(rtnObj);
      // 4、返回结果到前端
      return billcard;
    }
    catch (BusinessException ex) {
      // 处理异常信息
      Logger.error(ex);
      ExceptionUtils.wrapException(ex);
    }
    return null;
  }

  /**
   * 动作编码
   *
   * @return
   */
  protected String getActionCode() {
    return PlantravelHVOConst.CONST_ACTION_SAVEBASE;
  }

  /**
   * 调用动作脚本
   *
   * @param actionCode
   * @param aggVOs
   * @return
   * @throws BusinessException
   */
  private AggPlantravelHVO callActionScript(AggPlantravelHVO... aggVOs)
      throws BusinessException {

    String actionCode = this.getActionCode();
    String billType = PlantravelHVOConst.CONST_BILLTYPE_COST;

    CloudPFlowContext context = new CloudPFlowContext();
    context.setActionName(actionCode);
    context.setBillType(billType);
    context.setBillVos(aggVOs);
    Logger.debug("开始调用动作脚本 ActionName[" + actionCode + "] BillType[" + billType
        + "]...");

    ICloudScriptPFlowService service =
        CommonUtil.getCloudScriptPFlowService();

    Object[] result = service.exeScriptPFlow(context);

    Logger.debug("调用动作脚本 ActionName[" + actionCode + "] BillType[" + billType
        + "]结束");

    String wheresql =
        "pk_plantravel" + "='"
            + ((AggPlantravelHVO) result[0]).getPrimaryKey() + "'";
    Collection<AggPlantravelHVO> bills =
        CommonUtil.getMDPersistenceQueryService().queryBillOfVOByCond(
            AggPlantravelHVO.class, wheresql, true, false);
    return bills.toArray(new AggPlantravelHVO[0])[0];
  }

  /**
   * 判断新增或修改
   *
   * @param vo
   */
  private void doBefore(AggPlantravelHVO vo) {
    String parentPk = vo.getPrimaryKey();
    // 根据是否有主键信息判断是新增保存还是修改保存
    if ((parentPk != null) && !"".equals(parentPk)) {
      // 设置单据默认值
      CircularlyAccessibleValueObject[] allchildren = vo.getAllChildrenVO();
      if (allchildren != null) {
        for (CircularlyAccessibleValueObject obj : allchildren) {
          obj.setAttributeValue("pk_plantravel", parentPk);
        }
      }
    }
  }
}
