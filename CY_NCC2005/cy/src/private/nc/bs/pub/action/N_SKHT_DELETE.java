
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.CreditcontractHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ICreditcontracthvoMaintain;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_SKHT_DELETE extends AbstractPfAction<AggCreditcontractHVO> {

        @Override
        protected CompareAroundProcesser<AggCreditcontractHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggCreditcontractHVO> processor = new CompareAroundProcesser<AggCreditcontractHVO>(
                                CreditcontractHVOPluginPoint.SCRIPT_DELETE);
                // TODO 在此处添加前后规则
                return processor;
        }

        @Override
        protected AggCreditcontractHVO[] processBP(Object userObj,
                        AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills) {
                ICreditcontracthvoMaintain operator = NCLocator.getInstance().lookup(
                                ICreditcontracthvoMaintain.class);
                try {
                        operator.delete(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return clientFullVOs;
        }

}
