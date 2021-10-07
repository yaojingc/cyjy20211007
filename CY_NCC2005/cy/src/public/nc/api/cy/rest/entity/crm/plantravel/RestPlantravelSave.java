package nc.api.cy.rest.entity.crm.plantravel;

import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.api.cy.rest.entity.crm.jiedaiapply.JiedaiAggPOJO;

public class RestPlantravelSave extends PlantravelAggPOJO {
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
