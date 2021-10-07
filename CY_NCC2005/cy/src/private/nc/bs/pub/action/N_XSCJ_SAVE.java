
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.StumarkdataHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IStumarkdatahvoMaintain;
import nc.vo.cy.stumarkdata.AggStumarkdataHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XSCJ_SAVE extends AbstractPfAction<AggStumarkdataHVO> {

        protected CompareAroundProcesser<AggStumarkdataHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggStumarkdataHVO> processor = new CompareAroundProcesser<AggStumarkdataHVO>(
                StumarkdataHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggStumarkdataHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggStumarkdataHVO[] processBP(Object userObj,
                        AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills) {
                IStumarkdatahvoMaintain operator = NCLocator.getInstance().lookup(
                                IStumarkdatahvoMaintain.class);
                AggStumarkdataHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
