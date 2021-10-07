package nc.api.cy.rest.enums;

/**
 * 新老学校枚举
 */
public enum Ncc_Neworoldschool {
	
	ONE("老学校招生","0"),
    TWO("新学校开发","1");

    private String name;
    private String value;

    Ncc_Neworoldschool(String name, String value) {
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
