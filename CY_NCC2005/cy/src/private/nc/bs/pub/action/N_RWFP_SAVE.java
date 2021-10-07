
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TaskallocationHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ITaskallocationhvoMaintain;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_RWFP_SAVE extends AbstractPfAction<AggTaskallocationHVO> {

        protected CompareAroundProcesser<AggTaskallocationHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTaskallocationHVO> processor = new CompareAroundProcesser<AggTaskallocationHVO>(
                TaskallocationHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggTaskallocationHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggTaskallocationHVO[] processBP(Object userObj,
                        AggTaskallocationHVO[] clientFullVOs, AggTaskallocationHVO[] originBills) {
                ITaskallocationhvoMaintain operator = NCLocator.getInstance().lookup(
                                ITaskallocationhvoMaintain.class);
                AggTaskallocationHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
