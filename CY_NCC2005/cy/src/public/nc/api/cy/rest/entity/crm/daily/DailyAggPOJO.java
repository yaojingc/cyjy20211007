package nc.api.cy.rest.entity.crm.daily;

import nc.api.cy.rest.annotation.ZdyClassTagAnnotation;
import nc.api.cy.rest.annotation.ZdyFieldTagAnnotation;
import nc.api.cy.rest.entity.crm.itf.DeatilTagItf;

/**
 * @ClassName DailyAggPOJO
 * @Description TODO 日报详情查询响应实体
 * @Author huangcong
 * @Date 2021/8/3 16:07
 * @Version 1.0
 **/
@ZdyClassTagAnnotation(isMultiEntity = true)
public class DailyAggPOJO implements DeatilTagItf  {
	
	/**
	 * 日报主表信息
	 */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.daily.DailyHVO")
	private DailyInfoPOJO dailyinfo;

	public DailyInfoPOJO getDailyinfo() {
		return dailyinfo;
	}

	public void setDailyinfo(DailyInfoPOJO dailyinfo) {
		this.dailyinfo = dailyinfo;
	}
}
