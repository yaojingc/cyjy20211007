
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.YejiapplyHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.IYejiapplyhvoMaintain;
import nc.vo.cy.yejiapply.AggYejiapplyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_YJSQ_SAVEBASE extends AbstractPfAction<AggYejiapplyHVO> {

        @Override
        protected CompareAroundProcesser<AggYejiapplyHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggYejiapplyHVO> processor = null;
                AggYejiapplyHVO[] clientFullVOs = (AggYejiapplyHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggYejiapplyHVO>(
                                        YejiapplyHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggYejiapplyHVO>(
                                        YejiapplyHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggYejiapplyHVO> rule = null;

                return processor;
        }

        @Override
        protected AggYejiapplyHVO[] processBP(Object userObj,
                        AggYejiapplyHVO[] clientFullVOs, AggYejiapplyHVO[] originBills) {

                AggYejiapplyHVO[] bills = null;
            try {
              IYejiapplyhvoMaintain operator =
                  NCLocator.getInstance().lookup(IYejiapplyhvoMaintain.class);
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
