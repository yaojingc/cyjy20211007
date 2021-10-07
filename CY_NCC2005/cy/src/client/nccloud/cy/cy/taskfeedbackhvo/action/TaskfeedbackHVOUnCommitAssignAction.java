
package nccloud.cy.cy.taskfeedbackhvo.action;

import nc.bs.logging.Logger;
import nc.vo.cy.taskfeedback.AggTaskfeedbackHVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.container.IRequest;
import nccloud.cy.cy.taskfeedbackhvo.cy.bean.BillOperatorParam;
import nccloud.cy.cy.taskfeedbackhvo.cy.util.CommonUtil;

public class TaskfeedbackHVOUnCommitAssignAction
    extends AbstractUnCommitAssignAction<AggTaskfeedbackHVO> {

  @Override
  public Object doAction(IRequest request) {
    BillOperatorParam operaParam = this.getRequestParam(request);
    Object result = null;
    try {
  AggTaskfeedbackHVO[] operaVOs = this.queryBillsByPks(operaParam);
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
