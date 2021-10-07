
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.DailyHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IDailyhvoMaintain;
import nc.vo.cy.daily.AggDailyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_GZRB_SAVE extends AbstractPfAction<AggDailyHVO> {

        protected CompareAroundProcesser<AggDailyHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggDailyHVO> processor = new CompareAroundProcesser<AggDailyHVO>(
                DailyHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggDailyHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggDailyHVO[] processBP(Object userObj,
                        AggDailyHVO[] clientFullVOs, AggDailyHVO[] originBills) {
                IDailyhvoMaintain operator = NCLocator.getInstance().lookup(
                                IDailyhvoMaintain.class);
                AggDailyHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
