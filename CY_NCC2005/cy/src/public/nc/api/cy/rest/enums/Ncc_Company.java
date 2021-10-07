package nc.api.cy.rest.enums;

/**
 * 公司枚举
 */
public enum Ncc_Company {

    ONE("湖北词源教育投资管理有限公司","0"),
    TWO("海南漫语教育科技有限公司","1");

    private String name;
    private String value;

    Ncc_Company(String name, String value) {
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
