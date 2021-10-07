
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TaskfeedbackHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ITaskfeedbackhvoMaintain;
import nc.vo.cy.taskfeedback.AggTaskfeedbackHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_RWFK_SAVE extends AbstractPfAction<AggTaskfeedbackHVO> {

        protected CompareAroundProcesser<AggTaskfeedbackHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTaskfeedbackHVO> processor = new CompareAroundProcesser<AggTaskfeedbackHVO>(
                TaskfeedbackHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggTaskfeedbackHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggTaskfeedbackHVO[] processBP(Object userObj,
                        AggTaskfeedbackHVO[] clientFullVOs, AggTaskfeedbackHVO[] originBills) {
                ITaskfeedbackhvoMaintain operator = NCLocator.getInstance().lookup(
                                ITaskfeedbackhvoMaintain.class);
                AggTaskfeedbackHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
