
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ClasscyHVOPluginPoint;
import nccloud.impl.rule.ClasscyBillRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.cy.classfilecy.AggClasscyHVO;

/**
 * 标准单据新增BP
 */
public class AceClasscyHVOInsertBP {

	public AggClasscyHVO[] insert(AggClasscyHVO[] bills) {

		InsertBPTemplate<AggClasscyHVO> bp = new InsertBPTemplate<AggClasscyHVO>(ClasscyHVOPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * 新增后规则
	 *
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggClasscyHVO> processor) {
		IRule<AggClasscyHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("CYBJ");
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
	private void addBeforeRule(AroundProcesser<AggClasscyHVO> processer) {
		IRule<AggClasscyHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
		rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("CYBJ");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCodeItem("bill_no");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
		// 新增修改单据校验
		ClasscyBillRule<AggClasscyHVO> crule = new ClasscyBillRule<AggClasscyHVO>();
		processer.addBeforeRule(crule);
	}
}
