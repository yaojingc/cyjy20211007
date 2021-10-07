
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.SchoolBasicsHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ISchoolbasicshvoMaintain;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XXDA_APPROVE extends AbstractPfAction<AggSchoolBasicsHVO> {

        public N_XXDA_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggSchoolBasicsHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggSchoolBasicsHVO> processor = new CompareAroundProcesser<AggSchoolBasicsHVO>(
                                SchoolBasicsHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggSchoolBasicsHVO[] processBP(Object userObj,
                        AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills) {
                AggSchoolBasicsHVO[] bills = null;
                ISchoolbasicshvoMaintain operator = NCLocator.getInstance().lookup(
                                ISchoolbasicshvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
