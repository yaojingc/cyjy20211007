package nc.api.cy.rest.enums;

/**
 *布尔类型枚举
 */
public enum Ncc_Ufboolean {

    ONE("是","Y"),
    TWO("否","N"),;

    private String name;
    private String value;

    Ncc_Ufboolean(String name, String value) {
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
