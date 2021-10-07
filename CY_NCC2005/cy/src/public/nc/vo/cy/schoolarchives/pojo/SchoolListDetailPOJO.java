package nc.vo.cy.schoolarchives.pojo;

import nc.vo.cy.pojo.RefPOJO;

/*
 * 学生档案返回信息
 */
public class SchoolListDetailPOJO {

	// 学校主键
	private String pk_school;
	// 学校名称
	private String sname;
	// 学校地址
	private String saddress;
	// 学校电话
	private String sphone;
	// 学校电话
	private String isuse;
	// 是否启用
	private RefPOJO isuseing;

	public String getIsuse() {
		return isuse;
	}

	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}

	public String getPk_school() {
		return pk_school;
	}

	public void setPk_school(String pk_school) {
		this.pk_school = pk_school;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	public String getSphone() {
		return sphone;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	public RefPOJO getIsuseing() {
		return isuseing;
	}

	public void setIsuseing(RefPOJO isuseing) {
		this.isuseing = isuseing;
	}
}
