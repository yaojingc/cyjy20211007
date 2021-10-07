
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.DailyHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IDailyhvoMaintain;
import nc.vo.cy.daily.AggDailyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_GZRB_APPROVE extends AbstractPfAction<AggDailyHVO> {

        public N_GZRB_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggDailyHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggDailyHVO> processor = new CompareAroundProcesser<AggDailyHVO>(
                                DailyHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggDailyHVO[] processBP(Object userObj,
                        AggDailyHVO[] clientFullVOs, AggDailyHVO[] originBills) {
                AggDailyHVO[] bills = null;
                IDailyhvoMaintain operator = NCLocator.getInstance().lookup(
                                IDailyhvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
