
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.StudentHVOPluginPoint;
import nccloud.impl.rule.StudentBillRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.cy.studentfile.AggStudentHVO;

/**
 * 修改保存的BP
 *
 */
public class AceStudentHVOUpdateBP {

	public AggStudentHVO[] update(AggStudentHVO[] bills, AggStudentHVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggStudentHVO> bp = new UpdateBPTemplate<AggStudentHVO>(StudentHVOPluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggStudentHVO> processer) {
		// TODO 后规则
		IRule<AggStudentHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("XSDA");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCodeItem("bill_no");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processer.addAfterRule(rule);

	}

	private void addBeforeRule(CompareAroundProcesser<AggStudentHVO> processer) {
		// TODO 前规则
		IRule<AggStudentHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
		nc.impl.pubapp.pattern.rule.ICompareRule<AggStudentHVO> ruleCom = new nc.bs.pubapp.pub.rule.UpdateBillCodeRule();
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom).setCbilltype("XSDA");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom).setCodeItem("bill_no");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom).setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom).setOrgItem("pk_org");
		processer.addBeforeRule(ruleCom);
		// 新增修改单据校验
		StudentBillRule<AggStudentHVO> stuRule = new StudentBillRule<AggStudentHVO>();
		processer.addBeforeRule(stuRule);
	}

}
