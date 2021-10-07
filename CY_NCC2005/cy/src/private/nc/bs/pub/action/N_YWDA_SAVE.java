
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.SalesmanfileHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ISalesmanfilehvoMaintain;
import nc.vo.cy.salesmanfile.AggSalesmanfileHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_YWDA_SAVE extends AbstractPfAction<AggSalesmanfileHVO> {

        protected CompareAroundProcesser<AggSalesmanfileHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggSalesmanfileHVO> processor = new CompareAroundProcesser<AggSalesmanfileHVO>(
                SalesmanfileHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggSalesmanfileHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggSalesmanfileHVO[] processBP(Object userObj,
                        AggSalesmanfileHVO[] clientFullVOs, AggSalesmanfileHVO[] originBills) {
                ISalesmanfilehvoMaintain operator = NCLocator.getInstance().lookup(
                                ISalesmanfilehvoMaintain.class);
                AggSalesmanfileHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
