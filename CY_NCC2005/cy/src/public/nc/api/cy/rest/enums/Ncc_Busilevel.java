package nc.api.cy.rest.enums;

/**
 * 学校商务级别枚举
 */
public enum Ncc_Busilevel {

    ONE("A","0"),
    TWO("B","1"),
    THREE("C","2");

    private String name;
    private String value;

    Ncc_Busilevel(String name, String value) {
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
