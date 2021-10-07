package nc.api.cy.rest.entity;

//红票
public class RedInvoice {
	private String FPQQLSH;// 发票请求流水号：是
	private String fpDm;// 蓝字发票代码：是
	private String fpHm;// 蓝字发票号码：是
	private String ORGCODE;// 开票点编码
	private String KPR;// 开票人
	private String SKR;// 收款人
	private String FHR;// 复核人

	public String getFPQQLSH() {
		return FPQQLSH;
	}

	public void setFPQQLSH(String fPQQLSH) {
		FPQQLSH = fPQQLSH;
	}

	public String getFpDm() {
		return fpDm;
	}

	public void setFpDm(String fpDm) {
		this.fpDm = fpDm;
	}

	public String getFpHm() {
		return fpHm;
	}

	public void setFpHm(String fpHm) {
		this.fpHm = fpHm;
	}

	public String getORGCODE() {
		return ORGCODE;
	}

	public void setORGCODE(String oRGCODE) {
		ORGCODE = oRGCODE;
	}

	public String getKPR() {
		return KPR;
	}

	public void setKPR(String kPR) {
		KPR = kPR;
	}

	public String getSKR() {
		return SKR;
	}

	public void setSKR(String sKR) {
		SKR = sKR;
	}

	public String getFHR() {
		return FHR;
	}

	public void setFHR(String fHR) {
		FHR = fHR;
	}

}
