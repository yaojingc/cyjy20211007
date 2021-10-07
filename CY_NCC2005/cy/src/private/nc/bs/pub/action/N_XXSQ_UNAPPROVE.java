
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.SchoolformHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ISchoolformhvoMaintain;
import nc.vo.cy.schoolform.AggSchoolformHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XXSQ_UNAPPROVE extends AbstractPfAction<AggSchoolformHVO> {

        @Override
        protected CompareAroundProcesser<AggSchoolformHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggSchoolformHVO> processor = new CompareAroundProcesser<AggSchoolformHVO>(
                                SchoolformHVOPluginPoint.UNAPPROVE);
                // TODO 在此处添加前后规则
                processor.addBeforeRule(new UnapproveStatusCheckRule());

                return processor;
        }

        @Override
        protected AggSchoolformHVO[] processBP(Object userObj,
                        AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills) {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                AggSchoolformHVO[] bills = null;
                try {
                        ISchoolformhvoMaintain operator = NCLocator.getInstance()
                                        .lookup(ISchoolformhvoMaintain.class);
                        bills = operator.unapprove(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
