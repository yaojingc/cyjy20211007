
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.BankcollflowHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.IBankcollflowhvoMaintain;
import nc.vo.cy.bankcollflow.AggBankcollflowHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_SKLS_SAVEBASE extends AbstractPfAction<AggBankcollflowHVO> {

        @Override
        protected CompareAroundProcesser<AggBankcollflowHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggBankcollflowHVO> processor = null;
                AggBankcollflowHVO[] clientFullVOs = (AggBankcollflowHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggBankcollflowHVO>(
                                        BankcollflowHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggBankcollflowHVO>(
                                        BankcollflowHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggBankcollflowHVO> rule = null;

                return processor;
        }

        @Override
        protected AggBankcollflowHVO[] processBP(Object userObj,
                        AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills) {

                AggBankcollflowHVO[] bills = null;
            try {
              IBankcollflowhvoMaintain operator =
                  NCLocator.getInstance().lookup(IBankcollflowhvoMaintain.class);
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
