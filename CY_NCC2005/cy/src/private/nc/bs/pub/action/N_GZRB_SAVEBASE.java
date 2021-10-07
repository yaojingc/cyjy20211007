
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.DailyHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.IDailyhvoMaintain;
import nc.vo.cy.daily.AggDailyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_GZRB_SAVEBASE extends AbstractPfAction<AggDailyHVO> {

        @Override
        protected CompareAroundProcesser<AggDailyHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggDailyHVO> processor = null;
                AggDailyHVO[] clientFullVOs = (AggDailyHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggDailyHVO>(
                                        DailyHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggDailyHVO>(
                                        DailyHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggDailyHVO> rule = null;

                return processor;
        }

        @Override
        protected AggDailyHVO[] processBP(Object userObj,
                        AggDailyHVO[] clientFullVOs, AggDailyHVO[] originBills) {

                AggDailyHVO[] bills = null;
            try {
              IDailyhvoMaintain operator =
                  NCLocator.getInstance().lookup(IDailyhvoMaintain.class);
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
