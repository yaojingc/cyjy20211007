
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.SchoolformHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ISchoolformhvoMaintain;
import nc.vo.cy.schoolform.AggSchoolformHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XXSQ_DELETE extends AbstractPfAction<AggSchoolformHVO> {

        @Override
        protected CompareAroundProcesser<AggSchoolformHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggSchoolformHVO> processor = new CompareAroundProcesser<AggSchoolformHVO>(
                                SchoolformHVOPluginPoint.SCRIPT_DELETE);
                // TODO 在此处添加前后规则
                return processor;
        }

        @Override
        protected AggSchoolformHVO[] processBP(Object userObj,
                        AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills) {
                ISchoolformhvoMaintain operator = NCLocator.getInstance().lookup(
                                ISchoolformhvoMaintain.class);
                try {
                        operator.delete(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return clientFullVOs;
        }

}
