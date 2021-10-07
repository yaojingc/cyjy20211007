
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.SchoolformHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ISchoolformhvoMaintain;
import nc.vo.cy.schoolform.AggSchoolformHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XXSQ_APPROVE extends AbstractPfAction<AggSchoolformHVO> {

        public N_XXSQ_APPROVE() {
                super();
        }

        @Override
        protected CompareAroundProcesser<AggSchoolformHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggSchoolformHVO> processor = new CompareAroundProcesser<AggSchoolformHVO>(
                                SchoolformHVOPluginPoint.APPROVE);
                processor.addBeforeRule(new ApproveStatusCheckRule());
                return processor;
        }

        @Override
        protected AggSchoolformHVO[] processBP(Object userObj,
                        AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills) {
                AggSchoolformHVO[] bills = null;
                ISchoolformhvoMaintain operator = NCLocator.getInstance().lookup(
                                ISchoolformhvoMaintain.class);
                try {
                        bills = operator.approve(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
