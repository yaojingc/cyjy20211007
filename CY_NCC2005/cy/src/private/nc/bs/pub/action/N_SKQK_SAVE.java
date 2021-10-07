
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ClassituationHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IClassituationhvoMaintain;
import nc.vo.cy.classituation.AggClassituationHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_SKQK_SAVE extends AbstractPfAction<AggClassituationHVO> {

        protected CompareAroundProcesser<AggClassituationHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggClassituationHVO> processor = new CompareAroundProcesser<AggClassituationHVO>(
                ClassituationHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggClassituationHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggClassituationHVO[] processBP(Object userObj,
                        AggClassituationHVO[] clientFullVOs, AggClassituationHVO[] originBills) {
                IClassituationhvoMaintain operator = NCLocator.getInstance().lookup(
                                IClassituationhvoMaintain.class);
                AggClassituationHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
