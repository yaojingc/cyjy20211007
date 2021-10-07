
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.MsgstationHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IMsgstationhvoMaintain;
import nc.vo.cy.msgstation.AggMsgstationHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XXZZ_APPROVE extends AbstractPfAction<AggMsgstationHVO> {

        public N_XXZZ_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggMsgstationHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggMsgstationHVO> processor = new CompareAroundProcesser<AggMsgstationHVO>(
                                MsgstationHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggMsgstationHVO[] processBP(Object userObj,
                        AggMsgstationHVO[] clientFullVOs, AggMsgstationHVO[] originBills) {
                AggMsgstationHVO[] bills = null;
                IMsgstationhvoMaintain operator = NCLocator.getInstance().lookup(
                                IMsgstationhvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
