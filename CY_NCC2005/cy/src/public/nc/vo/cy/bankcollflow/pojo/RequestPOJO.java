package nc.vo.cy.bankcollflow.pojo;

public class RequestPOJO {

	// 业务类型
	private String bizType;
	// 签名方式
	private String signType;
	// 签名
	private String sign;
	// 商户号
	private String mchNo;
	// 开始时间 yyyy-MM-dd 00:00:00
	private String startDate;
	// 结束时间 yyyy-MM-dd 23:59:59
	private String endDate;

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getMchNo() {
		return mchNo;
	}

	public void setMchNo(String mchNo) {
		this.mchNo = mchNo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
