package nc.api.cy.rest.entity.teach.course;

import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;

/**
 * @ClassName StudentInfo
 * @Description TODO 学生详细信息
 * @Author NCC
 * @Date 2021/9/2 10:30
 * @Version 1.0
 **/
public class StudentInfo {
	
	/**
	 * 学生主键
	 */
	public String pk_student;
	
	/**
	 * 姓名
	 */
	public String name;
	
	/**
	 * 身份证号
	 */
	public String idcard;
	
	/**
	 * 学生编号
	 */
	public String bill_no;
	
	/**
	 * 学校
	 */
	public RefPOJO school;
	
	/**
	 * 词源班级
	 */
	public RefPOJO classcy;
	
	/**
	 * 行政班级
	 */
	public RefPOJO classxz;

	public String getPk_student() {
		return pk_student;
	}

	public void setPk_student(String pk_student) {
		this.pk_student = pk_student;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getBill_no() {
		return bill_no;
	}

	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}

	public RefPOJO getSchool() {
		return school;
	}

	public void setSchool(RefPOJO school) {
		this.school = school;
	}

	public RefPOJO getClasscy() {
		return classcy;
	}

	public void setClasscy(RefPOJO classcy) {
		this.classcy = classcy;
	}

	public RefPOJO getClassxz() {
		return classxz;
	}

	public void setClassxz(RefPOJO classxz) {
		this.classxz = classxz;
	}
	
}
