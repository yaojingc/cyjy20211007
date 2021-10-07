
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.PlantravelHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IPlantravelhvoMaintain;
import nc.vo.cy.plantravel.AggPlantravelHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XCAP_DELETE extends AbstractPfAction<AggPlantravelHVO> {

        @Override
        protected CompareAroundProcesser<AggPlantravelHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggPlantravelHVO> processor = new CompareAroundProcesser<AggPlantravelHVO>(
                                PlantravelHVOPluginPoint.SCRIPT_DELETE);
                // TODO 在此处添加前后规则
                return processor;
        }

        @Override
        protected AggPlantravelHVO[] processBP(Object userObj,
                        AggPlantravelHVO[] clientFullVOs, AggPlantravelHVO[] originBills) {
                IPlantravelhvoMaintain operator = NCLocator.getInstance().lookup(
                                IPlantravelhvoMaintain.class);
                try {
                        operator.delete(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return clientFullVOs;
        }

}
