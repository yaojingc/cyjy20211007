
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.StudentHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IStudenthvoMaintain;
import nc.vo.cy.studentfile.AggStudentHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XSDA_APPROVE extends AbstractPfAction<AggStudentHVO> {

        public N_XSDA_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggStudentHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggStudentHVO> processor = new CompareAroundProcesser<AggStudentHVO>(
                                StudentHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggStudentHVO[] processBP(Object userObj,
                        AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills) {
                AggStudentHVO[] bills = null;
                IStudenthvoMaintain operator = NCLocator.getInstance().lookup(
                                IStudenthvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
