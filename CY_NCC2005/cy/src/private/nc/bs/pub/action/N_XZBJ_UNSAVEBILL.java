
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ClassxzHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IClassxzhvoMaintain;
import nc.vo.cy.classfilexz.AggClassxzHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XZBJ_UNSAVEBILL extends AbstractPfAction<AggClassxzHVO> {

        @Override
        protected CompareAroundProcesser<AggClassxzHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggClassxzHVO> processor = new CompareAroundProcesser<AggClassxzHVO>(
                                ClassxzHVOPluginPoint.UNSEND_APPROVE);
                // TODO 在此处添加前后规则
                //processor.addBeforeRule(new UncommitStatusCheckRule());

                return processor;
        }

        @Override
        protected AggClassxzHVO[] processBP(Object userObj,
                        AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills) {
                IClassxzhvoMaintain operator = NCLocator.getInstance().lookup(
                                IClassxzhvoMaintain.class);
                AggClassxzHVO[] bills = null;
                try {
                        bills = operator.unsave(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
