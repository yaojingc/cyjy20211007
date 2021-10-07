package nc.vo.cy.studentfile.pojo;

/*
 * 家长学生是否绑定
 */
public class StudentBindQueryPOJO {

	// 身份证号
	private String idcard;
	// 家长手机号
	private String parentphone;
	// openid 加密微信号
	private String openid;
	
	

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getParentphone() {
		return parentphone;
	}

	public void setParentphone(String parentphone) {
		this.parentphone = parentphone;
	}

}
