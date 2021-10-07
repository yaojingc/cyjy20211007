package nc.vo.cy.pojo;

public class SchoolPOJO {

	// 名称
	private String label;
	// pk值或者枚举值
	private String value;
	// 学校所属大区
	private String schoolregion;
	// 学校地理位置
	private String schooladdress;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
