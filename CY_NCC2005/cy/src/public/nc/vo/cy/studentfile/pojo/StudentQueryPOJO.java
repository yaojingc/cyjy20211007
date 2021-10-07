package nc.vo.cy.studentfile.pojo;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;

/*
 * 学生档案查询条件 huangcong
 */
public class StudentQueryPOJO  extends QueryPage {

	// 学校名称
	private String schoolname;

	// 学校主键
	private String schoolid;

	// 班级主键
	private String cygrade;

	// 学生姓名
	private String sname;

	// 学生主键
	private String sid;

	// 学生编号
	private String scode;

	// 身份证号
	private String idcard;
	
	// 邮箱
	private String email;
	
	// 家长姓名
	private String parentname;
	
	// 家长身份证号
	private String parentid;
	
	private String parentphone;
	//学校省份
	private String province;
	//学校市区
	private String city;

	

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getParentphone() {
		return parentphone;
	}

	public void setParentphone(String parentphone) {
		this.parentphone = parentphone;
	}
	

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public String getSchoolid() {
		return schoolid;
	}

	public void setSchoolid(String schoolid) {
		this.schoolid = schoolid;
	}

	public String getCygrade() {
		return cygrade;
	}

	public void setCygrade(String cygrade) {
		this.cygrade = cygrade;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getScode() {
		return scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
}
