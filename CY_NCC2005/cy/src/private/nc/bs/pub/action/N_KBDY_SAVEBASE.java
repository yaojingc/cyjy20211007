
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.ClsscheduleHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.IClsschedulehvoMaintain;
import nc.vo.cy.clsschedule.AggClsscheduleHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_KBDY_SAVEBASE extends AbstractPfAction<AggClsscheduleHVO> {

        @Override
        protected CompareAroundProcesser<AggClsscheduleHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggClsscheduleHVO> processor = null;
                AggClsscheduleHVO[] clientFullVOs = (AggClsscheduleHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggClsscheduleHVO>(
                                        ClsscheduleHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggClsscheduleHVO>(
                                        ClsscheduleHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggClsscheduleHVO> rule = null;

                return processor;
        }

        @Override
        protected AggClsscheduleHVO[] processBP(Object userObj,
                        AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills) {

                AggClsscheduleHVO[] bills = null;
            try {
              IClsschedulehvoMaintain operator =
                  NCLocator.getInstance().lookup(IClsschedulehvoMaintain.class);
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
