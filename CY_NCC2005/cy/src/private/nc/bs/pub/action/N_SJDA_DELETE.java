
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TestpaperfileHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ITestpaperfilehvoMaintain;
import nc.vo.cy.testpaperfile.AggTestpaperfileHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_SJDA_DELETE extends AbstractPfAction<AggTestpaperfileHVO> {

        @Override
        protected CompareAroundProcesser<AggTestpaperfileHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTestpaperfileHVO> processor = new CompareAroundProcesser<AggTestpaperfileHVO>(
                                TestpaperfileHVOPluginPoint.SCRIPT_DELETE);
                // TODO 在此处添加前后规则
                return processor;
        }

        @Override
        protected AggTestpaperfileHVO[] processBP(Object userObj,
                        AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills) {
                ITestpaperfilehvoMaintain operator = NCLocator.getInstance().lookup(
                                ITestpaperfilehvoMaintain.class);
                try {
                        operator.delete(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return clientFullVOs;
        }

}
