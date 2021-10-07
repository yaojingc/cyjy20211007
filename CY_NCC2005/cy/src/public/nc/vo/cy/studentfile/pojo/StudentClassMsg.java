package nc.vo.cy.studentfile.pojo;

/**
 * 
 * @author RL 学生班级信息
 */
public class StudentClassMsg {

	// 词源班级
	private String cyclass;
	// 行政班级
	private String xzclass;
	// 学生主键
	private String pk_student;

	public String getCyclass() {
		return cyclass;
	}

	public void setCyclass(String cyclass) {
		this.cyclass = cyclass;
	}

	public String getXzclass() {
		return xzclass;
	}

	public void setXzclass(String xzclass) {
		this.xzclass = xzclass;
	}

	public String getPk_student() {
		return pk_student;
	}

	public void setPk_student(String pk_student) {
		this.pk_student = pk_student;
	}
}
