
package nccloud.bs.cy.cy.aggbusi.ace.bp;


import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.XuefeijmHVOPluginPoint;
import nc.vo.cy.xuefeiapply.AggXuefeijmHVO;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceXuefeijmHVODeleteBP {

        public void delete(AggXuefeijmHVO[] bills) {

                DeleteBPTemplate<AggXuefeijmHVO> bp = new DeleteBPTemplate<AggXuefeijmHVO>(
XuefeijmHVOPluginPoint.DELETE);
                // 增加执行前规则
                this.addBeforeRule(bp.getAroundProcesser());
                // 增加执行后业务规则
                this.addAfterRule(bp.getAroundProcesser());
                bp.delete(bills);
        }

        private void addBeforeRule(AroundProcesser<AggXuefeijmHVO> processer) {
                // TODO 前规则
//              IRule<AggXuefeijmHVO> rule = null;
//              rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
//              processer.addBeforeRule(rule);
        }

        /**
         * 删除后业务规则
         *
         * @param processer
         */
        private void addAfterRule(AroundProcesser<AggXuefeijmHVO> processer) {
                // TODO 后规则

        }
}
