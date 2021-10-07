
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ClsscheduleHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IClsschedulehvoMaintain;
import nc.vo.cy.clsschedule.AggClsscheduleHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_KBDY_UNAPPROVE extends AbstractPfAction<AggClsscheduleHVO> {

        @Override
        protected CompareAroundProcesser<AggClsscheduleHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggClsscheduleHVO> processor = new CompareAroundProcesser<AggClsscheduleHVO>(
                                ClsscheduleHVOPluginPoint.UNAPPROVE);
                // TODO 在此处添加前后规则
                processor.addBeforeRule(new UnapproveStatusCheckRule());

                return processor;
        }

        @Override
        protected AggClsscheduleHVO[] processBP(Object userObj,
                        AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills) {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                AggClsscheduleHVO[] bills = null;
                try {
                        IClsschedulehvoMaintain operator = NCLocator.getInstance()
                                        .lookup(IClsschedulehvoMaintain.class);
                        bills = operator.unapprove(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
