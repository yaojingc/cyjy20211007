
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.GuideHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IGuidehvoMaintain;
import nc.vo.cy.guide.AggGuideHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XSZN_UNSAVEBILL extends AbstractPfAction<AggGuideHVO> {

        @Override
        protected CompareAroundProcesser<AggGuideHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggGuideHVO> processor = new CompareAroundProcesser<AggGuideHVO>(
                                GuideHVOPluginPoint.UNSEND_APPROVE);
                // TODO 在此处添加前后规则
                //processor.addBeforeRule(new UncommitStatusCheckRule());

                return processor;
        }

        @Override
        protected AggGuideHVO[] processBP(Object userObj,
                        AggGuideHVO[] clientFullVOs, AggGuideHVO[] originBills) {
                IGuidehvoMaintain operator = NCLocator.getInstance().lookup(
                                IGuidehvoMaintain.class);
                AggGuideHVO[] bills = null;
                try {
                        bills = operator.unsave(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
