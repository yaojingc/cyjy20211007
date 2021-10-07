
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.SalesmanfileHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.ISalesmanfilehvoMaintain;
import nc.vo.cy.salesmanfile.AggSalesmanfileHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_YWDA_SAVEBASE extends AbstractPfAction<AggSalesmanfileHVO> {

        @Override
        protected CompareAroundProcesser<AggSalesmanfileHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggSalesmanfileHVO> processor = null;
                AggSalesmanfileHVO[] clientFullVOs = (AggSalesmanfileHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggSalesmanfileHVO>(
                                        SalesmanfileHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggSalesmanfileHVO>(
                                        SalesmanfileHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggSalesmanfileHVO> rule = null;

                return processor;
        }

        @Override
        protected AggSalesmanfileHVO[] processBP(Object userObj,
                        AggSalesmanfileHVO[] clientFullVOs, AggSalesmanfileHVO[] originBills) {

                AggSalesmanfileHVO[] bills = null;
            try {
              ISalesmanfilehvoMaintain operator =
                  NCLocator.getInstance().lookup(ISalesmanfilehvoMaintain.class);
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
