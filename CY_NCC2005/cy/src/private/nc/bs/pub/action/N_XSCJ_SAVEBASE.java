
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.StumarkdataHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.IStumarkdatahvoMaintain;
import nc.vo.cy.stumarkdata.AggStumarkdataHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XSCJ_SAVEBASE extends AbstractPfAction<AggStumarkdataHVO> {

        @Override
        protected CompareAroundProcesser<AggStumarkdataHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggStumarkdataHVO> processor = null;
                AggStumarkdataHVO[] clientFullVOs = (AggStumarkdataHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggStumarkdataHVO>(
                                        StumarkdataHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggStumarkdataHVO>(
                                        StumarkdataHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggStumarkdataHVO> rule = null;

                return processor;
        }

        @Override
        protected AggStumarkdataHVO[] processBP(Object userObj,
                        AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills) {

                AggStumarkdataHVO[] bills = null;
            try {
              IStumarkdatahvoMaintain operator =
                  NCLocator.getInstance().lookup(IStumarkdatahvoMaintain.class);
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
