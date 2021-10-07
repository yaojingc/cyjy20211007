
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ClsscheduleHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IClsschedulehvoMaintain;
import nc.vo.cy.clsschedule.AggClsscheduleHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_KBDY_APPROVE extends AbstractPfAction<AggClsscheduleHVO> {

        public N_KBDY_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggClsscheduleHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggClsscheduleHVO> processor = new CompareAroundProcesser<AggClsscheduleHVO>(
                                ClsscheduleHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggClsscheduleHVO[] processBP(Object userObj,
                        AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills) {
                AggClsscheduleHVO[] bills = null;
                IClsschedulehvoMaintain operator = NCLocator.getInstance().lookup(
                                IClsschedulehvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
