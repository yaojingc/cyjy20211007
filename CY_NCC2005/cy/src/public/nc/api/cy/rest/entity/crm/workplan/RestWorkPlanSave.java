package nc.api.cy.rest.entity.crm.workplan;

import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;

/**
 * @ClassName RestWorkPlanSave
 * @Description TODO 工作计划更新实体信息
 * @Author huangcong
 * @Date 2021/8/14 15:42
 * @Version 1.0
 **/
public class RestWorkPlanSave extends WorkPlanAggPOJO {
	
	/**
	 * 用户信息
	 */
    private UserInfoByDiworkPOJO userinfo;

	public UserInfoByDiworkPOJO getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfoByDiworkPOJO userinfo) {
		this.userinfo = userinfo;
	} 

}
