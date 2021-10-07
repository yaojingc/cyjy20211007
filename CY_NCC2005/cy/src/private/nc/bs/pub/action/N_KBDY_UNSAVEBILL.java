
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ClsscheduleHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IClsschedulehvoMaintain;
import nc.vo.cy.clsschedule.AggClsscheduleHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_KBDY_UNSAVEBILL extends AbstractPfAction<AggClsscheduleHVO> {

        @Override
        protected CompareAroundProcesser<AggClsscheduleHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggClsscheduleHVO> processor = new CompareAroundProcesser<AggClsscheduleHVO>(
                                ClsscheduleHVOPluginPoint.UNSEND_APPROVE);
                // TODO 在此处添加前后规则
                //processor.addBeforeRule(new UncommitStatusCheckRule());

                return processor;
        }

        @Override
        protected AggClsscheduleHVO[] processBP(Object userObj,
                        AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills) {
                IClsschedulehvoMaintain operator = NCLocator.getInstance().lookup(
                                IClsschedulehvoMaintain.class);
                AggClsscheduleHVO[] bills = null;
                try {
                        bills = operator.unsave(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
