package nc.api.cy.rest.enums;

/**
 * 开发进度枚举
 */
public enum Ncc_Developsituation {
	
	ONE("已开发","0"),
    TWO("未开发","1"),
    THREE("开发中","2");

    private String name;
    private String value;

    Ncc_Developsituation(String name, String value) {
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
