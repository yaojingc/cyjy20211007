
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.WorkplanHVOPluginPoint;
import nccloud.impl.rule.TaskRule;
import nccloud.impl.rule.WorkplanRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.vo.cy.workplan.AggWorkplanHVO;

/**
 * 标准单据新增BP
 */
public class AceWorkplanHVOInsertBP {

        public AggWorkplanHVO[] insert(AggWorkplanHVO[] bills) {

                InsertBPTemplate<AggWorkplanHVO> bp = new InsertBPTemplate<AggWorkplanHVO>(
WorkplanHVOPluginPoint.INSERT);
                this.addBeforeRule(bp.getAroundProcesser());
                this.addAfterRule(bp.getAroundProcesser());
                return bp.insert(bills);

        }

        /**
         * 新增后规则
         *
         * @param processor
         */
        private void addAfterRule(AroundProcesser<AggWorkplanHVO> processor) {
                IRule<AggWorkplanHVO> rule = null;
                rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
                ((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("GZJH");
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
        private void addBeforeRule(AroundProcesser<AggWorkplanHVO> processer) {
                IRule<AggWorkplanHVO> rule = null;
                rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
                processer.addBeforeRule(rule);
                rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("GZJH");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
                                .setCodeItem("bill_no");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
                                .setGroupItem("pk_group");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
                processer.addBeforeRule(rule);
                // 新增修改单据校验
                WorkplanRule<AggWorkplanHVO> wrule = new WorkplanRule<AggWorkplanHVO>();
                processer.addBeforeRule(wrule);//添加对应的规则类，进行对应的业务逻辑处理
        }
}
