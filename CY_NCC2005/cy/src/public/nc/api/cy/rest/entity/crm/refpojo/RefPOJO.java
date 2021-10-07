package nc.api.cy.rest.entity.crm.refpojo;

/**
 * @ClassName RefPOJO
 * @Description TODO NCC参照公共实体
 * @Author NCC
 * @Date 2021/7/7 11:22
 * @Version 1.0
 **/
public class RefPOJO {

    /**
         * 名称
     */
    private String label;

    /**
         * 编码，枚举为空
     */
    private String code;

    /**
     * pk值或者枚举值
     */
    private String value;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
