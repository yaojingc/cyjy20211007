package nc.vo.cy.teacherform.pojo;

public class SchoolRef extends BaseRef {

	private String school;
	private String schoolregion;// 学校所属大区
	private String schooladdress;// 学校地理位置

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getSchoolregion() {
		return schoolregion;
	}

	public void setSchoolregion(String schoolregion) {
		this.schoolregion = schoolregion;
	}

	public String getSchooladdress() {
		return schooladdress;
	}

	public void setSchooladdress(String schooladdress) {
		this.schooladdress = schooladdress;
	}

}
