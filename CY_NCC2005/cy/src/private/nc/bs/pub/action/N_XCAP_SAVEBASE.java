
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.PlantravelHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.IPlantravelhvoMaintain;
import nc.vo.cy.plantravel.AggPlantravelHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XCAP_SAVEBASE extends AbstractPfAction<AggPlantravelHVO> {

        @Override
        protected CompareAroundProcesser<AggPlantravelHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggPlantravelHVO> processor = null;
                AggPlantravelHVO[] clientFullVOs = (AggPlantravelHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggPlantravelHVO>(
                                        PlantravelHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggPlantravelHVO>(
                                        PlantravelHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggPlantravelHVO> rule = null;

                return processor;
        }

        @Override
        protected AggPlantravelHVO[] processBP(Object userObj,
                        AggPlantravelHVO[] clientFullVOs, AggPlantravelHVO[] originBills) {

                AggPlantravelHVO[] bills = null;
            try {
              IPlantravelhvoMaintain operator =
                  NCLocator.getInstance().lookup(IPlantravelhvoMaintain.class);
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
