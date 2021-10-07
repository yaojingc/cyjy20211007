
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.CallcenterHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.ICallcenterhvoMaintain;
import nc.vo.cy.callcenter.AggCallcenterHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_KFTD_SAVEBASE extends AbstractPfAction<AggCallcenterHVO> {

        @Override
        protected CompareAroundProcesser<AggCallcenterHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggCallcenterHVO> processor = null;
                AggCallcenterHVO[] clientFullVOs = (AggCallcenterHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggCallcenterHVO>(
                                        CallcenterHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggCallcenterHVO>(
                                        CallcenterHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggCallcenterHVO> rule = null;

                return processor;
        }

        @Override
        protected AggCallcenterHVO[] processBP(Object userObj,
                        AggCallcenterHVO[] clientFullVOs, AggCallcenterHVO[] originBills) {

                AggCallcenterHVO[] bills = null;
            try {
              ICallcenterhvoMaintain operator =
                  NCLocator.getInstance().lookup(ICallcenterhvoMaintain.class);
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
