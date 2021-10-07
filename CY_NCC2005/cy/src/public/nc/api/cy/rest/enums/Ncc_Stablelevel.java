package nc.api.cy.rest.enums;

/**
 * 稳定级别枚举
 */
public enum Ncc_Stablelevel {

    ONE("丢失","0"),
    TWO("稳定","1"),
    THREE("不稳定","2"),;

    private String name;
    private String value;

    Ncc_Stablelevel(String name, String value) {
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
