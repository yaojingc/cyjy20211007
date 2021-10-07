package nc.vo.cy.schoolform.pojo;

import nc.vo.cy.pojo.RefPOJO;

/*
 * 学校申请列表返回信息
 */
public class SchoolFormDetailReturnPOJO {

	// 学校主键
	private String pk_schoolf;
	// 学校名称
	private String school;
	// 详细地址
	private String address;
	// 大区
	private String ufregion;
	// 大区
	private RefPOJO region;
	// 申请人主键
	private String proposer;
	// 申请人vo
	private RefPOJO petitioner;

	public String getPk_schoolf() {
		return pk_schoolf;
	}

	public void setPk_schoolf(String pk_schoolf) {
		this.pk_schoolf = pk_schoolf;
	}

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
}
