package nc.api.cy.rest.entity;

import java.util.List;

public class BlueInvoiceHead {
	private String FPQQLSH;// 发票请求流水号：必传
	private String FPLX;// 发票类型：必传
	private String XSF_NSRSBH;// 销售方纳税人识别号：必传
	private String XSF_MC;// 销售方名称
	private String XSF_DZDH;// 销售方地址、电话
	private String XSF_YHZH;// 销售方银行账号
	private String GMF_NSRSBH;// 购买方纳税人识别号：必传
	private String GMF_MC;// 购买方名称：必传
	private String GMF_DZDH;// 购买方地址、电话：必传
	private String GMF_YHZH;// 购买方银行账号：必传
	private String KPR;// 开票人：必传
	private String SKR;// 收款人：必传
	private String FHR;// 复核人：必传
	private double JSHJ;// 价税合计：必传
	private double HJJE;// 合计金额
	private double HJSE;// 合计税额
	private String BZ;// 备注：有的话，需要传
	private String LYID;// 请求来源唯一标识
	private String BMB_BBH;// 编码版本号
	private String ORGCODE;// 开票点编码
	private String WXORDERID;// 微信订单号
	private String WXAPPID;// 微信商户id
	private String WXAUTHID;// 微信批量插入卡包的授权id
	private String ZDYBZ;// 自定义备注
	private String SGBZ;// 收购标志

	private List<BlueInvoiceBody> items;

	public String getFPQQLSH() {
		return FPQQLSH;
	}

	public void setFPQQLSH(String fPQQLSH) {
		FPQQLSH = fPQQLSH;
	}

	public String getFPLX() {
		return FPLX;
	}

	public void setFPLX(String fPLX) {
		FPLX = fPLX;
	}

	public String getXSF_NSRSBH() {
		return XSF_NSRSBH;
	}

	public void setXSF_NSRSBH(String xSF_NSRSBH) {
		XSF_NSRSBH = xSF_NSRSBH;
	}

	public String getXSF_MC() {
		return XSF_MC;
	}

	public void setXSF_MC(String xSF_MC) {
		XSF_MC = xSF_MC;
	}

	public String getXSF_DZDH() {
		return XSF_DZDH;
	}

	public void setXSF_DZDH(String xSF_DZDH) {
		XSF_DZDH = xSF_DZDH;
	}

	public String getXSF_YHZH() {
		return XSF_YHZH;
	}

	public void setXSF_YHZH(String xSF_YHZH) {
		XSF_YHZH = xSF_YHZH;
	}

	public String getGMF_NSRSBH() {
		return GMF_NSRSBH;
	}

	public void setGMF_NSRSBH(String gMF_NSRSBH) {
		GMF_NSRSBH = gMF_NSRSBH;
	}

	public String getGMF_MC() {
		return GMF_MC;
	}

	public void setGMF_MC(String gMF_MC) {
		GMF_MC = gMF_MC;
	}

	public String getGMF_DZDH() {
		return GMF_DZDH;
	}

	public void setGMF_DZDH(String gMF_DZDH) {
		GMF_DZDH = gMF_DZDH;
	}

	public String getGMF_YHZH() {
		return GMF_YHZH;
	}

	public void setGMF_YHZH(String gMF_YHZH) {
		GMF_YHZH = gMF_YHZH;
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

	public double getJSHJ() {
		return JSHJ;
	}

	public void setJSHJ(double jSHJ) {
		JSHJ = jSHJ;
	}

	public double getHJJE() {
		return HJJE;
	}

	public void setHJJE(double hJJE) {
		HJJE = hJJE;
	}

	public double getHJSE() {
		return HJSE;
	}

	public void setHJSE(double hJSE) {
		HJSE = hJSE;
	}

	public String getBZ() {
		return BZ;
	}

	public void setBZ(String bZ) {
		BZ = bZ;
	}

	public String getLYID() {
		return LYID;
	}

	public void setLYID(String lYID) {
		LYID = lYID;
	}

	public String getBMB_BBH() {
		return BMB_BBH;
	}

	public void setBMB_BBH(String bMB_BBH) {
		BMB_BBH = bMB_BBH;
	}

	public String getORGCODE() {
		return ORGCODE;
	}

	public void setORGCODE(String oRGCODE) {
		ORGCODE = oRGCODE;
	}

	public String getWXORDERID() {
		return WXORDERID;
	}

	public void setWXORDERID(String wXORDERID) {
		WXORDERID = wXORDERID;
	}

	public String getWXAPPID() {
		return WXAPPID;
	}

	public void setWXAPPID(String wXAPPID) {
		WXAPPID = wXAPPID;
	}

	public String getWXAUTHID() {
		return WXAUTHID;
	}

	public void setWXAUTHID(String wXAUTHID) {
		WXAUTHID = wXAUTHID;
	}

	public String getZDYBZ() {
		return ZDYBZ;
	}

	public void setZDYBZ(String zDYBZ) {
		ZDYBZ = zDYBZ;
	}

	public String getSGBZ() {
		return SGBZ;
	}

	public void setSGBZ(String sGBZ) {
		SGBZ = sGBZ;
	}

	public List<BlueInvoiceBody> getItems() {
		return items;
	}

	public void setItems(List<BlueInvoiceBody> items) {
		this.items = items;
	}

}
