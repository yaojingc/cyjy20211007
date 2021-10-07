
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ClassxzHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IClassxzhvoMaintain;
import nc.vo.cy.classfilexz.AggClassxzHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XZBJ_APPROVE extends AbstractPfAction<AggClassxzHVO> {

        public N_XZBJ_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggClassxzHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggClassxzHVO> processor = new CompareAroundProcesser<AggClassxzHVO>(
                                ClassxzHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggClassxzHVO[] processBP(Object userObj,
                        AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills) {
                AggClassxzHVO[] bills = null;
                IClassxzhvoMaintain operator = NCLocator.getInstance().lookup(
                                IClassxzhvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
