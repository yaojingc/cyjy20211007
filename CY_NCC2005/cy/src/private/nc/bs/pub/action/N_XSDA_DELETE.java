
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.StudentHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IStudenthvoMaintain;
import nc.vo.cy.studentfile.AggStudentHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XSDA_DELETE extends AbstractPfAction<AggStudentHVO> {

        @Override
        protected CompareAroundProcesser<AggStudentHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggStudentHVO> processor = new CompareAroundProcesser<AggStudentHVO>(
                                StudentHVOPluginPoint.SCRIPT_DELETE);
                // TODO 在此处添加前后规则
                return processor;
        }

        @Override
        protected AggStudentHVO[] processBP(Object userObj,
                        AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills) {
                IStudenthvoMaintain operator = NCLocator.getInstance().lookup(
                                IStudenthvoMaintain.class);
                try {
                        operator.delete(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return clientFullVOs;
        }

}
