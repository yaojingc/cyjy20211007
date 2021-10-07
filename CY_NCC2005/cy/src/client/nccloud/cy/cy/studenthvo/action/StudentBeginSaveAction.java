
package nccloud.cy.cy.studenthvo.action;

import nc.bs.logging.Logger;
import nc.vo.cy.studentfile.AggStudentHVO;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCard;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCardOperator;

/**
 * 批量创建学生上课情况
 * rongleia
 * @version @since v3.5.6-1903
 */
public class StudentBeginSaveAction implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		try {
			ExtBillCardOperator extbillCardOperator = new ExtBillCardOperator();
			// 1、获取AGGVO （request转换主子VO）
			AggStudentHVO vo = extbillCardOperator.toBill(paramIRequest);
			ExtBillCard billcard = extbillCardOperator.toCard(vo);
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
