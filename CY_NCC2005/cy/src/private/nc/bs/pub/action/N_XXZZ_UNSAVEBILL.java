
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.MsgstationHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IMsgstationhvoMaintain;
import nc.vo.cy.msgstation.AggMsgstationHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XXZZ_UNSAVEBILL extends AbstractPfAction<AggMsgstationHVO> {

        @Override
        protected CompareAroundProcesser<AggMsgstationHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggMsgstationHVO> processor = new CompareAroundProcesser<AggMsgstationHVO>(
                                MsgstationHVOPluginPoint.UNSEND_APPROVE);
                // TODO 在此处添加前后规则
                //processor.addBeforeRule(new UncommitStatusCheckRule());

                return processor;
        }

        @Override
        protected AggMsgstationHVO[] processBP(Object userObj,
                        AggMsgstationHVO[] clientFullVOs, AggMsgstationHVO[] originBills) {
                IMsgstationhvoMaintain operator = NCLocator.getInstance().lookup(
                                IMsgstationhvoMaintain.class);
                AggMsgstationHVO[] bills = null;
                try {
                        bills = operator.unsave(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
