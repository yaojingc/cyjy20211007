package nc.api.cy.rest.entity.crm.teacherform;

import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;

/**
 * @ClassName RestTeacherSave
 * @Description TODO 老师、讲师申请单新增/更新实体
 * @Author NCC
 * @Date 2021/7/14 17:23
 * @Version 1.0
 **/
public class RestTeacherSave extends TeacherAggPOJO {

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
