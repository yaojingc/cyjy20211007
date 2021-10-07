package nc.vo.cy.pojo;

import nc.vo.cy.teacherform.pojo.BaseRef;

public class StuRef  extends BaseRef {
	private String sname;//学生名称
	private String sidcard;// 学校身份证号
	private String stype;// 学校类型
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSidcard() {
		return sidcard;
	}
	public void setSidcard(String sidcard) {
		this.sidcard = sidcard;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
}
