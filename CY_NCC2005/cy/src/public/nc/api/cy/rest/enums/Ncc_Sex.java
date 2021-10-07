package nc.api.cy.rest.enums;

/**
 *性别枚举
 */
public enum Ncc_Sex {

    ONE("男","1"),
    TWO("女","2"),;

    private String name;
    private String value;

    Ncc_Sex(String name, String value) {
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
