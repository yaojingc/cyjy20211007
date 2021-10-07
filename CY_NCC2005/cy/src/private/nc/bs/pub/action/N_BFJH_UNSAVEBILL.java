
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.WorkplanHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IWorkplanhvoMaintain;
import nc.vo.cy.workplan.AggWorkplanHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_BFJH_UNSAVEBILL extends AbstractPfAction<AggWorkplanHVO> {

        @Override
        protected CompareAroundProcesser<AggWorkplanHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggWorkplanHVO> processor = new CompareAroundProcesser<AggWorkplanHVO>(
                                WorkplanHVOPluginPoint.UNSEND_APPROVE);
                // TODO 在此处添加前后规则
                //processor.addBeforeRule(new UncommitStatusCheckRule());

                return processor;
        }

        @Override
        protected AggWorkplanHVO[] processBP(Object userObj,
                        AggWorkplanHVO[] clientFullVOs, AggWorkplanHVO[] originBills) {
                IWorkplanhvoMaintain operator = NCLocator.getInstance().lookup(
                                IWorkplanhvoMaintain.class);
                AggWorkplanHVO[] bills = null;
                try {
                        bills = operator.unsave(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
