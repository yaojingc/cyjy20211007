package nccloud.impl.sync;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.pub.BusinessException;
import nccloud.itf.cy.cy.ICreditcontracthvoMaintain;


/**
 * 家长端催费短信推送，后台任务
 * 读取后台任务参数，参数设计为计划到期日之后的多少天
 * @author yao
 */
public class DunningMsgOprator implements IHttpServletAdaptor //implements IBackgroundWorkPlugin 
{

	
	
	
//	@Override
//	public PreAlertObject executeTask(BgWorkingContext context) throws BusinessException {
//		CyCommonUtils.login();
//		// 获取后台任务前台配置的催缴费到期提醒天数
////		String days = (String) context.getKeyMap().get("days");
//		
//		String days = "10";
//		
////		CyCommonUtils.getInstance(ICreditcontracthvoMaintain.class).queryDunningContract(days);
//		
//		
//		
//		
//		
//		
//		// 学费收款合同的计划到期日  计划到期日enddate
//		
//		
//		return null;
//	}

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String days = "10";
		
		try {
			CyCommonUtils.getInstance(ICreditcontracthvoMaintain.class).queryDunningContract(days);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
