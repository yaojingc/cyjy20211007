package nc.api.cy.rest.entity.crm.taskallocation;

import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
/**
 * @ClassName RestTaskallocationSave
 * @Description TODO 任务分配更新实体信息
 * @Author huangcong
 * @Date 2021/8/14 15:42
 * @Version 1.0
 **/
public class RestTaskallocationSave  extends TaskallocationAggPOJO{
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
