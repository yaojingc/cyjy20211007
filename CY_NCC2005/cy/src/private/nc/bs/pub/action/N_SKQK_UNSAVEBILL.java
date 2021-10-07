
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ClassituationHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IClassituationhvoMaintain;
import nc.vo.cy.classituation.AggClassituationHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_SKQK_UNSAVEBILL extends AbstractPfAction<AggClassituationHVO> {

        @Override
        protected CompareAroundProcesser<AggClassituationHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggClassituationHVO> processor = new CompareAroundProcesser<AggClassituationHVO>(
                                ClassituationHVOPluginPoint.UNSEND_APPROVE);
                // TODO 在此处添加前后规则
                //processor.addBeforeRule(new UncommitStatusCheckRule());

                return processor;
        }

        @Override
        protected AggClassituationHVO[] processBP(Object userObj,
                        AggClassituationHVO[] clientFullVOs, AggClassituationHVO[] originBills) {
                IClassituationhvoMaintain operator = NCLocator.getInstance().lookup(
                                IClassituationhvoMaintain.class);
                AggClassituationHVO[] bills = null;
                try {
                        bills = operator.unsave(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
