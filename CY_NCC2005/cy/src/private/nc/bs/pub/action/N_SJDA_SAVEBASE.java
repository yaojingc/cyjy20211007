
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TestpaperfileHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.ITestpaperfilehvoMaintain;
import nc.vo.cy.testpaperfile.AggTestpaperfileHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_SJDA_SAVEBASE extends AbstractPfAction<AggTestpaperfileHVO> {

        @Override
        protected CompareAroundProcesser<AggTestpaperfileHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTestpaperfileHVO> processor = null;
                AggTestpaperfileHVO[] clientFullVOs = (AggTestpaperfileHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggTestpaperfileHVO>(
                                        TestpaperfileHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggTestpaperfileHVO>(
                                        TestpaperfileHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggTestpaperfileHVO> rule = null;

                return processor;
        }

        @Override
        protected AggTestpaperfileHVO[] processBP(Object userObj,
                        AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills) {

                AggTestpaperfileHVO[] bills = null;
            try {
              ITestpaperfilehvoMaintain operator =
                  NCLocator.getInstance().lookup(ITestpaperfilehvoMaintain.class);
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
