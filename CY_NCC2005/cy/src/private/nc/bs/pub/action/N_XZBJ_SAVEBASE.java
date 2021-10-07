
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ClassxzHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.IClassxzhvoMaintain;
import nc.vo.cy.classfilexz.AggClassxzHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XZBJ_SAVEBASE extends AbstractPfAction<AggClassxzHVO> {

        @Override
        protected CompareAroundProcesser<AggClassxzHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggClassxzHVO> processor = null;
                AggClassxzHVO[] clientFullVOs = (AggClassxzHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggClassxzHVO>(
                                        ClassxzHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggClassxzHVO>(
                                        ClassxzHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggClassxzHVO> rule = null;

                return processor;
        }

        @Override
        protected AggClassxzHVO[] processBP(Object userObj,
                        AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills) {

                AggClassxzHVO[] bills = null;
            try {
              IClassxzhvoMaintain operator =
                  NCLocator.getInstance().lookup(IClassxzhvoMaintain.class);
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
