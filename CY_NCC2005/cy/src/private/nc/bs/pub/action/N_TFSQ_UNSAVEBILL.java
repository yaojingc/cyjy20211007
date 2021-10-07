
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ReturncostHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IReturncosthvoMaintain;
import nc.vo.cy.returncost.AggReturncostHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_TFSQ_UNSAVEBILL extends AbstractPfAction<AggReturncostHVO> {

        @Override
        protected CompareAroundProcesser<AggReturncostHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggReturncostHVO> processor = new CompareAroundProcesser<AggReturncostHVO>(
                                ReturncostHVOPluginPoint.UNSEND_APPROVE);
                // TODO 在此处添加前后规则
                //processor.addBeforeRule(new UncommitStatusCheckRule());

                return processor;
        }

        @Override
        protected AggReturncostHVO[] processBP(Object userObj,
                        AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills) {
                IReturncosthvoMaintain operator = NCLocator.getInstance().lookup(
                                IReturncosthvoMaintain.class);
                AggReturncostHVO[] bills = null;
                try {
                        bills = operator.unsave(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
