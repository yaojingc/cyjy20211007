
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.BankcollflowHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IBankcollflowhvoMaintain;
import nc.vo.cy.bankcollflow.AggBankcollflowHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_SKLS_UNSAVEBILL extends AbstractPfAction<AggBankcollflowHVO> {

        @Override
        protected CompareAroundProcesser<AggBankcollflowHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggBankcollflowHVO> processor = new CompareAroundProcesser<AggBankcollflowHVO>(
                                BankcollflowHVOPluginPoint.UNSEND_APPROVE);
                // TODO 在此处添加前后规则
                //processor.addBeforeRule(new UncommitStatusCheckRule());

                return processor;
        }

        @Override
        protected AggBankcollflowHVO[] processBP(Object userObj,
                        AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills) {
                IBankcollflowhvoMaintain operator = NCLocator.getInstance().lookup(
                                IBankcollflowhvoMaintain.class);
                AggBankcollflowHVO[] bills = null;
                try {
                        bills = operator.unsave(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
