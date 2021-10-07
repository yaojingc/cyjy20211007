
package nccloud.bs.cy.cy.aggbusi.ace.bp;


import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.SalesmanfileHVOPluginPoint;
import nc.vo.cy.salesmanfile.AggSalesmanfileHVO;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceSalesmanfileHVODeleteBP {

        public void delete(AggSalesmanfileHVO[] bills) {

                DeleteBPTemplate<AggSalesmanfileHVO> bp = new DeleteBPTemplate<AggSalesmanfileHVO>(
SalesmanfileHVOPluginPoint.DELETE);
                // 增加执行前规则
                this.addBeforeRule(bp.getAroundProcesser());
                // 增加执行后业务规则
                this.addAfterRule(bp.getAroundProcesser());
                bp.delete(bills);
        }

        private void addBeforeRule(AroundProcesser<AggSalesmanfileHVO> processer) {
                // TODO 前规则
//              IRule<AggSalesmanfileHVO> rule = null;
//              rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
//              processer.addBeforeRule(rule);
        }

        /**
         * 删除后业务规则
         *
         * @param processer
         */
        private void addAfterRule(AroundProcesser<AggSalesmanfileHVO> processer) {
                // TODO 后规则

        }
}
