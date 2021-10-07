
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.MsgstationHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.IMsgstationhvoMaintain;
import nc.vo.cy.msgstation.AggMsgstationHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XXZZ_SAVEBASE extends AbstractPfAction<AggMsgstationHVO> {

        @Override
        protected CompareAroundProcesser<AggMsgstationHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggMsgstationHVO> processor = null;
                AggMsgstationHVO[] clientFullVOs = (AggMsgstationHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggMsgstationHVO>(
                                        MsgstationHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggMsgstationHVO>(
                                        MsgstationHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggMsgstationHVO> rule = null;

                return processor;
        }

        @Override
        protected AggMsgstationHVO[] processBP(Object userObj,
                        AggMsgstationHVO[] clientFullVOs, AggMsgstationHVO[] originBills) {

                AggMsgstationHVO[] bills = null;
            try {
              IMsgstationhvoMaintain operator =
                  NCLocator.getInstance().lookup(IMsgstationhvoMaintain.class);
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
