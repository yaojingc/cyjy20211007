
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.CreditcontractHVOPluginPoint;
import nccloud.impl.rule.CreditcontractRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;

/**
 * 修改保存的BP
 *
 */
public class AceCreditcontractHVOUpdateBP {

	public AggCreditcontractHVO[] update(AggCreditcontractHVO[] bills, AggCreditcontractHVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggCreditcontractHVO> bp = new UpdateBPTemplate<AggCreditcontractHVO>(
				CreditcontractHVOPluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggCreditcontractHVO> processer) {
		// TODO 后规则
		IRule<AggCreditcontractHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("SKHT");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCodeItem("bill_no");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processer.addAfterRule(rule);

	}

	private void addBeforeRule(CompareAroundProcesser<AggCreditcontractHVO> processer) {
		// TODO 前规则
		IRule<AggCreditcontractHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
		nc.impl.pubapp.pattern.rule.ICompareRule<AggCreditcontractHVO> ruleCom = new nc.bs.pubapp.pub.rule.UpdateBillCodeRule();
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom).setCbilltype("SKHT");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom).setCodeItem("bill_no");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom).setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom).setOrgItem("pk_org");
		processer.addBeforeRule(ruleCom);

		// 单据校验
		CreditcontractRule<AggCreditcontractHVO> ccRule = new CreditcontractRule<AggCreditcontractHVO>();
		processer.addBeforeRule(ccRule);
	}

}
