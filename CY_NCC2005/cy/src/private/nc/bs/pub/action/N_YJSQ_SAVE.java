
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.YejiapplyHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.IYejiapplyhvoMaintain;
import nc.vo.cy.yejiapply.AggYejiapplyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_YJSQ_SAVE extends AbstractPfAction<AggYejiapplyHVO> {

        protected CompareAroundProcesser<AggYejiapplyHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggYejiapplyHVO> processor = new CompareAroundProcesser<AggYejiapplyHVO>(
                YejiapplyHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggYejiapplyHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggYejiapplyHVO[] processBP(Object userObj,
                        AggYejiapplyHVO[] clientFullVOs, AggYejiapplyHVO[] originBills) {
                IYejiapplyhvoMaintain operator = NCLocator.getInstance().lookup(
                                IYejiapplyhvoMaintain.class);
                AggYejiapplyHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
