package nc.api.cy.rest.entity.crm.basedoc.region;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;

/**
 * @ClassName QueryPage
 * @Description TODO 行政区划分页查询条件
 * @Author NCC
 * @Date 2021/7/14 15:42
 * @Version 1.0
 **/
public class RegionQuery extends QueryPage {

	/**
	 * 名称
	 */
	private String name;

	private String pk_father;

	public String getPk_father() {
		return pk_father;
	}

	public void setPk_father(String pk_father) {
		this.pk_father = pk_father;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
