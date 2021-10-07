package nc.api.cy.rest.entity.crm.classfilecy;

import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;

/**
 * @ClassName RestClassSave
 * @Description TODO 班级信息新增/更新实体
 * @Author NCC
 * @Date 2021/7/14 16:50
 * @Version 1.0
 **/
public class RestClassSave extends ClassAggPOJO {

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
