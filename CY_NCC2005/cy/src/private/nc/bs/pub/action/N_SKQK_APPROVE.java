
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ClassituationHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IClassituationhvoMaintain;
import nc.vo.cy.classituation.AggClassituationHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_SKQK_APPROVE extends AbstractPfAction<AggClassituationHVO> {

        public N_SKQK_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggClassituationHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggClassituationHVO> processor = new CompareAroundProcesser<AggClassituationHVO>(
                                ClassituationHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggClassituationHVO[] processBP(Object userObj,
                        AggClassituationHVO[] clientFullVOs, AggClassituationHVO[] originBills) {
                AggClassituationHVO[] bills = null;
                IClassituationhvoMaintain operator = NCLocator.getInstance().lookup(
                                IClassituationhvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
