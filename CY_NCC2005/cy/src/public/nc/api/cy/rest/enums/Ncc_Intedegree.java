package nc.api.cy.rest.enums;

/**
 * 意向度枚举
 */
public enum Ncc_Intedegree {

    ONE("高","0"),
    TWO("较高","1"),
    THREE("一般","2"),
    FOUR("低","3");

    private String name;
    private String value;

    Ncc_Intedegree(String name, String value) {
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
