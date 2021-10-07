package nc.vo.cy.pojo;

/*
 * 应收单实体类
 */
public class SingleReceivablePOJO {

	// 收款合同主键
	private String hid;
	// 扣税类别
	private String ftaxtypeflag;
	// 税率
	private String ntaxrate;
	// 税码
	private String ctaxcodeid;
	// 财务组织
	private String pk_org;
	// 财务组织
	private String pk_org_v;
	// 客户
	private String customer;
	// 税额
	private String se;
	// 不含税金额
	private String bhsje;
	// 含税金额
	private String hsje;
	// 开始时间
	private String stime;
	// 结束时间
	private String etime;
	// 状态
	private String status;

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public String getHsje() {
		return hsje;
	}

	public void setHsje(String hsje) {
		this.hsje = hsje;
	}

	public String getFtaxtypeflag() {
		return ftaxtypeflag;
	}

	public void setFtaxtypeflag(String ftaxtypeflag) {
		this.ftaxtypeflag = ftaxtypeflag;
	}

	public String getNtaxrate() {
		return ntaxrate;
	}

	public void setNtaxrate(String ntaxrate) {
		this.ntaxrate = ntaxrate;
	}

	public String getCtaxcodeid() {
		return ctaxcodeid;
	}

	public void setCtaxcodeid(String ctaxcodeid) {
		this.ctaxcodeid = ctaxcodeid;
	}

	public String getPk_org_v() {
		return pk_org_v;
	}

	public void setPk_org_v(String pk_org_v) {
		this.pk_org_v = pk_org_v;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getPk_org() {
		return pk_org;
	}

	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	public String getSe() {
		return se;
	}

	public void setSe(String se) {
		this.se = se;
	}

	public String getBhsje() {
		return bhsje;
	}

	public void setBhsje(String bhsje) {
		this.bhsje = bhsje;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
