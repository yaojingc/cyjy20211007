
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.CallcenterHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ICallcenterhvoMaintain;
import nc.vo.cy.callcenter.AggCallcenterHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_KFTD_UNSAVEBILL extends AbstractPfAction<AggCallcenterHVO> {

        @Override
        protected CompareAroundProcesser<AggCallcenterHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggCallcenterHVO> processor = new CompareAroundProcesser<AggCallcenterHVO>(
                                CallcenterHVOPluginPoint.UNSEND_APPROVE);
                // TODO 在此处添加前后规则
                //processor.addBeforeRule(new UncommitStatusCheckRule());

                return processor;
        }

        @Override
        protected AggCallcenterHVO[] processBP(Object userObj,
                        AggCallcenterHVO[] clientFullVOs, AggCallcenterHVO[] originBills) {
                ICallcenterhvoMaintain operator = NCLocator.getInstance().lookup(
                                ICallcenterhvoMaintain.class);
                AggCallcenterHVO[] bills = null;
                try {
                        bills = operator.unsave(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
