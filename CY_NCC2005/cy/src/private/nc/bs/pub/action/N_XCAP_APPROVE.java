
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.PlantravelHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IPlantravelhvoMaintain;
import nc.vo.cy.plantravel.AggPlantravelHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XCAP_APPROVE extends AbstractPfAction<AggPlantravelHVO> {

        public N_XCAP_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggPlantravelHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggPlantravelHVO> processor = new CompareAroundProcesser<AggPlantravelHVO>(
                                PlantravelHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggPlantravelHVO[] processBP(Object userObj,
                        AggPlantravelHVO[] clientFullVOs, AggPlantravelHVO[] originBills) {
                AggPlantravelHVO[] bills = null;
                IPlantravelhvoMaintain operator = NCLocator.getInstance().lookup(
                                IPlantravelhvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
