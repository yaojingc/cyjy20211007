package nc.api.cy.rest.entity.crm.jiedaiapply;

import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
/**
 * @ClassName RestJiedaiSave
 * @Description TODO 客情接待申请单新增/更新实体
 * @Author huangcong
 * @Date 2021/7/14 16:50
 * @Version 1.0
 **/
public class RestJiedaiSave extends JiedaiAggPOJO {
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
