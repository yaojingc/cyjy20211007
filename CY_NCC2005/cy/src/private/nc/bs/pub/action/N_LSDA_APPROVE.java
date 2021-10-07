
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TeacherfileHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ITeacherfilehvoMaintain;
import nc.vo.cy.teacherfile.AggTeacherfileHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_LSDA_APPROVE extends AbstractPfAction<AggTeacherfileHVO> {

        public N_LSDA_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggTeacherfileHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTeacherfileHVO> processor = new CompareAroundProcesser<AggTeacherfileHVO>(
                                TeacherfileHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggTeacherfileHVO[] processBP(Object userObj,
                        AggTeacherfileHVO[] clientFullVOs, AggTeacherfileHVO[] originBills) {
                AggTeacherfileHVO[] bills = null;
                ITeacherfilehvoMaintain operator = NCLocator.getInstance().lookup(
                                ITeacherfilehvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
