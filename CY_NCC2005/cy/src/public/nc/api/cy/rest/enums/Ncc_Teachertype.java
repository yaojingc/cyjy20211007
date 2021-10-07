package nc.api.cy.rest.enums;

/**
 * 老师类型枚举
 */
public enum Ncc_Teachertype {

    ONE("老师","0"),
    TWO("讲师","1"),;

    private String name;
    private String value;

    Ncc_Teachertype(String name, String value) {
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
