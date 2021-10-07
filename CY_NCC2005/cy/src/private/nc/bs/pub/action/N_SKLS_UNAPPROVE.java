
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.BankcollflowHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IBankcollflowhvoMaintain;
import nc.vo.cy.bankcollflow.AggBankcollflowHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_SKLS_UNAPPROVE extends AbstractPfAction<AggBankcollflowHVO> {

        @Override
        protected CompareAroundProcesser<AggBankcollflowHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggBankcollflowHVO> processor = new CompareAroundProcesser<AggBankcollflowHVO>(
                                BankcollflowHVOPluginPoint.UNAPPROVE);
                // TODO 在此处添加前后规则
                processor.addBeforeRule(new UnapproveStatusCheckRule());

                return processor;
        }

        @Override
        protected AggBankcollflowHVO[] processBP(Object userObj,
                        AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills) {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                AggBankcollflowHVO[] bills = null;
                try {
                        IBankcollflowhvoMaintain operator = NCLocator.getInstance()
                                        .lookup(IBankcollflowhvoMaintain.class);
                        bills = operator.unapprove(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
