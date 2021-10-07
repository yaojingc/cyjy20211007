
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.PlantravelHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IPlantravelhvoMaintain;
import nc.vo.cy.plantravel.AggPlantravelHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XCAP_SAVE extends AbstractPfAction<AggPlantravelHVO> {

        protected CompareAroundProcesser<AggPlantravelHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggPlantravelHVO> processor = new CompareAroundProcesser<AggPlantravelHVO>(
                PlantravelHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggPlantravelHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggPlantravelHVO[] processBP(Object userObj,
                        AggPlantravelHVO[] clientFullVOs, AggPlantravelHVO[] originBills) {
                IPlantravelhvoMaintain operator = NCLocator.getInstance().lookup(
                                IPlantravelhvoMaintain.class);
                AggPlantravelHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
