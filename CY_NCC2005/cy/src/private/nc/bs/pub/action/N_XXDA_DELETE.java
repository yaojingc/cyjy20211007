
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.SchoolBasicsHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nccloud.itf.cy.cy.ISchoolbasicshvoMaintain;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XXDA_DELETE extends AbstractPfAction<AggSchoolBasicsHVO> {

        @Override
        protected CompareAroundProcesser<AggSchoolBasicsHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggSchoolBasicsHVO> processor = new CompareAroundProcesser<AggSchoolBasicsHVO>(
                                SchoolBasicsHVOPluginPoint.SCRIPT_DELETE);
                // TODO 在此处添加前后规则
                return processor;
        }

        @Override
        protected AggSchoolBasicsHVO[] processBP(Object userObj,
                        AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills) {
                ISchoolbasicshvoMaintain operator = NCLocator.getInstance().lookup(
                                ISchoolbasicshvoMaintain.class);
                try {
                        operator.delete(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return clientFullVOs;
        }

}
