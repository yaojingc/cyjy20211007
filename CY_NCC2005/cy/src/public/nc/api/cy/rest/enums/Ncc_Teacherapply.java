package nc.api.cy.rest.enums;

/**
 * 师资申请
 */

public enum Ncc_Teacherapply {

    ONE("新学校初次开班","0"),
    TWO("老学校低年级开班","1"),
    THREE("已开班申请增派师资","2"),
    FOUR("特殊情况增派师资","3"),;

    private String name;
    private String value;

    Ncc_Teacherapply(String name, String value) {
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
