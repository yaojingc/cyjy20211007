
package nccloud.cy.cy.clsschedulehvo.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.vo.cy.clsschedule.AggClsscheduleHVO;
import nc.vo.pub.lang.UFDate;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;
import nccloud.itf.cy.cy.IClsschedulehvoMaintain;

/**
 * 批量创建学生上课情况
 * rongleia
 * @version @since v3.5.6-1903
 */
public class CreatClaSituationAction implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		try {
			BillCardOperator billCardOperator = new BillCardOperator();
			// 1、获取AGGVO （request转换主子VO）
			AggClsscheduleHVO vo = billCardOperator.toBill(paramIRequest);
			IClsschedulehvoMaintain maintain = NCLocator.getInstance().lookup(IClsschedulehvoMaintain.class);
			Boolean flag = maintain.creatSituation(vo);
			Object billcard = billCardOperator.toCard(vo);
			// 4、返回结果到前端
			return billcard;
		} catch (Exception ex) {
			// 处理异常信息
			Logger.error(ex);
			ExceptionUtils.wrapException(ex);
		}
		return null;
	}
}
