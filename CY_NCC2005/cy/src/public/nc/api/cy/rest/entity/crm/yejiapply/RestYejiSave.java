package nc.api.cy.rest.entity.crm.yejiapply;

import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
/**
 * @ClassName RestYejiSave
 * @Description TODO 星级评定(业绩申请)更新实体
 * @Author huangcong
 * @Date 2021/8/14 15:42
 * @Version 1.0
 **/
public class RestYejiSave extends YejiAggPOJO {

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
