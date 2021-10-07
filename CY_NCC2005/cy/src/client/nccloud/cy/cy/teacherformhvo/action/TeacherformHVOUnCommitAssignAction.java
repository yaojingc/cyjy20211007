
package nccloud.cy.cy.teacherformhvo.action;

import nc.bs.logging.Logger;
import nc.vo.cy.teacherform.AggTeacherformHVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.container.IRequest;
import nccloud.cy.cy.teacherformhvo.cy.bean.BillOperatorParam;
import nccloud.cy.cy.teacherformhvo.cy.util.CommonUtil;

public class TeacherformHVOUnCommitAssignAction extends AbstractUnCommitAssignAction<AggTeacherformHVO> {

	@Override
	public Object doAction(IRequest request) {
		BillOperatorParam operaParam = this.getRequestParam(request);
		Object result = null;
		try {
			AggTeacherformHVO[] operaVOs = this.queryBillsByPks(operaParam);
			CommonUtil.setBillsTs(operaParam.getPkMapTs(), operaVOs);
			Object resultVOs = this.unCommit(operaVOs);
			result = this.buildFontResult(operaParam, resultVOs);
		} catch (BusinessException ex) {
			Logger.error(ex.getMessage(), ex);
			ExceptionUtils.wrapException(ex);
		}
		return result;
	}

}
