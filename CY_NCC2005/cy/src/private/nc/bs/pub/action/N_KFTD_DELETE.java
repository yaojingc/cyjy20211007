
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.CallcenterHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ICallcenterhvoMaintain;
import nc.vo.cy.callcenter.AggCallcenterHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_KFTD_DELETE extends AbstractPfAction<AggCallcenterHVO> {

        @Override
        protected CompareAroundProcesser<AggCallcenterHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggCallcenterHVO> processor = new CompareAroundProcesser<AggCallcenterHVO>(
                                CallcenterHVOPluginPoint.SCRIPT_DELETE);
                // TODO 在此处添加前后规则
                return processor;
        }

        @Override
        protected AggCallcenterHVO[] processBP(Object userObj,
                        AggCallcenterHVO[] clientFullVOs, AggCallcenterHVO[] originBills) {
                ICallcenterhvoMaintain operator = NCLocator.getInstance().lookup(
                                ICallcenterhvoMaintain.class);
                try {
                        operator.delete(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return clientFullVOs;
        }

}
