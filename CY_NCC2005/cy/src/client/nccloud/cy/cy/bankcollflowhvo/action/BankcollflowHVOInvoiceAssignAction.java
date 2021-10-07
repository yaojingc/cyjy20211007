
package nccloud.cy.cy.bankcollflowhvo.action;

import java.util.ArrayList;
import java.util.List;

import nc.bs.logging.Logger;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.bankcollflow.AggBankcollflowHVO;
import nccloud.vo.cy.cy.BankcollflowHVOConst;
import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.container.IRequest;
import nccloud.itf.cy.cy.IBankcollflowhvoMaintain;
import nccloud.cy.cy.bankcollflowhvo.cy.bean.BillOperatorParam;
import nccloud.cy.cy.bankcollflowhvo.cy.util.CommonUtil;

public class BankcollflowHVOInvoiceAssignAction extends AbstractCommitAssignAction<AggBankcollflowHVO> {

	@Override
	public Object doAction(IRequest request) {
		// 获取前端请求参数
		BillOperatorParam operaParam = this.getRequestParam(request);
		Object result = null;
		try {
			// 根据pk查询待操作单据
			AggBankcollflowHVO[] operaVOs = this.queryBillsByPks(operaParam);
			// 对查询结果执行具体业务处理（修改已开票状态makestate）
			if (CyCommonUtils.isNotEmpty(operaVOs)) {
				// 新建一个list存放需要修改开票状态的单据主键
				List<String> pklist = new ArrayList<>();
				for (AggBankcollflowHVO aggBankcollflowHVO : operaVOs) {
					String pk_id = aggBankcollflowHVO.getParentVO().getPk_bank();
					pklist.add(pk_id);
				}
				if (CyCommonUtils.isNotEmpty(pklist)) {
					Boolean updateMakestate = CyCommonUtils.getInstance(IBankcollflowhvoMaintain.class)
							.updateMakestate(pklist);
				}
			}
		} catch (BusinessException ex) {
			Logger.error(ex.getMessage(), ex);
			ExceptionUtils.wrapException(ex);
		}
		return result;
	}

}