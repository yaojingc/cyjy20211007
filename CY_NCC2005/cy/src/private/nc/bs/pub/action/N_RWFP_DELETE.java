
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TaskallocationHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ITaskallocationhvoMaintain;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_RWFP_DELETE extends AbstractPfAction<AggTaskallocationHVO> {

        @Override
        protected CompareAroundProcesser<AggTaskallocationHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTaskallocationHVO> processor = new CompareAroundProcesser<AggTaskallocationHVO>(
                                TaskallocationHVOPluginPoint.SCRIPT_DELETE);
                // TODO 在此处添加前后规则
                return processor;
        }

        @Override
        protected AggTaskallocationHVO[] processBP(Object userObj,
                        AggTaskallocationHVO[] clientFullVOs, AggTaskallocationHVO[] originBills) {
                ITaskallocationhvoMaintain operator = NCLocator.getInstance().lookup(
                                ITaskallocationhvoMaintain.class);
                try {
                        operator.delete(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return clientFullVOs;
        }

}
