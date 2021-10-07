
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.CreditcontractHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ICreditcontracthvoMaintain;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_SKHT_SAVE extends AbstractPfAction<AggCreditcontractHVO> {

        protected CompareAroundProcesser<AggCreditcontractHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggCreditcontractHVO> processor = new CompareAroundProcesser<AggCreditcontractHVO>(
                CreditcontractHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggCreditcontractHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggCreditcontractHVO[] processBP(Object userObj,
                        AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills) {
                ICreditcontracthvoMaintain operator = NCLocator.getInstance().lookup(
                                ICreditcontracthvoMaintain.class);
                AggCreditcontractHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
