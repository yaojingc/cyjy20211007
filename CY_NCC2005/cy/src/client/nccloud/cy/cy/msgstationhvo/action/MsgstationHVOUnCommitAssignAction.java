
package nccloud.cy.cy.msgstationhvo.action;

import nc.bs.logging.Logger;
import nc.vo.cy.msgstation.AggMsgstationHVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.container.IRequest;
import nccloud.cy.cy.msgstationhvo.cy.bean.BillOperatorParam;
import nccloud.cy.cy.msgstationhvo.cy.util.CommonUtil;

public class MsgstationHVOUnCommitAssignAction
    extends AbstractUnCommitAssignAction<AggMsgstationHVO> {

  @Override
  public Object doAction(IRequest request) {
    BillOperatorParam operaParam = this.getRequestParam(request);
    Object result = null;
    try {
  AggMsgstationHVO[] operaVOs = this.queryBillsByPks(operaParam);
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
