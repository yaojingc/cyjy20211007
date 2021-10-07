package nccloud.cy.cy.clsschedulehvo.cy.stutree;

import nc.vo.platform.workbench.model.TreeModelVO;

public class StuTreeVO extends TreeModelVO {

	private static final long serialVersionUID = 2096705502134519560L;
	private String appId;
	private String appCode;

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppCode() {
		return this.appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

}
