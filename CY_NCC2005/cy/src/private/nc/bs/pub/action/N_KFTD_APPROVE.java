
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.CallcenterHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ICallcenterhvoMaintain;
import nc.vo.cy.callcenter.AggCallcenterHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_KFTD_APPROVE extends AbstractPfAction<AggCallcenterHVO> {

        public N_KFTD_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggCallcenterHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggCallcenterHVO> processor = new CompareAroundProcesser<AggCallcenterHVO>(
                                CallcenterHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggCallcenterHVO[] processBP(Object userObj,
                        AggCallcenterHVO[] clientFullVOs, AggCallcenterHVO[] originBills) {
                AggCallcenterHVO[] bills = null;
                ICallcenterhvoMaintain operator = NCLocator.getInstance().lookup(
                                ICallcenterhvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
