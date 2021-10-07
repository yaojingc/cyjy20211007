
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.BankcollflowHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IBankcollflowhvoMaintain;
import nc.vo.cy.bankcollflow.AggBankcollflowHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_SKLS_SAVE extends AbstractPfAction<AggBankcollflowHVO> {

        protected CompareAroundProcesser<AggBankcollflowHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggBankcollflowHVO> processor = new CompareAroundProcesser<AggBankcollflowHVO>(
                BankcollflowHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggBankcollflowHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggBankcollflowHVO[] processBP(Object userObj,
                        AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills) {
                IBankcollflowhvoMaintain operator = NCLocator.getInstance().lookup(
                                IBankcollflowhvoMaintain.class);
                AggBankcollflowHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
