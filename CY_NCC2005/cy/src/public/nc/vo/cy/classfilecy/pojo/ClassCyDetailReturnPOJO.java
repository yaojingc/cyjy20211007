package nc.vo.cy.classfilecy.pojo;

import nc.vo.cy.pojo.SchoolPOJO;

/*
 * 班级信息
 */
public class ClassCyDetailReturnPOJO {

	// 班级主键
	private String pk_classcy;
	// 班级编码
	private String bill_no;
	// 班级名称
	private String clasname;
	// 开班年级
	private String openclas;
	// 单据日期 格式（YYYY-MM-DD）
	private String dbilldate;
	// 学校信息
	private String def1;
	// 学校信息
	private SchoolPOJO school;

	public String getPk_classcy() {
		return pk_classcy;
	}

	public void setPk_classcy(String pk_classcy) {
		this.pk_classcy = pk_classcy;
	}

	public String getBill_no() {
		return bill_no;
	}

	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}

	public String getClasname() {
		return clasname;
	}

	public void setClasname(String clasname) {
		this.clasname = clasname;
	}

	public String getOpenclas() {
		return openclas;
	}

	public void setOpenclas(String openclas) {
		this.openclas = openclas;
	}

	public String getDbilldate() {
		return dbilldate;
	}

	public void setDbilldate(String dbilldate) {
		this.dbilldate = dbilldate;
	}

	public String getDef1() {
		return def1;
	}

	public void setDef1(String def1) {
		this.def1 = def1;
	}

	public SchoolPOJO getSchool() {
		return school;
	}

	public void setSchool(SchoolPOJO school) {
		this.school = school;
	}
}
