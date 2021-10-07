
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ReturncostHVOPluginPoint;
import nccloud.impl.rule.CheckReturnBillIsUnApproveRule;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IReturncosthvoMaintain;
import nc.vo.cy.returncost.AggReturncostHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_TFSQ_UNAPPROVE extends AbstractPfAction<AggReturncostHVO> {

        @Override
        protected CompareAroundProcesser<AggReturncostHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggReturncostHVO> processor = new CompareAroundProcesser<AggReturncostHVO>(
                                ReturncostHVOPluginPoint.UNAPPROVE);
                // TODO 在此处添加前后规则
                processor.addBeforeRule(new UnapproveStatusCheckRule());
                processor.addBeforeRule(new CheckReturnBillIsUnApproveRule());//add by csh
                return processor;
        }

        @Override
        protected AggReturncostHVO[] processBP(Object userObj,
                        AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills) {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                AggReturncostHVO[] bills = null;
                try {
                        IReturncosthvoMaintain operator = NCLocator.getInstance()
                                        .lookup(IReturncosthvoMaintain.class);
                        bills = operator.unapprove(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
