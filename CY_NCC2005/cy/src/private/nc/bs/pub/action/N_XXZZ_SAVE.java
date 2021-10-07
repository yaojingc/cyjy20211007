
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.MsgstationHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IMsgstationhvoMaintain;
import nc.vo.cy.msgstation.AggMsgstationHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XXZZ_SAVE extends AbstractPfAction<AggMsgstationHVO> {

        protected CompareAroundProcesser<AggMsgstationHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggMsgstationHVO> processor = new CompareAroundProcesser<AggMsgstationHVO>(
                MsgstationHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggMsgstationHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggMsgstationHVO[] processBP(Object userObj,
                        AggMsgstationHVO[] clientFullVOs, AggMsgstationHVO[] originBills) {
                IMsgstationhvoMaintain operator = NCLocator.getInstance().lookup(
                                IMsgstationhvoMaintain.class);
                AggMsgstationHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
