
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.XuefeijmHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IXuefeijmhvoMaintain;
import nc.vo.cy.xuefeiapply.AggXuefeijmHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XFJM_DELETE extends AbstractPfAction<AggXuefeijmHVO> {

        @Override
        protected CompareAroundProcesser<AggXuefeijmHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggXuefeijmHVO> processor = new CompareAroundProcesser<AggXuefeijmHVO>(
                                XuefeijmHVOPluginPoint.SCRIPT_DELETE);
                // TODO 在此处添加前后规则
                return processor;
        }

        @Override
        protected AggXuefeijmHVO[] processBP(Object userObj,
                        AggXuefeijmHVO[] clientFullVOs, AggXuefeijmHVO[] originBills) {
                IXuefeijmhvoMaintain operator = NCLocator.getInstance().lookup(
                                IXuefeijmhvoMaintain.class);
                try {
                        operator.delete(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return clientFullVOs;
        }

}
