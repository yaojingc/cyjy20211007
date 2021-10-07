
package nccloud.bs.cy.cy.aggbusi.ace.bp;


import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ClsscheduleHVOPluginPoint;
import nc.vo.cy.clsschedule.AggClsscheduleHVO;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceClsscheduleHVODeleteBP {

        public void delete(AggClsscheduleHVO[] bills) {

                DeleteBPTemplate<AggClsscheduleHVO> bp = new DeleteBPTemplate<AggClsscheduleHVO>(
ClsscheduleHVOPluginPoint.DELETE);
                // 增加执行前规则
                this.addBeforeRule(bp.getAroundProcesser());
                // 增加执行后业务规则
                this.addAfterRule(bp.getAroundProcesser());
                bp.delete(bills);
        }

        private void addBeforeRule(AroundProcesser<AggClsscheduleHVO> processer) {
                // TODO 前规则
//              IRule<AggClsscheduleHVO> rule = null;
//              rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
//              processer.addBeforeRule(rule);
        }

        /**
         * 删除后业务规则
         *
         * @param processer
         */
        private void addAfterRule(AroundProcesser<AggClsscheduleHVO> processer) {
                // TODO 后规则

        }
}
