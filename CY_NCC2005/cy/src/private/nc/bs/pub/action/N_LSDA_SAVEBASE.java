
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TeacherfileHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.ITeacherfilehvoMaintain;
import nc.vo.cy.teacherfile.AggTeacherfileHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_LSDA_SAVEBASE extends AbstractPfAction<AggTeacherfileHVO> {

        @Override
        protected CompareAroundProcesser<AggTeacherfileHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTeacherfileHVO> processor = null;
                AggTeacherfileHVO[] clientFullVOs = (AggTeacherfileHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggTeacherfileHVO>(
                                        TeacherfileHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggTeacherfileHVO>(
                                        TeacherfileHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggTeacherfileHVO> rule = null;

                return processor;
        }

        @Override
        protected AggTeacherfileHVO[] processBP(Object userObj,
                        AggTeacherfileHVO[] clientFullVOs, AggTeacherfileHVO[] originBills) {

                AggTeacherfileHVO[] bills = null;
            try {
              ITeacherfilehvoMaintain operator =
                  NCLocator.getInstance().lookup(ITeacherfilehvoMaintain.class);
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
