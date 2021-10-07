package nc.api.cy.rest.entity.crm.taskallocation;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.itf.ParentPojoTagItf;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
import nc.api.cy.rest.entity.crm.refpojo.School;
/**
 * @ClassName TaskallocationDvoPOJO 
 * @Description TODO 任务分配子表信息
 * @Author huangcong
 * @Date 2021/8/3 17:00
 * @Version 1.0
 **/
public class TaskallocationDvoPOJO  {

	/*
	 * 任务分配子表主键
	 */
	private String pk_task_detail;
	
	/**
	 * 拜访日期
	 */
	private String assgindate;
	
	/**
	 * 安排工作内容
	 */
	private String task;
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	/**
	 * 拜访学校（自定义字段参照学校档案）
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_SCHOOLEXP)
	private School def1;
	
	/**
	 * 数据状态（1更新行、2新增行、3删除行）
	 */
	private String vostatus;

	public String getVostatus() {
		return vostatus;
	}

	public void setVostatus(String vostatus) {
		this.vostatus = vostatus;
	}

	public String getPk_task_detail() {
		return pk_task_detail;
	}

	public void setPk_task_detail(String pk_task_detail) {
		this.pk_task_detail = pk_task_detail;
	}

	public String getAssgindate() {
		return assgindate;
	}

	public void setAssgindate(String assgindate) {
		this.assgindate = assgindate;
	}

	public School getDef1() {
		return def1;
	}

	public void setDef1(School def1) {
		this.def1 = def1;
	}


	
}
