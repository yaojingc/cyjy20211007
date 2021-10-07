
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TaskfeedbackHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ITaskfeedbackhvoMaintain;
import nc.vo.cy.taskfeedback.AggTaskfeedbackHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_RWFK_APPROVE extends AbstractPfAction<AggTaskfeedbackHVO> {

        public N_RWFK_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggTaskfeedbackHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTaskfeedbackHVO> processor = new CompareAroundProcesser<AggTaskfeedbackHVO>(
                                TaskfeedbackHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggTaskfeedbackHVO[] processBP(Object userObj,
                        AggTaskfeedbackHVO[] clientFullVOs, AggTaskfeedbackHVO[] originBills) {
                AggTaskfeedbackHVO[] bills = null;
                ITaskfeedbackhvoMaintain operator = NCLocator.getInstance().lookup(
                                ITaskfeedbackhvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
