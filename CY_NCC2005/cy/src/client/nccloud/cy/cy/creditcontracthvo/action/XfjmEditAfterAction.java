package nccloud.cy.cy.creditcontracthvo.action;

import nc.bs.logging.Logger;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;
import nccloud.itf.cy.cy.ICreditcontracthvoMaintain;


/**
 * 
 * 选择学费减免参照时的编辑后事件，带出金额
 * 
 * @author yao
 */
public class XfjmEditAfterAction implements ICommonAction  {

	@Override
	public Object doAction(IRequest request) {
		BillCardOperator billCardOperator = new BillCardOperator();
		// 1、获取AGGVO （request转换主子VO）
		AggCreditcontractHVO vo = billCardOperator.toBill(request);
		
		try {
			CyCommonUtils.getInstance(ICreditcontracthvoMaintain.class).contractModifyWithAr(vo);
			
			
			
		} catch (BusinessException e) {
			// 处理异常信息
			Logger.error(e);
			ExceptionUtils.wrapException(e);
		}
		
		return null;
	}

}
