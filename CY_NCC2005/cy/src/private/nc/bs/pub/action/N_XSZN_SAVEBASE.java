
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.GuideHVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nccloud.itf.cy.cy.IGuidehvoMaintain;
import nc.vo.cy.guide.AggGuideHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_XSZN_SAVEBASE extends AbstractPfAction<AggGuideHVO> {

        @Override
        protected CompareAroundProcesser<AggGuideHVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggGuideHVO> processor = null;
                AggGuideHVO[] clientFullVOs = (AggGuideHVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggGuideHVO>(
                                        GuideHVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggGuideHVO>(
                                        GuideHVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggGuideHVO> rule = null;

                return processor;
        }

        @Override
        protected AggGuideHVO[] processBP(Object userObj,
                        AggGuideHVO[] clientFullVOs, AggGuideHVO[] originBills) {

                AggGuideHVO[] bills = null;
            try {
              IGuidehvoMaintain operator =
                  NCLocator.getInstance().lookup(IGuidehvoMaintain.class);
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
