
package nccloud.cy.cy.guidehvo.action;

import nc.bs.logging.Logger;
import nc.vo.cy.guide.AggGuideHVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.container.IRequest;
import nccloud.cy.cy.guidehvo.cy.bean.BillOperatorParam;
import nccloud.cy.cy.guidehvo.cy.util.CommonUtil;

public class GuideHVOUnCommitAssignAction
    extends AbstractUnCommitAssignAction<AggGuideHVO> {

  @Override
  public Object doAction(IRequest request) {
    BillOperatorParam operaParam = this.getRequestParam(request);
    Object result = null;
    try {
  AggGuideHVO[] operaVOs = this.queryBillsByPks(operaParam);
      CommonUtil.setBillsTs(operaParam.getPkMapTs(), operaVOs);
      Object resultVOs = this.unCommit(operaVOs);
      result = this.buildFontResult(operaParam, resultVOs);
    }
    catch (BusinessException ex) {
      Logger.error(ex.getMessage(), ex);
      ExceptionUtils.wrapException(ex);
    }
    return result;
  }

}
