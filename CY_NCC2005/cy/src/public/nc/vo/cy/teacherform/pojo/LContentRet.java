package nc.vo.cy.teacherform.pojo;

public class LContentRet {

	private String pk_teacherform;
	private SchoolRef school;
	private BaseRef classcy;
	private String pkschool;
	private String pkclasscy;
	private String dbilldate;
	private String ageclass;

	public String getAgeclass() {
		return ageclass;
	}

	public void setAgeclass(String ageclass) {
		this.ageclass = ageclass;
	}

	public String getPk_teacherform() {
		return pk_teacherform;
	}

	public void setPk_teacherform(String pk_teacherform) {
		this.pk_teacherform = pk_teacherform;
	}

	public SchoolRef getSchool() {
		return school;
	}

	public void setSchool(SchoolRef school) {
		this.school = school;
	}

	public BaseRef getClasscy() {
		return classcy;
	}

	public void setClasscy(BaseRef classcy) {
		this.classcy = classcy;
	}

	public String getPkschool() {
		return pkschool;
	}

	public void setPkschool(String pkschool) {
		this.pkschool = pkschool;
	}

	public String getPkclasscy() {
		return pkclasscy;
	}

	public void setPkclasscy(String pkclasscy) {
		this.pkclasscy = pkclasscy;
	}

	public String getDbilldate() {
		return dbilldate;
	}

	public void setDbilldate(String dbilldate) {
		this.dbilldate = dbilldate;
	}

}
