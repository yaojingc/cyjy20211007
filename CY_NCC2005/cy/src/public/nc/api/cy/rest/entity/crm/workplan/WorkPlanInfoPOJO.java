package nc.api.cy.rest.entity.crm.workplan;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.CrmBaseTag;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.itf.ParentPojoTagItf;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;

/**
 * @ClassName WorkPlanInfoPOJO
 * @Description TODO 工作计划基本信息
 * @Author huangcong
 * @Date 2021/8/3 17:00
 * @Version 1.0
 **/
public class WorkPlanInfoPOJO implements ParentPojoTagItf {
	
	/**
	 * 工作计划主键
	 */
	private String pk_workplan;
	
	
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_BILLMAKER)
	private RefPOJO billmaker;

	public RefPOJO getBillmaker() {
		return billmaker;
	}

	public void setBillmaker(RefPOJO billmaker) {
		this.billmaker = billmaker;
	}
	/**
	 * 开始日期
	 */
	private String startdate;
	
	/**
	 * 结束日期
	 */
	private String enddate;
	
	/**
	 * 业务员
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_PERSONDOC)
	private RefPOJO salesman;
	
	/**
	 * 审批状态
	 */
	
	@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_BILLSTATUS)
	private RefPOJO approvestatus;
	
	
	
	public RefPOJO getApprovestatus() {
		return approvestatus;
	}

	public void setApprovestatus(RefPOJO approvestatus) {
		this.approvestatus = approvestatus;
	}

	public String getPk_workplan() {
		return pk_workplan;
	}

	public void setPk_workplan(String pk_workplan) {
		this.pk_workplan = pk_workplan;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public RefPOJO getSalesman() {
		return salesman;
	}

	public void setSalesman(RefPOJO salesman) {
		this.salesman = salesman;
	}


	
}
