
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TaskfeedbackHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.ITaskfeedbackhvoMaintain;
import nc.vo.cy.taskfeedback.AggTaskfeedbackHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_RWFK_SAVEBASE extends AbstractPfAction<AggTaskfeedbackHVO> {

        @Override
        protected CompareAroundProcesser<AggTaskfeedbackHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTaskfeedbackHVO> processor = null;
                AggTaskfeedbackHVO[] clientFullVOs = (AggTaskfeedbackHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggTaskfeedbackHVO>(
                                        TaskfeedbackHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggTaskfeedbackHVO>(
                                        TaskfeedbackHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggTaskfeedbackHVO> rule = null;

                return processor;
        }

        @Override
        protected AggTaskfeedbackHVO[] processBP(Object userObj,
                        AggTaskfeedbackHVO[] clientFullVOs, AggTaskfeedbackHVO[] originBills) {

                AggTaskfeedbackHVO[] bills = null;
            try {
              ITaskfeedbackhvoMaintain operator =
                  NCLocator.getInstance().lookup(ITaskfeedbackhvoMaintain.class);
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
