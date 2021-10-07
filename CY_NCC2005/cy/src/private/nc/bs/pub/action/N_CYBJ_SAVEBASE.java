
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ClasscyHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.IClasscyhvoMaintain;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_CYBJ_SAVEBASE extends AbstractPfAction<AggClasscyHVO> {

        @Override
        protected CompareAroundProcesser<AggClasscyHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggClasscyHVO> processor = null;
                AggClasscyHVO[] clientFullVOs = (AggClasscyHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggClasscyHVO>(
                                        ClasscyHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggClasscyHVO>(
                                        ClasscyHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggClasscyHVO> rule = null;

                return processor;
        }

        @Override
        protected AggClasscyHVO[] processBP(Object userObj,
                        AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills) {

                AggClasscyHVO[] bills = null;
            try {
              IClasscyhvoMaintain operator =
                  NCLocator.getInstance().lookup(IClasscyhvoMaintain.class);
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
