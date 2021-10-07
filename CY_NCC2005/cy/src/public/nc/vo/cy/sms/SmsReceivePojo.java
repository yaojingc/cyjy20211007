package nc.vo.cy.sms;

public class SmsReceivePojo {

	// 短信模板类型（参照自定义档案：cy24）
	private String smsTemplateid;
	// 消息正文
	private String smsContent;
	// 待发送手机号
	private String[] phoneNumbers;

	
	
	public String getSmsTemplateid() {
		return smsTemplateid;
	}

	public void setSmsTemplateid(String smsTemplateid) {
		this.smsTemplateid = smsTemplateid;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String[] getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String[] phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

}
