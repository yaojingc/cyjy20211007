
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.XuefeijmHVOPluginPoint;
import nccloud.impl.rule.TaskfeedbackRule;
import nccloud.impl.rule.XuefeiRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.cy.taskfeedback.AggTaskfeedbackHVO;
import nc.vo.cy.xuefeiapply.AggXuefeijmHVO;

/**
 * 标准单据新增BP
 */
public class AceXuefeijmHVOInsertBP {

        public AggXuefeijmHVO[] insert(AggXuefeijmHVO[] bills) {

                InsertBPTemplate<AggXuefeijmHVO> bp = new InsertBPTemplate<AggXuefeijmHVO>(
XuefeijmHVOPluginPoint.INSERT);
                this.addBeforeRule(bp.getAroundProcesser());
                this.addAfterRule(bp.getAroundProcesser());
                return bp.insert(bills);

        }

        /**
         * 新增后规则
         *
         * @param processor
         */
        private void addAfterRule(AroundProcesser<AggXuefeijmHVO> processor) {
                IRule<AggXuefeijmHVO> rule = null;
                rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
                ((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("XFJM");
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
        private void addBeforeRule(AroundProcesser<AggXuefeijmHVO> processer) {
                IRule<AggXuefeijmHVO> rule = null;
                rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
                processer.addBeforeRule(rule);
                rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("XFJM");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
                                .setCodeItem("bill_no");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
                                .setGroupItem("pk_group");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
                processer.addBeforeRule(rule);
                XuefeiRule<AggXuefeijmHVO> trule = new XuefeiRule<AggXuefeijmHVO>();
                processer.addBeforeRule(trule);//添加对应的规则类，进行对应的业务逻辑处理

        }
}
