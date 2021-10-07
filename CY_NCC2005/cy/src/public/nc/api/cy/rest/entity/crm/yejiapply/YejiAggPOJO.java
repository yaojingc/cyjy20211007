package nc.api.cy.rest.entity.crm.yejiapply;

import nc.api.cy.rest.annotation.ZdyClassTagAnnotation;
import nc.api.cy.rest.annotation.ZdyFieldTagAnnotation;
import nc.api.cy.rest.entity.crm.daily.DailyInfoPOJO;
import nc.api.cy.rest.entity.crm.itf.DeatilTagItf;
/**
 * @ClassName YejiAggPOJO
 * @Description TODO 星级评定(业绩申请)查询响应实体
 * @Author huangcong
 * @Date 2021/8/14 16:07
 * @Version 1.0
 **/
@ZdyClassTagAnnotation(isMultiEntity = true)
public class YejiAggPOJO  implements DeatilTagItf{

	/**
	 * 业绩申请单主表信息
	 */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.yejiapply.YejiapplyHVO")
	private YejiInfoPOJO yejiinfo;

	public YejiInfoPOJO getYejiinfo() {
		return yejiinfo;
	}

	public void setYejiinfo(YejiInfoPOJO yejiinfo) {
		this.yejiinfo = yejiinfo;
	}
	
}
