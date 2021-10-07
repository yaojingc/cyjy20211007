package nc.vo.cy.stumarkdata;

public class StudentInfoVO {
//	 select stu.sname sname,sch.sname schoolname,cla.clasname classname,cla.bill_no classno,xz.clasname xzname,psn.name thachername from cy_student stu
//	 left join cy_classcy cla on stu.def1 = cla.pk_classcy 
//	 left join cy_schoolBasics sch on  sch.pk_school = stu.def2
//	 left join cy_classxz xz on xz.PK_CLASSXZ = stu.def4
//	 left join bd_psndoc psn on psn.pk_psndoc = cla.teacher 
//	 where stu.idcard = '37010219900307683X' and stu.dr = 0 and cla.dr= 0;	
	String sname ;//学生姓名
	String schoolname ;//学校名称
	String classname ;//班级名称
	String classno ;//班级编码
	String xzname ;//行政班级
	String thachername ;//老师姓名
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSchoolname() {
		return schoolname;
	}
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getClassno() {
		return classno;
	}
	public void setClassno(String classno) {
		this.classno = classno;
	}
	public String getXzname() {
		return xzname;
	}
	public void setXzname(String xzname) {
		this.xzname = xzname;
	}
	public String getThachername() {
		return thachername;
	}
	public void setThachername(String thachername) {
		this.thachername = thachername;
	}
	
	
	
}
