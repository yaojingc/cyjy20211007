
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ClsscheduleHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IClsschedulehvoMaintain;
import nc.vo.cy.clsschedule.AggClsscheduleHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_KBDY_SAVE extends AbstractPfAction<AggClsscheduleHVO> {

        protected CompareAroundProcesser<AggClsscheduleHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggClsscheduleHVO> processor = new CompareAroundProcesser<AggClsscheduleHVO>(
                ClsscheduleHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggClsscheduleHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggClsscheduleHVO[] processBP(Object userObj,
                        AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills) {
                IClsschedulehvoMaintain operator = NCLocator.getInstance().lookup(
                                IClsschedulehvoMaintain.class);
                AggClsscheduleHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
