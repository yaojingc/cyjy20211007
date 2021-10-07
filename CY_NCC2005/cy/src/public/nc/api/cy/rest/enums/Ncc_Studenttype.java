package nc.api.cy.rest.enums;

/**
 * 学生类型枚举
 */
public enum Ncc_Studenttype {

    ONE("普通生","0"),
    TWO("艺术生","1"),
    THREE("特长生","2"),;

    private String name;
    private String value;

    Ncc_Studenttype(String name, String value) {
        this.name = name;
        this.value = value;
    }

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}
