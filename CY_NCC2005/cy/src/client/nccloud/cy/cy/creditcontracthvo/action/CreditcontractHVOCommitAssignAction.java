
package nccloud.cy.cy.creditcontracthvo.action;


import nc.bs.logging.Logger;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nccloud.vo.cy.cy.CreditcontractHVOConst;
import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.container.IRequest;
import nccloud.cy.cy.creditcontracthvo.cy.bean.BillOperatorParam;
import nccloud.cy.cy.creditcontracthvo.cy.util.CommonUtil;

public class CreditcontractHVOCommitAssignAction
    extends AbstractCommitAssignAction<AggCreditcontractHVO> {

    @Override
    public Object doAction(IRequest request) {
    // 获取前端请求参数
    BillOperatorParam operaParam = this.getRequestParam(request);
    Object result = null;
    try {
    // 根据pk查询待操作单据
    AggCreditcontractHVO[] operaVOs = this.queryBillsByPks(operaParam);
    // ts赋值
    CommonUtil.setBillsTs(operaParam.getPkMapTs(), operaVOs);
    // 对查询结果执行具体业务处理
    Object resultVOs = this.doCommit(operaVOs, operaParam.getUserObj(),
    CreditcontractHVOConst.CONST_BILLTYPE_COST);
    // 卡片页面需要返回操作后的数据到前端，列表界面操作后，前端重新调用列表查询请求，无需返回操作后数据，所以此处按照卡片处理返回结果
    if (operaVOs.length < 2) {
    result = this.buildFontResult(operaParam, resultVOs);
    }
    else {
    result = resultVOs;
    }
    }
    catch (BusinessException ex) {
    Logger.error(ex.getMessage(), ex);
    ExceptionUtils.wrapException(ex);
    }
    return result;
    }

    }