
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.StumarkdataHVOPluginPoint;
import nccloud.impl.rule.StuMarkRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.cy.stumarkdata.AggStumarkdataHVO;

/**
 * 标准单据新增BP
 */
public class AceStumarkdataHVOInsertBP {

	public AggStumarkdataHVO[] insert(AggStumarkdataHVO[] bills) {

		InsertBPTemplate<AggStumarkdataHVO> bp = new InsertBPTemplate<AggStumarkdataHVO>(
				StumarkdataHVOPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * 新增后规则
	 *
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggStumarkdataHVO> processor) {
		IRule<AggStumarkdataHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("XSCJ");
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
	private void addBeforeRule(AroundProcesser<AggStumarkdataHVO> processer) {
		IRule<AggStumarkdataHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
		rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("XSCJ");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCodeItem("bill_no");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
		StuMarkRule<AggStumarkdataHVO> markrule = new StuMarkRule<AggStumarkdataHVO>();
		processer.addBeforeRule(markrule);
	}
}
