package nc.api.cy.rest.entity.crm.xuefeiapply;

import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;

/**
 * @ClassName RestXuefeiSave
 * @Description TODO 学费减免更新实体信息
 * @Author huangcong
 * @Date 2021/8/14 15:42
 * @Version 1.0
 **/
public class RestXuefeiSave  extends XuefeiAggPOJO{

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
