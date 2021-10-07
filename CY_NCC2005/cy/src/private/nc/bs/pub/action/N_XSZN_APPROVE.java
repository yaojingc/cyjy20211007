
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.GuideHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IGuidehvoMaintain;
import nc.vo.cy.guide.AggGuideHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XSZN_APPROVE extends AbstractPfAction<AggGuideHVO> {

        public N_XSZN_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggGuideHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggGuideHVO> processor = new CompareAroundProcesser<AggGuideHVO>(
                                GuideHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggGuideHVO[] processBP(Object userObj,
                        AggGuideHVO[] clientFullVOs, AggGuideHVO[] originBills) {
                AggGuideHVO[] bills = null;
                IGuidehvoMaintain operator = NCLocator.getInstance().lookup(
                                IGuidehvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
