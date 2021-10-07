package nc.api.cy.rest.entity.crm.taskallocation;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;
/**
 * @ClassName RestTaskallocationQuery
 * @Description TODO 任务分配查询实体
 * @Author huangcong
 * @Date 2021/8/18 22:54
 * @Version 1.0
 **/
public class RestTaskallocationQuery extends QueryPage{
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
	/**
	 * 业务员
	 */
	private String salesman;

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
}
