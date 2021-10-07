
package nccloud.cy.cy.jiedaiapplyhvo.action;

import nc.bs.logging.Logger;
import nc.vo.cy.jiedaiapply.AggJiedaiapplyHVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.container.IRequest;
import nccloud.cy.cy.jiedaiapplyhvo.cy.bean.BillOperatorParam;
import nccloud.cy.cy.jiedaiapplyhvo.cy.util.CommonUtil;

public class JiedaiapplyHVOUnCommitAssignAction
    extends AbstractUnCommitAssignAction<AggJiedaiapplyHVO> {

  @Override
  public Object doAction(IRequest request) {
    BillOperatorParam operaParam = this.getRequestParam(request);
    Object result = null;
    try {
  AggJiedaiapplyHVO[] operaVOs = this.queryBillsByPks(operaParam);
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
