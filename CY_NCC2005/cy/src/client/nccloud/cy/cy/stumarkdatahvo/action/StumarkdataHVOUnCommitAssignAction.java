
package nccloud.cy.cy.stumarkdatahvo.action;

import nc.bs.logging.Logger;
import nc.vo.cy.stumarkdata.AggStumarkdataHVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.container.IRequest;
import nccloud.cy.cy.stumarkdatahvo.cy.bean.BillOperatorParam;
import nccloud.cy.cy.stumarkdatahvo.cy.util.CommonUtil;

public class StumarkdataHVOUnCommitAssignAction
    extends AbstractUnCommitAssignAction<AggStumarkdataHVO> {

  @Override
  public Object doAction(IRequest request) {
    BillOperatorParam operaParam = this.getRequestParam(request);
    Object result = null;
    try {
  AggStumarkdataHVO[] operaVOs = this.queryBillsByPks(operaParam);
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
