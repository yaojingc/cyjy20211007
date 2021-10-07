package nc.api.cy.rest.entity.crm.schoolarchives;

import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;

/**
 * @ClassName RestSchoolSave
 * @Description TODO 学校更新实体信息
 * @Author NCC
 * @Date 2021/7/14 15:42
 * @Version 1.0
 **/
public class RestSchoolSave extends SchoolAggPOJO {
	
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
