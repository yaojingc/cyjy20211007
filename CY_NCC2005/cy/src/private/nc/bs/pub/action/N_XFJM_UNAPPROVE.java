
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.XuefeijmHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IXuefeijmhvoMaintain;
import nc.vo.cy.xuefeiapply.AggXuefeijmHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XFJM_UNAPPROVE extends AbstractPfAction<AggXuefeijmHVO> {

        @Override
        protected CompareAroundProcesser<AggXuefeijmHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggXuefeijmHVO> processor = new CompareAroundProcesser<AggXuefeijmHVO>(
                                XuefeijmHVOPluginPoint.UNAPPROVE);
                // TODO 在此处添加前后规则
                processor.addBeforeRule(new UnapproveStatusCheckRule());

                return processor;
        }

        @Override
        protected AggXuefeijmHVO[] processBP(Object userObj,
                        AggXuefeijmHVO[] clientFullVOs, AggXuefeijmHVO[] originBills) {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                AggXuefeijmHVO[] bills = null;
                try {
                        IXuefeijmhvoMaintain operator = NCLocator.getInstance()
                                        .lookup(IXuefeijmhvoMaintain.class);
                        bills = operator.unapprove(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
