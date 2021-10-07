
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.XuefeijmHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IXuefeijmhvoMaintain;
import nc.vo.cy.xuefeiapply.AggXuefeijmHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XFJM_APPROVE extends AbstractPfAction<AggXuefeijmHVO> {

        public N_XFJM_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggXuefeijmHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggXuefeijmHVO> processor = new CompareAroundProcesser<AggXuefeijmHVO>(
                                XuefeijmHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggXuefeijmHVO[] processBP(Object userObj,
                        AggXuefeijmHVO[] clientFullVOs, AggXuefeijmHVO[] originBills) {
                AggXuefeijmHVO[] bills = null;
                IXuefeijmhvoMaintain operator = NCLocator.getInstance().lookup(
                                IXuefeijmhvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
