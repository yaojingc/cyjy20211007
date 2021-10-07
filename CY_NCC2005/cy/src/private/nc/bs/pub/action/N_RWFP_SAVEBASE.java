
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TaskallocationHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.ITaskallocationhvoMaintain;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_RWFP_SAVEBASE extends AbstractPfAction<AggTaskallocationHVO> {

        @Override
        protected CompareAroundProcesser<AggTaskallocationHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTaskallocationHVO> processor = null;
                AggTaskallocationHVO[] clientFullVOs = (AggTaskallocationHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggTaskallocationHVO>(
                                        TaskallocationHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggTaskallocationHVO>(
                                        TaskallocationHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggTaskallocationHVO> rule = null;

                return processor;
        }

        @Override
        protected AggTaskallocationHVO[] processBP(Object userObj,
                        AggTaskallocationHVO[] clientFullVOs, AggTaskallocationHVO[] originBills) {

                AggTaskallocationHVO[] bills = null;
            try {
              ITaskallocationhvoMaintain operator =
                  NCLocator.getInstance().lookup(ITaskallocationhvoMaintain.class);
              if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                  .getPrimaryKey())) {
                bills = operator.update(clientFullVOs, originBills);
              }
              else {
                bills = operator.insert(clientFullVOs, originBills);
              }
            }
            catch (BusinessException e) {
              ExceptionUtils.wrappBusinessException(e.getMessage());
            }
            return bills;
        }
}
