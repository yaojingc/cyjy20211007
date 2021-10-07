package nc.api.cy.rest.entity.crm.refpojo;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;

/**
 * @ClassName QueryPage
 * @Description TODO 公共分页查询条件
 * @Author NCC
 * @Date 2021/7/14 15:42
 * @Version 1.0
 **/
public class RefQueryPage extends QueryPage {

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 参照编码
	 */
	private String refcode;

	public String getRefcode() {
		return refcode;
	}

	public void setRefcode(String refcode) {
		this.refcode = refcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
