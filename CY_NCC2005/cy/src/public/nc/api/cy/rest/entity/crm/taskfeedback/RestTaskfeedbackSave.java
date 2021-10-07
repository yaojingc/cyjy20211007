package nc.api.cy.rest.entity.crm.taskfeedback;

import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.api.cy.rest.entity.crm.taskallocation.TaskallocationAggPOJO;

public class RestTaskfeedbackSave extends TaskfeedbackAggPOJO{
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
