
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.CreditcontractHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.ICreditcontracthvoMaintain;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_SKHT_SAVEBASE extends AbstractPfAction<AggCreditcontractHVO> {

        @Override
        protected CompareAroundProcesser<AggCreditcontractHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggCreditcontractHVO> processor = null;
                AggCreditcontractHVO[] clientFullVOs = (AggCreditcontractHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggCreditcontractHVO>(
                                        CreditcontractHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggCreditcontractHVO>(
                                        CreditcontractHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggCreditcontractHVO> rule = null;

                return processor;
        }

        @Override
        protected AggCreditcontractHVO[] processBP(Object userObj,
                        AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills) {

                AggCreditcontractHVO[] bills = null;
            try {
              ICreditcontracthvoMaintain operator =
                  NCLocator.getInstance().lookup(ICreditcontracthvoMaintain.class);
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
