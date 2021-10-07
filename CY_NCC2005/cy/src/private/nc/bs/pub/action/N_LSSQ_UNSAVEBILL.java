
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TeacherformHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ITeacherformhvoMaintain;
import nc.vo.cy.teacherform.AggTeacherformHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_LSSQ_UNSAVEBILL extends AbstractPfAction<AggTeacherformHVO> {

        @Override
        protected CompareAroundProcesser<AggTeacherformHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTeacherformHVO> processor = new CompareAroundProcesser<AggTeacherformHVO>(
                                TeacherformHVOPluginPoint.UNSEND_APPROVE);
                // TODO 在此处添加前后规则
                //processor.addBeforeRule(new UncommitStatusCheckRule());

                return processor;
        }

        @Override
        protected AggTeacherformHVO[] processBP(Object userObj,
                        AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills) {
                ITeacherformhvoMaintain operator = NCLocator.getInstance().lookup(
                                ITeacherformhvoMaintain.class);
                AggTeacherformHVO[] bills = null;
                try {
                        bills = operator.unsave(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
