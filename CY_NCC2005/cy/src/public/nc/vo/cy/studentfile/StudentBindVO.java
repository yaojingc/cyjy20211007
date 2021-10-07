package nc.vo.cy.studentfile;

/*
 * 学生信息绑定
 */
public class StudentBindVO {

	// 学生姓名
	private String sname;
	// 学生身份证号
	private String idcard;
	// 班级唯一编码
	private String classno;
	// 微信手机号
	private String phoneno;
	// 邮箱
	private String mail;
	// 家长姓名  parentname
	private String parentname;
	// 家长身份证号parentid
	private String parentid;
	// 微信的openid
	private String openid;
	// 与学生关系
	private String relation;
	// 用户登录密码
	private String password;
	
	
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getClassno() {
		return classno;
	}

	public void setClassno(String classno) {
		this.classno = classno;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

}
