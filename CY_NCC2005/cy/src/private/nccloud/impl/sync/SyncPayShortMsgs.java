package nccloud.impl.sync;

import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.pub.BusinessException;
import nccloud.itf.cy.cy.ICreditcontracthvoMaintain;

public class SyncPayShortMsgs  implements IBackgroundWorkPlugin{
	//定时任务短信催缴费
	@Override
	public PreAlertObject executeTask(BgWorkingContext context) throws BusinessException {
		String day = context.getKeyMap().get("到期天数")==null?"10":context.getKeyMap().get("到期天数").toString();
		if("".equals(day)) {
			//取不到数据默认10天
			day = "10";
		}
//		String days = "10";
		
		CyCommonUtils.getInstance(ICreditcontracthvoMaintain.class).queryDunningContract(day);
		
		return null;
	}

	
	

	
	
}
