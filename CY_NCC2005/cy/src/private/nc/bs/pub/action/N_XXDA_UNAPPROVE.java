
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.SchoolBasicsHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ISchoolbasicshvoMaintain;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XXDA_UNAPPROVE extends AbstractPfAction<AggSchoolBasicsHVO> {

        @Override
        protected CompareAroundProcesser<AggSchoolBasicsHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggSchoolBasicsHVO> processor = new CompareAroundProcesser<AggSchoolBasicsHVO>(
                                SchoolBasicsHVOPluginPoint.UNAPPROVE);
                // TODO 在此处添加前后规则
                processor.addBeforeRule(new UnapproveStatusCheckRule());

                return processor;
        }

        @Override
        protected AggSchoolBasicsHVO[] processBP(Object userObj,
                        AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills) {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                AggSchoolBasicsHVO[] bills = null;
                try {
                        ISchoolbasicshvoMaintain operator = NCLocator.getInstance()
                                        .lookup(ISchoolbasicshvoMaintain.class);
                        bills = operator.unapprove(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
