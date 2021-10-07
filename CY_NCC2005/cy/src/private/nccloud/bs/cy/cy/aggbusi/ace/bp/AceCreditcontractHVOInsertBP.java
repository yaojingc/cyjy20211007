
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.CreditcontractHVOPluginPoint;
import nccloud.impl.rule.CreditcontractRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;

/**
 * 标准单据新增BP
 */
public class AceCreditcontractHVOInsertBP {

	public AggCreditcontractHVO[] insert(AggCreditcontractHVO[] bills) {

		InsertBPTemplate<AggCreditcontractHVO> bp = new InsertBPTemplate<AggCreditcontractHVO>(
				CreditcontractHVOPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * 新增后规则
	 *
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggCreditcontractHVO> processor) {
		IRule<AggCreditcontractHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("SKHT");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCodeItem("bill_no");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processor.addAfterRule(rule);
	}

	/**
	 * 新增前规则
	 *
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggCreditcontractHVO> processer) {
		IRule<AggCreditcontractHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
		rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("SKHT");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCodeItem("bill_no");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
		// 单据校验
		CreditcontractRule<AggCreditcontractHVO> ccRule = new CreditcontractRule<AggCreditcontractHVO>();
		processer.addBeforeRule(ccRule);
		
	}
}
