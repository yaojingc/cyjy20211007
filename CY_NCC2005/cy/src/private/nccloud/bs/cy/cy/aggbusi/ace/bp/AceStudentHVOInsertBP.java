
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.StudentHVOPluginPoint;
import nccloud.impl.rule.StudentBillRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.studentfile.AggStudentHVO;

/**
 * 标准单据新增BP
 */
public class AceStudentHVOInsertBP {

	public AggStudentHVO[] insert(AggStudentHVO[] bills) {

		InsertBPTemplate<AggStudentHVO> bp = new InsertBPTemplate<AggStudentHVO>(StudentHVOPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * 新增后规则
	 *
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggStudentHVO> processor) {
		IRule<AggStudentHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("XSDA");
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
	private void addBeforeRule(AroundProcesser<AggStudentHVO> processer) {
		IRule<AggStudentHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
		rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("XSDA");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCodeItem("bill_no");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
		// 新增修改单据校验
		StudentBillRule<AggStudentHVO> stuRule = new StudentBillRule<AggStudentHVO>();
		processer.addBeforeRule(stuRule);
	}
}
