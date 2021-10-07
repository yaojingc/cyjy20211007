package nc.utils.commonplugin.PushResponse;

public class UserDetailVO {
	private String userId;//用户id（主键） 
	private String userCode;//用户编码 
	private String userName;//用户名
	private String userMobile;//手机号 
	private String userMobileCountrycode;//手机号所属国家编码 
	private String userEmail;//邮箱
	private String userActivate;//用户是否激活 
	private String registerDate;
	private String userAvatorNew;
	private String userBigAvatorNew;
	private String userSmallAvatorNew;
	private String sysId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserMobileCountrycode() {
		return userMobileCountrycode;
	}
	public void setUserMobileCountrycode(String userMobileCountrycode) {
		this.userMobileCountrycode = userMobileCountrycode;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserActivate() {
		return userActivate;
	}
	public void setUserActivate(String userActivate) {
		this.userActivate = userActivate;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getUserAvatorNew() {
		return userAvatorNew;
	}
	public void setUserAvatorNew(String userAvatorNew) {
		this.userAvatorNew = userAvatorNew;
	}
	public String getUserBigAvatorNew() {
		return userBigAvatorNew;
	}
	public void setUserBigAvatorNew(String userBigAvatorNew) {
		this.userBigAvatorNew = userBigAvatorNew;
	}
	public String getUserSmallAvatorNew() {
		return userSmallAvatorNew;
	}
	public void setUserSmallAvatorNew(String userSmallAvatorNew) {
		this.userSmallAvatorNew = userSmallAvatorNew;
	}
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
}
