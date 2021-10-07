package nc.api.cy.rest.entity.crm.xuefeiapply;

import nc.api.cy.rest.annotation.ZdyClassTagAnnotation;
import nc.api.cy.rest.annotation.ZdyFieldTagAnnotation;
import nc.api.cy.rest.entity.crm.itf.DeatilTagItf;
import nc.api.cy.rest.entity.crm.yejiapply.YejiInfoPOJO;
/**
 * @ClassName XuefeiAggPOJO
 * @Description TODO 学费减免查询响应实体
 * @Author huangcong
 * @Date 2021/8/14 16:07
 * @Version 1.0
 **/
@ZdyClassTagAnnotation(isMultiEntity = true)
public class XuefeiAggPOJO implements DeatilTagItf {

	/**
	 * 业绩申请单主表信息
	 */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.xuefeiapply.XuefeijmHVO")
	private XuefeiInfoPOJO xuefeiinfo;

	public XuefeiInfoPOJO getXuefeiinfo() {
		return xuefeiinfo;
	}

	public void setXuefeiinfo(XuefeiInfoPOJO xuefeiinfo) {
		this.xuefeiinfo = xuefeiinfo;
	}	
	
}
