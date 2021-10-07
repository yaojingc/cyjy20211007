
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TestpaperfileHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ITestpaperfilehvoMaintain;
import nc.vo.cy.testpaperfile.AggTestpaperfileHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_SJDA_UNSAVEBILL extends AbstractPfAction<AggTestpaperfileHVO> {

        @Override
        protected CompareAroundProcesser<AggTestpaperfileHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTestpaperfileHVO> processor = new CompareAroundProcesser<AggTestpaperfileHVO>(
                                TestpaperfileHVOPluginPoint.UNSEND_APPROVE);
                // TODO 在此处添加前后规则
                //processor.addBeforeRule(new UncommitStatusCheckRule());

                return processor;
        }

        @Override
        protected AggTestpaperfileHVO[] processBP(Object userObj,
                        AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills) {
                ITestpaperfilehvoMaintain operator = NCLocator.getInstance().lookup(
                                ITestpaperfilehvoMaintain.class);
                AggTestpaperfileHVO[] bills = null;
                try {
                        bills = operator.unsave(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
