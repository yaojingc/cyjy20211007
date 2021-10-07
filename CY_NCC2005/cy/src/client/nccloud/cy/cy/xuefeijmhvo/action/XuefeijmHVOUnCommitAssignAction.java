
package nccloud.cy.cy.xuefeijmhvo.action;

import nc.bs.logging.Logger;
import nc.vo.cy.xuefeiapply.AggXuefeijmHVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.container.IRequest;
import nccloud.cy.cy.xuefeijmhvo.cy.bean.BillOperatorParam;
import nccloud.cy.cy.xuefeijmhvo.cy.util.CommonUtil;

public class XuefeijmHVOUnCommitAssignAction
    extends AbstractUnCommitAssignAction<AggXuefeijmHVO> {

  @Override
  public Object doAction(IRequest request) {
    BillOperatorParam operaParam = this.getRequestParam(request);
    Object result = null;
    try {
  AggXuefeijmHVO[] operaVOs = this.queryBillsByPks(operaParam);
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
