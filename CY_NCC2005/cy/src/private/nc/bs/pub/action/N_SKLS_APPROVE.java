
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.BankcollflowHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IBankcollflowhvoMaintain;
import nc.vo.cy.bankcollflow.AggBankcollflowHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_SKLS_APPROVE extends AbstractPfAction<AggBankcollflowHVO> {

        public N_SKLS_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggBankcollflowHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggBankcollflowHVO> processor = new CompareAroundProcesser<AggBankcollflowHVO>(
                                BankcollflowHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggBankcollflowHVO[] processBP(Object userObj,
                        AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills) {
                AggBankcollflowHVO[] bills = null;
                IBankcollflowhvoMaintain operator = NCLocator.getInstance().lookup(
                                IBankcollflowhvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
