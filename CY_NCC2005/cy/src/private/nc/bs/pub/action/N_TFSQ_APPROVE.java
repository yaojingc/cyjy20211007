
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ReturncostHVOPluginPoint;
import nccloud.impl.rule.CheckReturnBillIsUnApproveRule;
import nccloud.impl.rule.CheckReturnMoneyIsSetRule;
import nccloud.impl.rule.ReturnBillApprovePassRule;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IReturncosthvoMaintain;
import nc.vo.cy.returncost.AggReturncostHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_TFSQ_APPROVE extends AbstractPfAction<AggReturncostHVO> {

        public N_TFSQ_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggReturncostHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggReturncostHVO> processor = new CompareAroundProcesser<AggReturncostHVO>(
                                ReturncostHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                processor.addBeforeRule(new CheckReturnMoneyIsSetRule());//add by csh
                processor.addAfterRule(new ReturnBillApprovePassRule());//add by csh
                return processor;
        }

        @Override
        protected AggReturncostHVO[] processBP(Object userObj,
                        AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills) {
                AggReturncostHVO[] bills = null;
                IReturncosthvoMaintain operator = NCLocator.getInstance().lookup(
                                IReturncosthvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
