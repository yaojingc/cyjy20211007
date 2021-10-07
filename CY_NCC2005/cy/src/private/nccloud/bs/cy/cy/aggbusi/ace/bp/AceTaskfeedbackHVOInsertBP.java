
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TaskfeedbackHVOPluginPoint;
import nccloud.impl.rule.TaskRule;
import nccloud.impl.rule.TaskfeedbackRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.vo.cy.taskfeedback.AggTaskfeedbackHVO;

/**
 * 标准单据新增BP
 */
public class AceTaskfeedbackHVOInsertBP {

        public AggTaskfeedbackHVO[] insert(AggTaskfeedbackHVO[] bills) {

                InsertBPTemplate<AggTaskfeedbackHVO> bp = new InsertBPTemplate<AggTaskfeedbackHVO>(
TaskfeedbackHVOPluginPoint.INSERT);
                this.addBeforeRule(bp.getAroundProcesser());
                this.addAfterRule(bp.getAroundProcesser());
                return bp.insert(bills);

        }

        /**
         * 新增后规则
         *
         * @param processor
         */
        private void addAfterRule(AroundProcesser<AggTaskfeedbackHVO> processor) {
                IRule<AggTaskfeedbackHVO> rule = null;
                rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
                ((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("RWFK");
                ((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
                                .setCodeItem("bill_no");
                ((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
                                .setGroupItem("pk_group");
                ((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
                processor.addAfterRule(rule);
        }

        /**
         * 新增前规则
         *
         * @param processor
         */
        private void addBeforeRule(AroundProcesser<AggTaskfeedbackHVO> processer) {
                IRule<AggTaskfeedbackHVO> rule = null;
                rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
                processer.addBeforeRule(rule);
                rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("RWFK");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
                                .setCodeItem("bill_no");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
                                .setGroupItem("pk_group");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
                processer.addBeforeRule(rule);
                TaskfeedbackRule<AggTaskfeedbackHVO> trule = new TaskfeedbackRule<AggTaskfeedbackHVO>();
                processer.addBeforeRule(trule);//添加对应的规则类，进行对应的业务逻辑处理

        }
}
