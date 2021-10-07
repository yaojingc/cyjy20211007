
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.SchoolformHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.ISchoolformhvoMaintain;
import nc.vo.cy.schoolform.AggSchoolformHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XXSQ_SAVEBASE extends AbstractPfAction<AggSchoolformHVO> {

        @Override
        protected CompareAroundProcesser<AggSchoolformHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggSchoolformHVO> processor = null;
                AggSchoolformHVO[] clientFullVOs = (AggSchoolformHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggSchoolformHVO>(
                                        SchoolformHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggSchoolformHVO>(
                                        SchoolformHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggSchoolformHVO> rule = null;

                return processor;
        }

        @Override
        protected AggSchoolformHVO[] processBP(Object userObj,
                        AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills) {

                AggSchoolformHVO[] bills = null;
            try {
              ISchoolformhvoMaintain operator =
                  NCLocator.getInstance().lookup(ISchoolformhvoMaintain.class);
              if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                  .getPrimaryKey())) {
                bills = operator.update(clientFullVOs, originBills);
              }
              else {
                bills = operator.insert(clientFullVOs, originBills);
              }
            }
            catch (BusinessException e) {
              ExceptionUtils.wrappBusinessException(e.getMessage());
            }
            return bills;
        }
}
