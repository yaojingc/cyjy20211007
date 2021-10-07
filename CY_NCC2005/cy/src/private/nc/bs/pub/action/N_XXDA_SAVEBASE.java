
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.SchoolBasicsHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.ISchoolbasicshvoMaintain;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XXDA_SAVEBASE extends AbstractPfAction<AggSchoolBasicsHVO> {

        @Override
        protected CompareAroundProcesser<AggSchoolBasicsHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggSchoolBasicsHVO> processor = null;
                AggSchoolBasicsHVO[] clientFullVOs = (AggSchoolBasicsHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggSchoolBasicsHVO>(
                                        SchoolBasicsHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggSchoolBasicsHVO>(
                                        SchoolBasicsHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggSchoolBasicsHVO> rule = null;

                return processor;
        }

        @Override
        protected AggSchoolBasicsHVO[] processBP(Object userObj,
                        AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills) {

                AggSchoolBasicsHVO[] bills = null;
            try {
              ISchoolbasicshvoMaintain operator =
                  NCLocator.getInstance().lookup(ISchoolbasicshvoMaintain.class);
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
