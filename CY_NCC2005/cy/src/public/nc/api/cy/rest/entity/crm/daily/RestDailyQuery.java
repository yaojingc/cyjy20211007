package nc.api.cy.rest.entity.crm.daily;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;

/**
 * @ClassName RestDailyQuery
 * @Description TODO 日报信息查询实体
 * @Author huangcong
 * @Date 2021/7/5 16:56
 * @Version 1.0
 **/
public class RestDailyQuery extends QueryPage {
	
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
	  * 汇报日期
	 */
	private String reportdate;
	
	/**
	  * 任务紧急程度
	 */
	private String urgency;

	public String getReportdate() {
		return reportdate;
	}

	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

}
