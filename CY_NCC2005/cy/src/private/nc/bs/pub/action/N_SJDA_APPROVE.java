
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TestpaperfileHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ITestpaperfilehvoMaintain;
import nc.vo.cy.testpaperfile.AggTestpaperfileHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_SJDA_APPROVE extends AbstractPfAction<AggTestpaperfileHVO> {

        public N_SJDA_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggTestpaperfileHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTestpaperfileHVO> processor = new CompareAroundProcesser<AggTestpaperfileHVO>(
                                TestpaperfileHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggTestpaperfileHVO[] processBP(Object userObj,
                        AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills) {
                AggTestpaperfileHVO[] bills = null;
                ITestpaperfilehvoMaintain operator = NCLocator.getInstance().lookup(
                                ITestpaperfilehvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
