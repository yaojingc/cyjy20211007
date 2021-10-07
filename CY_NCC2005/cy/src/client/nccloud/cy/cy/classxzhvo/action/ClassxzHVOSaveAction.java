
package nccloud.cy.cy.classxzhvo.action;

import java.util.Collection;

import nc.bs.logging.Logger;
import nc.vo.cy.classfilexz.AggClassxzHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nccloud.cy.cy.classxzhvo.cy.util.CommonUtil;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;
import nccloud.pubitf.riart.pflow.CloudPFlowContext;
import nccloud.pubitf.riart.pflow.ICloudScriptPFlowService;
import nccloud.vo.cy.cy.ClassxzHVOConst;

/**
 * 主子表保存
 * 
 * @version @since v3.5.6-1903
 */
public class ClassxzHVOSaveAction implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		try {
			BillCardOperator billCardOperator = new BillCardOperator();
			// 1、获取AGGVO （request转换主子VO）
			AggClassxzHVO vo = billCardOperator.toBill(paramIRequest);
			this.doBefore(vo);
			// 2、调用单据的保存动作脚本（savebase），得到保存后结果
			AggClassxzHVO rtnObj = this.callActionScript(vo);
			// 3、处理返回结果（包含功能：根据模板转换前端BillCard，参照翻译，显示公式处理）
			BillCard billcard = billCardOperator.toCard(rtnObj);
			// 4、返回结果到前端
			return CommonUtil.displayCardManager(billcard, rtnObj);
		} catch (BusinessException ex) {
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
		return ClassxzHVOConst.CONST_ACTION_SAVEBASE;
	}

	/**
	 * 调用动作脚本
	 *
	 * @param actionCode
	 * @param aggVOs
	 * @return
	 * @throws BusinessException
	 */
	private AggClassxzHVO callActionScript(AggClassxzHVO... aggVOs) throws BusinessException {

		String actionCode = this.getActionCode();
		String billType = ClassxzHVOConst.CONST_BILLTYPE_COST;

		CloudPFlowContext context = new CloudPFlowContext();
		context.setActionName(actionCode);
		context.setBillType(billType);
		context.setBillVos(aggVOs);
		Logger.debug("开始调用动作脚本 ActionName[" + actionCode + "] BillType[" + billType + "]...");

		ICloudScriptPFlowService service = CommonUtil.getCloudScriptPFlowService();

		Object[] result = service.exeScriptPFlow(context);

		Logger.debug("调用动作脚本 ActionName[" + actionCode + "] BillType[" + billType + "]结束");

		String wheresql = "pk_classxz" + "='" + ((AggClassxzHVO) result[0]).getPrimaryKey() + "'";
		Collection<AggClassxzHVO> bills = CommonUtil.getMDPersistenceQueryService()
				.queryBillOfVOByCond(AggClassxzHVO.class, wheresql, true, false);
		return bills.toArray(new AggClassxzHVO[0])[0];
	}

	/**
	 * 判断新增或修改
	 *
	 * @param vo
	 */
	private void doBefore(AggClassxzHVO vo) {
		String parentPk = vo.getPrimaryKey();
		// 根据是否有主键信息判断是新增保存还是修改保存
		if ((parentPk != null) && !"".equals(parentPk)) {
			// 设置单据默认值
			CircularlyAccessibleValueObject[] allchildren = vo.getAllChildrenVO();
			if (allchildren != null) {
				for (CircularlyAccessibleValueObject obj : allchildren) {
					obj.setAttributeValue("pk_classxz", parentPk);
				}
			}
		}
	}
}
