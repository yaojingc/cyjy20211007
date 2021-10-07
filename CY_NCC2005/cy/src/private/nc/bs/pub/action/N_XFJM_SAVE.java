
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.XuefeijmHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IXuefeijmhvoMaintain;
import nc.vo.cy.xuefeiapply.AggXuefeijmHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XFJM_SAVE extends AbstractPfAction<AggXuefeijmHVO> {

        protected CompareAroundProcesser<AggXuefeijmHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggXuefeijmHVO> processor = new CompareAroundProcesser<AggXuefeijmHVO>(
                XuefeijmHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggXuefeijmHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggXuefeijmHVO[] processBP(Object userObj,
                        AggXuefeijmHVO[] clientFullVOs, AggXuefeijmHVO[] originBills) {
                IXuefeijmhvoMaintain operator = NCLocator.getInstance().lookup(
                                IXuefeijmhvoMaintain.class);
                AggXuefeijmHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
