package nccloud.cy.cy.returncosthvo.cy.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;

import nc.bs.logging.Logger;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.pub.BusinessException;
import nc.vo.wfengine.core.activity.Activity;
import nc.vo.wfengine.core.workflow.WorkflowProcess;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.itf.cy.cy.IReturncosthvoMaintain;
/**
 * 退费单据，单据当前所处流程，人员能否进行设置退费金额
 * @author csh
 *
 */
public class ChecKManIsEditReturnMoney implements ICommonAction {

	@Override
	public Object doAction(IRequest request) {
		String read = request.read();

		@SuppressWarnings("unchecked")
		Map<String,String> maps = (Map<String, String>) JSON.parse(read); 
		
		IReturncosthvoMaintain returnCostService = ServiceLocator.find(IReturncosthvoMaintain.class);
		
		try {
			boolean tag = returnCostService.checkManIsEditReturnMoney(maps.get("pk_bill"), maps.get("checkman"));
			if(tag) {
				maps.put("isEdit", "Y");//可以设置退款金额
			}else {
				maps.put("isEdit", "N");//不能设置退款金额
			}
			return maps;
		} catch (BusinessException ex) {
			 // 处理异常信息
		      Logger.error(ex);
		      ExceptionUtils.wrapException(ex);
		}
		return null;
	}

}
