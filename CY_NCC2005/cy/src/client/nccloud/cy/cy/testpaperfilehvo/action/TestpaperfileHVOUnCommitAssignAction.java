
package nccloud.cy.cy.testpaperfilehvo.action;

import nc.bs.logging.Logger;
import nc.vo.cy.testpaperfile.AggTestpaperfileHVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.container.IRequest;
import nccloud.cy.cy.testpaperfilehvo.cy.bean.BillOperatorParam;
import nccloud.cy.cy.testpaperfilehvo.cy.util.CommonUtil;

public class TestpaperfileHVOUnCommitAssignAction
    extends AbstractUnCommitAssignAction<AggTestpaperfileHVO> {

  @Override
  public Object doAction(IRequest request) {
    BillOperatorParam operaParam = this.getRequestParam(request);
    Object result = null;
    try {
  AggTestpaperfileHVO[] operaVOs = this.queryBillsByPks(operaParam);
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
