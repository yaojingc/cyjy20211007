package nc.api.cy.rest.entity.crm.yejiapply;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;
/**
 * @ClassName RestYejiQuery
 * @Description TODO 星级评定(业绩申请)查询实体
 * @Author huangcong
 * @Date 2021/8/16 16:54
 * @Version 1.0
 **/
public class RestYejiQuery extends QueryPage{

	/**
	 * 学校
	 */
	private String school;
	
	/**
	 * 年级
	 */
	private String openclas;
	
	/**
	 * 业务员
	 */
	private String salesman;

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getOpenclas() {
		return openclas;
	}

	public void setOpenclas(String openclas) {
		this.openclas = openclas;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	
	
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
}
