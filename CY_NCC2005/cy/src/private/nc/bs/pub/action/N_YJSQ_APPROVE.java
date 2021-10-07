
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.YejiapplyHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IYejiapplyhvoMaintain;
import nc.vo.cy.yejiapply.AggYejiapplyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_YJSQ_APPROVE extends AbstractPfAction<AggYejiapplyHVO> {

        public N_YJSQ_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggYejiapplyHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggYejiapplyHVO> processor = new CompareAroundProcesser<AggYejiapplyHVO>(
                                YejiapplyHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggYejiapplyHVO[] processBP(Object userObj,
                        AggYejiapplyHVO[] clientFullVOs, AggYejiapplyHVO[] originBills) {
                AggYejiapplyHVO[] bills = null;
                IYejiapplyhvoMaintain operator = NCLocator.getInstance().lookup(
                                IYejiapplyhvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
