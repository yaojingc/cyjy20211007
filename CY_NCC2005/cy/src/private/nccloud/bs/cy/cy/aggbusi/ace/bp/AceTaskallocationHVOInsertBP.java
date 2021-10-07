
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TaskallocationHVOPluginPoint;
import nccloud.impl.rule.ClasscyBillRule;
import nccloud.impl.rule.TaskRule;
import nccloud.impl.rule.TaskallocationRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;

/**
 * 标准单据新增BP
 */
public class AceTaskallocationHVOInsertBP {

        public AggTaskallocationHVO[] insert(AggTaskallocationHVO[] bills) {

                InsertBPTemplate<AggTaskallocationHVO> bp = new InsertBPTemplate<AggTaskallocationHVO>(
TaskallocationHVOPluginPoint.INSERT);
                this.addBeforeRule(bp.getAroundProcesser());
                this.addAfterRule(bp.getAroundProcesser());
                return bp.insert(bills);

        }

        /**
         * 新增后规则
         *
         * @param processor
         */
        private void addAfterRule(AroundProcesser<AggTaskallocationHVO> processor) {
                IRule<AggTaskallocationHVO> rule = null;
                rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
                ((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("RWFP");
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
        private void addBeforeRule(AroundProcesser<AggTaskallocationHVO> processer) {
                IRule<AggTaskallocationHVO> rule = null;
                rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
                processer.addBeforeRule(rule);
                rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("RWFP");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
                                .setCodeItem("bill_no");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
                                .setGroupItem("pk_group");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
                processer.addBeforeRule(rule);
                // 新增修改单据校验
                TaskallocationRule<AggTaskallocationHVO> trule = new TaskallocationRule<AggTaskallocationHVO>();
                processer.addBeforeRule(trule);//添加对应的规则类，进行对应的业务逻辑处理
        }
}
