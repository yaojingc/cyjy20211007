
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.JiedaiapplyHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.IJiedaiapplyhvoMaintain;
import nc.vo.cy.jiedaiapply.AggJiedaiapplyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_JDSQ_SAVEBASE extends AbstractPfAction<AggJiedaiapplyHVO> {

        @Override
        protected CompareAroundProcesser<AggJiedaiapplyHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggJiedaiapplyHVO> processor = null;
                AggJiedaiapplyHVO[] clientFullVOs = (AggJiedaiapplyHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggJiedaiapplyHVO>(
                                        JiedaiapplyHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggJiedaiapplyHVO>(
                                        JiedaiapplyHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggJiedaiapplyHVO> rule = null;

                return processor;
        }

        @Override
        protected AggJiedaiapplyHVO[] processBP(Object userObj,
                        AggJiedaiapplyHVO[] clientFullVOs, AggJiedaiapplyHVO[] originBills) {

                AggJiedaiapplyHVO[] bills = null;
            try {
              IJiedaiapplyhvoMaintain operator =
                  NCLocator.getInstance().lookup(IJiedaiapplyhvoMaintain.class);
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
