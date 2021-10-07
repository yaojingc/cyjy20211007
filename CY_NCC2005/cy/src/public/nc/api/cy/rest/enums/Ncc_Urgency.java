package nc.api.cy.rest.enums;

/**
 *紧急程度
 */
public enum Ncc_Urgency {
	
	ONE("紧急","0"),
    TWO("高","1"),
    THREE("一般","2");

    private String name;
    private String value;

    Ncc_Urgency(String name, String value) {
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
