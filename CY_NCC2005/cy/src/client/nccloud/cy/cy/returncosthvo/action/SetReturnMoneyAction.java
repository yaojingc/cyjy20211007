package nccloud.cy.cy.returncosthvo.action;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import nc.bs.logging.Logger;
import nc.vo.cy.returncost.AggReturncostHVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.itf.cy.cy.IReturncosthvoMaintain;

/**
 * 设置退款金额
 * @author csh
 *
 */
public class SetReturnMoneyAction implements ICommonAction  {

	
	@Override
	public Object doAction(IRequest request) {
		String read = request.read();

		@SuppressWarnings("unchecked")
		Map<String,String> maps = (Map<String, String>) JSON.parse(read); 
		
		IReturncosthvoMaintain returnCostService = ServiceLocator.find(IReturncosthvoMaintain.class);
		
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			AggReturncostHVO aggvo = returnCostService.setReturnMoney(maps.get("pk_bill"), maps.get("return_money"));
			map.put("headvo", aggvo.getParentVO());
			map.put("success", "Y");
			return map;
		} catch (BusinessException ex) {
			 // 处理异常信息
		      Logger.error(ex);
		      ExceptionUtils.wrapException(ex);
		}
		return null;
	}

}
