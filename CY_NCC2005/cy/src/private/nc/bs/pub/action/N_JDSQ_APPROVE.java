
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.JiedaiapplyHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IJiedaiapplyhvoMaintain;
import nc.vo.cy.jiedaiapply.AggJiedaiapplyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_JDSQ_APPROVE extends AbstractPfAction<AggJiedaiapplyHVO> {

        public N_JDSQ_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggJiedaiapplyHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggJiedaiapplyHVO> processor = new CompareAroundProcesser<AggJiedaiapplyHVO>(
                                JiedaiapplyHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggJiedaiapplyHVO[] processBP(Object userObj,
                        AggJiedaiapplyHVO[] clientFullVOs, AggJiedaiapplyHVO[] originBills) {
                AggJiedaiapplyHVO[] bills = null;
                IJiedaiapplyhvoMaintain operator = NCLocator.getInstance().lookup(
                                IJiedaiapplyhvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
