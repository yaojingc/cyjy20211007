
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.XuefeijmHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.IXuefeijmhvoMaintain;
import nc.vo.cy.xuefeiapply.AggXuefeijmHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XFJM_SAVEBASE extends AbstractPfAction<AggXuefeijmHVO> {

        @Override
        protected CompareAroundProcesser<AggXuefeijmHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggXuefeijmHVO> processor = null;
                AggXuefeijmHVO[] clientFullVOs = (AggXuefeijmHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggXuefeijmHVO>(
                                        XuefeijmHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggXuefeijmHVO>(
                                        XuefeijmHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggXuefeijmHVO> rule = null;

                return processor;
        }

        @Override
        protected AggXuefeijmHVO[] processBP(Object userObj,
                        AggXuefeijmHVO[] clientFullVOs, AggXuefeijmHVO[] originBills) {

                AggXuefeijmHVO[] bills = null;
            try {
              IXuefeijmhvoMaintain operator =
                  NCLocator.getInstance().lookup(IXuefeijmhvoMaintain.class);
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
