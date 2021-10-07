
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.SalesmanfileHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ISalesmanfilehvoMaintain;
import nc.vo.cy.salesmanfile.AggSalesmanfileHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_YWDA_DELETE extends AbstractPfAction<AggSalesmanfileHVO> {

        @Override
        protected CompareAroundProcesser<AggSalesmanfileHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggSalesmanfileHVO> processor = new CompareAroundProcesser<AggSalesmanfileHVO>(
                                SalesmanfileHVOPluginPoint.SCRIPT_DELETE);
                // TODO 在此处添加前后规则
                return processor;
        }

        @Override
        protected AggSalesmanfileHVO[] processBP(Object userObj,
                        AggSalesmanfileHVO[] clientFullVOs, AggSalesmanfileHVO[] originBills) {
                ISalesmanfilehvoMaintain operator = NCLocator.getInstance().lookup(
                                ISalesmanfilehvoMaintain.class);
                try {
                        operator.delete(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return clientFullVOs;
        }

}
