
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.CreditcontractHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ICreditcontracthvoMaintain;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_SKHT_APPROVE extends AbstractPfAction<AggCreditcontractHVO> {

        public N_SKHT_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggCreditcontractHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggCreditcontractHVO> processor = new CompareAroundProcesser<AggCreditcontractHVO>(
                                CreditcontractHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggCreditcontractHVO[] processBP(Object userObj,
                        AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills) {
                AggCreditcontractHVO[] bills = null;
                ICreditcontracthvoMaintain operator = NCLocator.getInstance().lookup(
                                ICreditcontracthvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
