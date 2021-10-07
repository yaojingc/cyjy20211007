package nc.api.cy.rest.entity;

public class BlueInvoiceBody {
	private int FPHXZ;// 发票行性质
	private String XMMC;// 项目名称：必传
	private String XMBM;// 项目编码
	private String GGXH;// 规格型号
	private String DW;// 单位
	private double XMSL;// 项目数量
	private double XMDJ;// 项目单价
	private double XMJE;// 项目金额
	private double XMJSHJ;// 项目价税合计：必传
	private double SL;// 税率：必传
	private double SE;// 税额
	private String HH;// 行号
	private String ZKHHH;// 折扣行行号
	private String SPBM;// 商品编码
	private String ZXBM;// 商品自行编码
	private int YHZCBS;// 销售优惠标识
	private String LSLBS;// 零税率标识
	private String ZZSTSGL;// 优惠政策说明
	private double KCE;// 扣除额

	public int getFPHXZ() {
		return FPHXZ;
	}

	public void setFPHXZ(int fPHXZ) {
		FPHXZ = fPHXZ;
	}

	public String getXMMC() {
		return XMMC;
	}

	public void setXMMC(String xMMC) {
		XMMC = xMMC;
	}

	public String getXMBM() {
		return XMBM;
	}

	public void setXMBM(String xMBM) {
		XMBM = xMBM;
	}

	public String getGGXH() {
		return GGXH;
	}

	public void setGGXH(String gGXH) {
		GGXH = gGXH;
	}

	public String getDW() {
		return DW;
	}

	public void setDW(String dW) {
		DW = dW;
	}

	public double getXMSL() {
		return XMSL;
	}

	public void setXMSL(double xMSL) {
		XMSL = xMSL;
	}

	public double getXMDJ() {
		return XMDJ;
	}

	public void setXMDJ(double xMDJ) {
		XMDJ = xMDJ;
	}

	public double getXMJE() {
		return XMJE;
	}

	public void setXMJE(double xMJE) {
		XMJE = xMJE;
	}

	public double getXMJSHJ() {
		return XMJSHJ;
	}

	public void setXMJSHJ(double xMJSHJ) {
		XMJSHJ = xMJSHJ;
	}

	public double getSL() {
		return SL;
	}

	public void setSL(double sL) {
		SL = sL;
	}

	public double getSE() {
		return SE;
	}

	public void setSE(double sE) {
		SE = sE;
	}

	public String getHH() {
		return HH;
	}

	public void setHH(String hH) {
		HH = hH;
	}

	public String getZKHHH() {
		return ZKHHH;
	}

	public void setZKHHH(String zKHHH) {
		ZKHHH = zKHHH;
	}

	public String getSPBM() {
		return SPBM;
	}

	public void setSPBM(String sPBM) {
		SPBM = sPBM;
	}

	public String getZXBM() {
		return ZXBM;
	}

	public void setZXBM(String zXBM) {
		ZXBM = zXBM;
	}

	public int getYHZCBS() {
		return YHZCBS;
	}

	public void setYHZCBS(int yHZCBS) {
		YHZCBS = yHZCBS;
	}

	public String getLSLBS() {
		return LSLBS;
	}

	public void setLSLBS(String lSLBS) {
		LSLBS = lSLBS;
	}

	public String getZZSTSGL() {
		return ZZSTSGL;
	}

	public void setZZSTSGL(String zZSTSGL) {
		ZZSTSGL = zZSTSGL;
	}

	public double getKCE() {
		return KCE;
	}

	public void setKCE(double kCE) {
		KCE = kCE;
	}

}
