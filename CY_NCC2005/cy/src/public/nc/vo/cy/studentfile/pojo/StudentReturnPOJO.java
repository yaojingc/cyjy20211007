package nc.vo.cy.studentfile.pojo;

/*
 * 学生档案返回信息
 */
public class StudentReturnPOJO {

	// 学生姓名
	private String sname;
	// 学生编号
	private String scode;
	// 学校名称
	private String schoolname;
	// 学生所在年级
	private String grade;
	// 学生所在班级（词源班级）
	private String cygrade;
	// 学生所在班级（行政班级）
	private String xzgrade;

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getScode() {
		return scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCygrade() {
		return cygrade;
	}

	public void setCygrade(String cygrade) {
		this.cygrade = cygrade;
	}

	public String getXzgrade() {
		return xzgrade;
	}

	public void setXzgrade(String xzgrade) {
		this.xzgrade = xzgrade;
	}
}
