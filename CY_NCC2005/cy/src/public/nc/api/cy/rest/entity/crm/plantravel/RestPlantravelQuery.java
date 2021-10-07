package nc.api.cy.rest.entity.crm.plantravel;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;

public class RestPlantravelQuery extends QueryPage {
	
	/**
	 * 业务员
	 */
	private String salesman;
	/**
	 * 学校档案
	 */
	private String school;
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
