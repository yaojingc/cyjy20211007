package nccloud.cy.cy.classcyhvo.action;

import nc.utils.crm.CrmUtil;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;

/**
 * 班级编码随机6位数
 * 
 * @author RL
 *
 */
public class SetBillCodeAction implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		try {
			BillCardOperator billCardOperator = new BillCardOperator();
			// 1、获取AGGVO （request转换主子VO）
			AggClasscyHVO vo = billCardOperator.toBill(paramIRequest);
			// 获取6位随机码
			String billno = CrmUtil.generateClassNUM();
			vo.getParentVO().setBill_no(billno);
			Object card = billCardOperator.toCard(vo);
			return card;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
