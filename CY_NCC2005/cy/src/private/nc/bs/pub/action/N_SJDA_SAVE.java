
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TestpaperfileHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ITestpaperfilehvoMaintain;
import nc.vo.cy.testpaperfile.AggTestpaperfileHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_SJDA_SAVE extends AbstractPfAction<AggTestpaperfileHVO> {

        protected CompareAroundProcesser<AggTestpaperfileHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggTestpaperfileHVO> processor = new CompareAroundProcesser<AggTestpaperfileHVO>(
                TestpaperfileHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggTestpaperfileHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggTestpaperfileHVO[] processBP(Object userObj,
                        AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills) {
                ITestpaperfilehvoMaintain operator = NCLocator.getInstance().lookup(
                                ITestpaperfilehvoMaintain.class);
                AggTestpaperfileHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
