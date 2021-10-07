package nc.api.cy.rest.enums;

/**
 * 区域枚举
 */
public enum Ncc_Area {

    ONE("东大区","0"),
    TWO("南大区","1"),
    THREE("西大区","2"),
    FOUR("北大区","3"),
    FIVE("湖北地区","4"),;

    private String name;
    private String value;

    Ncc_Area(String name, String value) {
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
