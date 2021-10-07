
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.JiedaiapplyHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IJiedaiapplyhvoMaintain;
import nc.vo.cy.jiedaiapply.AggJiedaiapplyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_JDSQ_DELETE extends AbstractPfAction<AggJiedaiapplyHVO> {

        @Override
        protected CompareAroundProcesser<AggJiedaiapplyHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggJiedaiapplyHVO> processor = new CompareAroundProcesser<AggJiedaiapplyHVO>(
                                JiedaiapplyHVOPluginPoint.SCRIPT_DELETE);
                // TODO 在此处添加前后规则
                return processor;
        }

        @Override
        protected AggJiedaiapplyHVO[] processBP(Object userObj,
                        AggJiedaiapplyHVO[] clientFullVOs, AggJiedaiapplyHVO[] originBills) {
                IJiedaiapplyhvoMaintain operator = NCLocator.getInstance().lookup(
                                IJiedaiapplyhvoMaintain.class);
                try {
                        operator.delete(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return clientFullVOs;
        }

}
