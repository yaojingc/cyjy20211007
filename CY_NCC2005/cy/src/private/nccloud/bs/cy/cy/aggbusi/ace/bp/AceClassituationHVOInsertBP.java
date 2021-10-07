
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ClassituationHVOPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.cy.classituation.AggClassituationHVO;

/**
 * 标准单据新增BP
 */
public class AceClassituationHVOInsertBP {

        public AggClassituationHVO[] insert(AggClassituationHVO[] bills) {

                InsertBPTemplate<AggClassituationHVO> bp = new InsertBPTemplate<AggClassituationHVO>(
ClassituationHVOPluginPoint.INSERT);
                this.addBeforeRule(bp.getAroundProcesser());
                this.addAfterRule(bp.getAroundProcesser());
                return bp.insert(bills);

        }

        /**
         * 新增后规则
         *
         * @param processor
         */
        private void addAfterRule(AroundProcesser<AggClassituationHVO> processor) {
                IRule<AggClassituationHVO> rule = null;
                rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
                ((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("SKQK");
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
        private void addBeforeRule(AroundProcesser<AggClassituationHVO> processer) {
                IRule<AggClassituationHVO> rule = null;
                rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
                processer.addBeforeRule(rule);
                rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("SKQK");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
                                .setCodeItem("bill_no");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
                                .setGroupItem("pk_group");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
                processer.addBeforeRule(rule);
        }
}
