
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.SalesmanfileHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ISalesmanfilehvoMaintain;
import nc.vo.cy.salesmanfile.AggSalesmanfileHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_YWDA_APPROVE extends AbstractPfAction<AggSalesmanfileHVO> {

        public N_YWDA_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggSalesmanfileHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggSalesmanfileHVO> processor = new CompareAroundProcesser<AggSalesmanfileHVO>(
                                SalesmanfileHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggSalesmanfileHVO[] processBP(Object userObj,
                        AggSalesmanfileHVO[] clientFullVOs, AggSalesmanfileHVO[] originBills) {
                AggSalesmanfileHVO[] bills = null;
                ISalesmanfilehvoMaintain operator = NCLocator.getInstance().lookup(
                                ISalesmanfilehvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
