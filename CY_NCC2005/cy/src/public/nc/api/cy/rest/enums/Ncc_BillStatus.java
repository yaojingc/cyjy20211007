package nc.api.cy.rest.enums;

/**
 * NCC状态
 */
public enum Ncc_BillStatus {

    ONE("自由态","-1"),
    TWO("已提交","3"),
    THREE("审批中","2"),
    FOUR("审批通过","1"),
    FIVE("审批未通过","0");

    private String name;
    private String value;

    Ncc_BillStatus(String name, String value) {
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
