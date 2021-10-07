
package nccloud.cy.cy.schoolformhvo.action;

import nc.bs.logging.Logger;
import nc.vo.cy.schoolform.AggSchoolformHVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.container.IRequest;
import nccloud.cy.cy.schoolformhvo.cy.bean.BillOperatorParam;
import nccloud.cy.cy.schoolformhvo.cy.util.CommonUtil;

public class SchoolformHVOUnCommitAssignAction
    extends AbstractUnCommitAssignAction<AggSchoolformHVO> {

  @Override
  public Object doAction(IRequest request) {
    BillOperatorParam operaParam = this.getRequestParam(request);
    Object result = null;
    try {
  AggSchoolformHVO[] operaVOs = this.queryBillsByPks(operaParam);
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
