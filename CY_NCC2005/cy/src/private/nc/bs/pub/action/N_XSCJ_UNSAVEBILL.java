
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.StumarkdataHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IStumarkdatahvoMaintain;
import nc.vo.cy.stumarkdata.AggStumarkdataHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XSCJ_UNSAVEBILL extends AbstractPfAction<AggStumarkdataHVO> {

        @Override
        protected CompareAroundProcesser<AggStumarkdataHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggStumarkdataHVO> processor = new CompareAroundProcesser<AggStumarkdataHVO>(
                                StumarkdataHVOPluginPoint.UNSEND_APPROVE);
                // TODO 在此处添加前后规则
                //processor.addBeforeRule(new UncommitStatusCheckRule());

                return processor;
        }

        @Override
        protected AggStumarkdataHVO[] processBP(Object userObj,
                        AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills) {
                IStumarkdatahvoMaintain operator = NCLocator.getInstance().lookup(
                                IStumarkdatahvoMaintain.class);
                AggStumarkdataHVO[] bills = null;
                try {
                        bills = operator.unsave(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
