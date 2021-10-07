package nc.api.cy.rest.entity.crm.schoolform;

import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;

/**
 * @ClassName RestSchoolFindSave
 * @Description TODO 新发现学校新增/更新实体
 * @Author 黎兵
 * @Date 2021/7/14 21:34
 * @Version 1.0
 **/
public class RestSchoolFindSave extends SchoolFindAggPOJO {

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
