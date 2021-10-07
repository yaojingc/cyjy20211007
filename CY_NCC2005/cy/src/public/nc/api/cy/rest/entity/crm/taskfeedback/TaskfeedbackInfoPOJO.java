package nc.api.cy.rest.entity.crm.taskfeedback;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.CrmBaseTag;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.itf.ParentPojoTagItf;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
import nc.api.cy.rest.entity.crm.refpojo.School;

public class TaskfeedbackInfoPOJO  implements ParentPojoTagItf{

	/**
	 * 任务分配反馈主键
	 */
	private String pk_taskfeedback;	
	/**
	 * 指派人
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_PERSONDOC)
	private RefPOJO appointman;
	
	
	/**
	 * 被指派人
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_PERSONDOC)
	private RefPOJO appointedman;
	
	/**
	 * 实际开始时间
	 */
	private String creationtime;
	
	/**
	 * 实际开始时间
	 */
	private String startdate;
	
	/**
	 * 时间结束时间
	 */
	private String enddate;
	
	/**
	 * 计划指派开始时间
	 */
	private String planstartdate;
	
	/**
	 * 计划指派结束时间
	 */
	private String planenddate;
	
	/**
	 * 指派任务内容
	 */
	private String taskinfo;
	
	/**
	 * 指派任务反馈内容
	 */
	private String feedback;
	
	/**
	 * 学校档案（自定义字段参照学校档案）
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_SCHOOLEXP)
	private School def1;
	
	/**
	 * istatus反馈状态 (完成，未完成，逾期未完成，逾期完成)
	 */
	@ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_TASKFEEDBACK)
	private RefPOJO def2;
	
	/**
	 * 所属大区
	 */
	
    @ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_AREA)
    private RefPOJO def3;
	    
	public RefPOJO getDef2() {
		return def2;
	}

	public void setDef2(RefPOJO def2) {
		this.def2 = def2;
	}

	public RefPOJO getDef3() {
		return def3;
	}

	public void setDef3(RefPOJO def3) {
		this.def3 = def3;
	}

	public School getDef1() {
		return def1;
	}

	public void setDef1(School def1) {
		this.def1 = def1;
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
	
	public String getTaskinfo() {
		return taskinfo;
	}

	public void setTaskinfo(String taskinfo) {
		this.taskinfo = taskinfo;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getPk_taskfeedback() {
		return pk_taskfeedback;
	}

	public void setPk_taskfeedback(String pk_taskfeedback) {
		this.pk_taskfeedback = pk_taskfeedback;
	}

	public RefPOJO getAppointman() {
		return appointman;
	}

	public void setAppointman(RefPOJO appointman) {
		this.appointman = appointman;
	}

	public RefPOJO getAppointedman() {
		return appointedman;
	}

	public void setAppointedman(RefPOJO appointedman) {
		this.appointedman = appointedman;
	}

	public String getPlanstartdate() {
		return planstartdate;
	}

	public void setPlanstartdate(String planstartdate) {
		this.planstartdate = planstartdate;
	}

	public String getPlanenddate() {
		return planenddate;
	}

	public void setPlanenddate(String planenddate) {
		this.planenddate = planenddate;
	}

	public String getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(String creationtime) {
		this.creationtime = creationtime;
	}
	
	
}
