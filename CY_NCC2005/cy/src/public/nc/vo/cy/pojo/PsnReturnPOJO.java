package nc.vo.cy.pojo;

public class PsnReturnPOJO {

	// 人员主键
	private String pk_psndoc;
	// 人员姓名
	private String name;
	// 人员编码
	private String code;
	// 人员所属部门
	private String ufdept;
	// 人员所属部门
	private RefPOJO dept;

	public String getUfdept() {
		return ufdept;
	}

	public void setUfdept(String ufdept) {
		this.ufdept = ufdept;
	}

	public String getPk_psndoc() {
		return pk_psndoc;
	}

	public void setPk_psndoc(String pk_psndoc) {
		this.pk_psndoc = pk_psndoc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public RefPOJO getDept() {
		return dept;
	}

	public void setDept(RefPOJO dept) {
		this.dept = dept;
	}
}
