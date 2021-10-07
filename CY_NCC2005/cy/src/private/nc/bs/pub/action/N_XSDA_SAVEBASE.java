
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.StudentHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.IStudenthvoMaintain;
import nc.vo.cy.studentfile.AggStudentHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XSDA_SAVEBASE extends AbstractPfAction<AggStudentHVO> {

        @Override
        protected CompareAroundProcesser<AggStudentHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggStudentHVO> processor = null;
                AggStudentHVO[] clientFullVOs = (AggStudentHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggStudentHVO>(
                                        StudentHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggStudentHVO>(
                                        StudentHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggStudentHVO> rule = null;

                return processor;
        }

        @Override
        protected AggStudentHVO[] processBP(Object userObj,
                        AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills) {

                AggStudentHVO[] bills = null;
            try {
              IStudenthvoMaintain operator =
                  NCLocator.getInstance().lookup(IStudenthvoMaintain.class);
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
