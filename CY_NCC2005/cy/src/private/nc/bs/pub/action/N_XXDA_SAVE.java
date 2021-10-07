
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.SchoolBasicsHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ISchoolbasicshvoMaintain;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XXDA_SAVE extends AbstractPfAction<AggSchoolBasicsHVO> {

        protected CompareAroundProcesser<AggSchoolBasicsHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggSchoolBasicsHVO> processor = new CompareAroundProcesser<AggSchoolBasicsHVO>(
                SchoolBasicsHVOPluginPoint.SEND_APPROVE);
                // TODO 在此处添加审核前后规则
                IRule<AggSchoolBasicsHVO> rule = new CommitStatusCheckRule();
                processor.addBeforeRule(rule);
                return processor;
        }

        @Override
        protected AggSchoolBasicsHVO[] processBP(Object userObj,
                        AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills) {
                ISchoolbasicshvoMaintain operator = NCLocator.getInstance().lookup(
                                ISchoolbasicshvoMaintain.class);
                AggSchoolBasicsHVO[] bills = null;
                try {
                        bills = operator.save(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
