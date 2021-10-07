
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.CallcenterHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ICallcenterhvoMaintain;
import nc.vo.cy.callcenter.AggCallcenterHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_KFTD_SAVE extends AbstractPfAction<AggCallcenterHVO> {

        protected CompareAroundProcesser<AggCallcenterHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggCallcenterHVO> processor = new CompareAroundProcesser<AggCallcenterHVO>(
                CallcenterHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggCallcenterHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggCallcenterHVO[] processBP(Object userObj,
                        AggCallcenterHVO[] clientFullVOs, AggCallcenterHVO[] originBills) {
                ICallcenterhvoMaintain operator = NCLocator.getInstance().lookup(
                                ICallcenterhvoMaintain.class);
                AggCallcenterHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
