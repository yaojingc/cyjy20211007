
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TaskfeedbackHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ITaskfeedbackhvoMaintain;
import nc.vo.cy.taskfeedback.AggTaskfeedbackHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_RWFK_DELETE extends AbstractPfAction<AggTaskfeedbackHVO> {

        @Override
        protected CompareAroundProcesser<AggTaskfeedbackHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTaskfeedbackHVO> processor = new CompareAroundProcesser<AggTaskfeedbackHVO>(
                                TaskfeedbackHVOPluginPoint.SCRIPT_DELETE);
                // TODO 在此处添加前后规则
                return processor;
        }

        @Override
        protected AggTaskfeedbackHVO[] processBP(Object userObj,
                        AggTaskfeedbackHVO[] clientFullVOs, AggTaskfeedbackHVO[] originBills) {
                ITaskfeedbackhvoMaintain operator = NCLocator.getInstance().lookup(
                                ITaskfeedbackhvoMaintain.class);
                try {
                        operator.delete(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return clientFullVOs;
        }

}
