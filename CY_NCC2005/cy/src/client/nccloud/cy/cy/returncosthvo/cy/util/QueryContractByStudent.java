package nccloud.cy.cy.returncosthvo.cy.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import nc.bs.logging.Logger;
import nc.vo.fct.ar.entity.AggCtArVO;
import nc.vo.fct.ar.entity.CtArVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.itf.cy.cy.IReturncosthvoMaintain;

/**
 * 通过退费申请单上学生信息找到学生的收款合同信息数据返回给前端
 * @author csh
 *
 */
public class QueryContractByStudent implements ICommonAction  {
	
	@Override
	public Object doAction(IRequest request) {
		String read = request.read();

		@SuppressWarnings("unchecked")
		Map<String,String> maps = (Map<String, String>) JSON.parse(read);
		
		IReturncosthvoMaintain returnCostService = ServiceLocator.find(IReturncosthvoMaintain.class);
		
		try {
			Map<String, Object> mapData = new HashMap<String, Object>();
			AggCtArVO aggvo = returnCostService.getAggCtArVOByStudent(maps.get("pk_customer"));
			
			if(aggvo!=null) {
				CtArVO headvo = aggvo.getParentVO();
				mapData.put("ctarvo", headvo);
//				mapData.put("ntotalorigmny", headvo.getNtotalorigmny()==null?"":headvo.getNtotalorigmny().toString());//合同总金额
//				mapData.put("norigpshamount", headvo.getNorigpshamount()==null?"":headvo.getNorigpshamount().toString());//已交学费\合同信息累计收款金额
//				mapData.put("norigcopamount", headvo.getNorigcopamount()==null?"":headvo.getNorigcopamount().toString());//收款合同累计应收金额
			}
			return mapData;
		} catch (BusinessException ex) {
			 // 处理异常信息
		      Logger.error(ex);
		      ExceptionUtils.wrapException(ex);
		}
		return null;
	}

}
