
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.WorkplanHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IWorkplanhvoMaintain;
import nc.vo.cy.workplan.AggWorkplanHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_GZJH_SAVE extends AbstractPfAction<AggWorkplanHVO> {

        protected CompareAroundProcesser<AggWorkplanHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggWorkplanHVO> processor = new CompareAroundProcesser<AggWorkplanHVO>(
                WorkplanHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggWorkplanHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggWorkplanHVO[] processBP(Object userObj,
                        AggWorkplanHVO[] clientFullVOs, AggWorkplanHVO[] originBills) {
                IWorkplanhvoMaintain operator = NCLocator.getInstance().lookup(
                                IWorkplanhvoMaintain.class);
                AggWorkplanHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
