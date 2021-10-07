package nc.vo.cy.schoolform.pojo;

import nc.vo.cy.pojo.RefPOJO;

/*
 * 学校发现申请详情数据
 */
public class SchoolFormCardReturnPOJO {

	// 学校主键
	private String pk_schoolf;
	// 学校
	private String school;
	// 大区
	private String ufregion;
	// 大区
	private RefPOJO region;
	// 详细地址
	private String address;
	// 申请人主键
	private String proposer;
	// 申请人主键
	private RefPOJO petitioner;
	// 备注
	private String vnote;
	// 特殊情况说明
	private String express;

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public RefPOJO getPetitioner() {
		return petitioner;
	}

	public void setPetitioner(RefPOJO petitioner) {
		this.petitioner = petitioner;
	}

	public String getPk_schoolf() {
		return pk_schoolf;
	}

	public void setPk_schoolf(String pk_schoolf) {
		this.pk_schoolf = pk_schoolf;
	}

	public String getUfregion() {
		return ufregion;
	}

	public void setUfregion(String ufregion) {
		this.ufregion = ufregion;
	}

	public RefPOJO getRegion() {
		return region;
	}

	public void setRegion(RefPOJO region) {
		this.region = region;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	public String getVnote() {
		return vnote;
	}

	public void setVnote(String vnote) {
		this.vnote = vnote;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}
}
