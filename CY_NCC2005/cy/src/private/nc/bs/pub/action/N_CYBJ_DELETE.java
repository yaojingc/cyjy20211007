
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ClasscyHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IClasscyhvoMaintain;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_CYBJ_DELETE extends AbstractPfAction<AggClasscyHVO> {

        @Override
        protected CompareAroundProcesser<AggClasscyHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggClasscyHVO> processor = new CompareAroundProcesser<AggClasscyHVO>(
                                ClasscyHVOPluginPoint.SCRIPT_DELETE);
                // TODO 在此处添加前后规则
                return processor;
        }

        @Override
        protected AggClasscyHVO[] processBP(Object userObj,
                        AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills) {
                IClasscyhvoMaintain operator = NCLocator.getInstance().lookup(
                                IClasscyhvoMaintain.class);
                try {
                        operator.delete(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return clientFullVOs;
        }

}
