
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TeacherformHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ITeacherformhvoMaintain;
import nc.vo.cy.teacherform.AggTeacherformHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_LSSQ_APPROVE extends AbstractPfAction<AggTeacherformHVO> {

        public N_LSSQ_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggTeacherformHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTeacherformHVO> processor = new CompareAroundProcesser<AggTeacherformHVO>(
                                TeacherformHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggTeacherformHVO[] processBP(Object userObj,
                        AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills) {
                AggTeacherformHVO[] bills = null;
                ITeacherformhvoMaintain operator = NCLocator.getInstance().lookup(
                                ITeacherformhvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                } catch (Exception e) {
					// TODO Auto-generated catch block
                	 ExceptionUtils.wrappBusinessException(e.getMessage());
				}
                return bills;
        }

}
