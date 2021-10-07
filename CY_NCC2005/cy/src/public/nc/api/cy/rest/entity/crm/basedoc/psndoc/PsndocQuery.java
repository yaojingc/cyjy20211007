package nc.api.cy.rest.entity.crm.basedoc.psndoc;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;

/**
 * @ClassName QueryPage
 * @Description TODO 人员分页查询条件
 * @Author NCC
 * @Date 2021/7/14 15:42
 * @Version 1.0
 **/
public class PsndocQuery extends QueryPage {

	/**
	 * 名称
	 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
