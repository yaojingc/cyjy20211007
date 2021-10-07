
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.SchoolformHVOPluginPoint;
import nccloud.impl.rule.SchoolformRule;
import nccloud.impl.rule.StuMarkRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.cy.schoolform.AggSchoolformHVO;
import nc.vo.cy.stumarkdata.AggStumarkdataHVO;

/**
 * 标准单据新增BP
 */
public class AceSchoolformHVOInsertBP {

	public AggSchoolformHVO[] insert(AggSchoolformHVO[] bills) {

		InsertBPTemplate<AggSchoolformHVO> bp = new InsertBPTemplate<AggSchoolformHVO>(SchoolformHVOPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * 新增后规则
	 *
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggSchoolformHVO> processor) {
		IRule<AggSchoolformHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("XXSQ");
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
	private void addBeforeRule(AroundProcesser<AggSchoolformHVO> processer) {
		IRule<AggSchoolformHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
		rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("XXSQ");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCodeItem("bill_no");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
		SchoolformRule<AggSchoolformHVO> formrule = new SchoolformRule<AggSchoolformHVO>();
		processer.addBeforeRule(formrule);
	}
}
