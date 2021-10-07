
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TeacherfileHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ITeacherfilehvoMaintain;
import nc.vo.cy.teacherfile.AggTeacherfileHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_LSDA_DELETE extends AbstractPfAction<AggTeacherfileHVO> {

        @Override
        protected CompareAroundProcesser<AggTeacherfileHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTeacherfileHVO> processor = new CompareAroundProcesser<AggTeacherfileHVO>(
                                TeacherfileHVOPluginPoint.SCRIPT_DELETE);
                // TODO 在此处添加前后规则
                return processor;
        }

        @Override
        protected AggTeacherfileHVO[] processBP(Object userObj,
                        AggTeacherfileHVO[] clientFullVOs, AggTeacherfileHVO[] originBills) {
                ITeacherfilehvoMaintain operator = NCLocator.getInstance().lookup(
                                ITeacherfilehvoMaintain.class);
                try {
                        operator.delete(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return clientFullVOs;
        }

}
