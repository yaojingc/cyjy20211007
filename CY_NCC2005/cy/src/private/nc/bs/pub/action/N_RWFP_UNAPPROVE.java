
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TaskallocationHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ITaskallocationhvoMaintain;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_RWFP_UNAPPROVE extends AbstractPfAction<AggTaskallocationHVO> {

        @Override
        protected CompareAroundProcesser<AggTaskallocationHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTaskallocationHVO> processor = new CompareAroundProcesser<AggTaskallocationHVO>(
                                TaskallocationHVOPluginPoint.UNAPPROVE);
                // TODO 在此处添加前后规则
                processor.addBeforeRule(new UnapproveStatusCheckRule());

                return processor;
        }

        @Override
        protected AggTaskallocationHVO[] processBP(Object userObj,
                        AggTaskallocationHVO[] clientFullVOs, AggTaskallocationHVO[] originBills) {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                AggTaskallocationHVO[] bills = null;
                try {
                        ITaskallocationhvoMaintain operator = NCLocator.getInstance()
                                        .lookup(ITaskallocationhvoMaintain.class);
                        bills = operator.unapprove(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
