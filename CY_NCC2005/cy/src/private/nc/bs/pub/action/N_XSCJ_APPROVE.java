
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.StumarkdataHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IStumarkdatahvoMaintain;
import nc.vo.cy.stumarkdata.AggStumarkdataHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XSCJ_APPROVE extends AbstractPfAction<AggStumarkdataHVO> {

        public N_XSCJ_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggStumarkdataHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggStumarkdataHVO> processor = new CompareAroundProcesser<AggStumarkdataHVO>(
                                StumarkdataHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggStumarkdataHVO[] processBP(Object userObj,
                        AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills) {
                AggStumarkdataHVO[] bills = null;
                IStumarkdatahvoMaintain operator = NCLocator.getInstance().lookup(
                                IStumarkdatahvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
