package nc.api.cy.rest.enums;

/**
 * 学校年级枚举
 */
public enum Ncc_Grade {

    ONE("高一","0"),
    TWO("高二","1"),
    THREE("高三","2"),
    FOUR("初一","3"),
    FIVE("初二","4"),
    SIX("初三","4"),;

    private String name;
    private String value;

    Ncc_Grade(String name, String value) {
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
