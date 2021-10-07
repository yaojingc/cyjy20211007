
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ClasscyHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IClasscyhvoMaintain;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_CYBJ_SAVE extends AbstractPfAction<AggClasscyHVO> {

        protected CompareAroundProcesser<AggClasscyHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggClasscyHVO> processor = new CompareAroundProcesser<AggClasscyHVO>(
                ClasscyHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggClasscyHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggClasscyHVO[] processBP(Object userObj,
                        AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills) {
                IClasscyhvoMaintain operator = NCLocator.getInstance().lookup(
                                IClasscyhvoMaintain.class);
                AggClasscyHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
