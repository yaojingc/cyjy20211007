package nc.api.cy.rest.entity.crm.jiedaiapply;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;
/**
 * @ClassName RestJiedaiQuery
 * @Description TODO 客情接待申请单查询实体
 * @Author huangcong
 * @Date 2021/7/5 16:56
 * @Version 1.0
 **/
public class RestJiedaiQuery extends QueryPage {

	/**
	 * 学校
	 */
	private String school;

	/**
	 * 业务员
	 */
	private String salesman;
	
	/**
	 * 制单人
	 */
	private String billmaker;
	
	public String getBillmaker() {
		return billmaker;
	}

	public void setBillmaker(String billmaker) {
		this.billmaker = billmaker;
	}
	
	
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	
	
}
