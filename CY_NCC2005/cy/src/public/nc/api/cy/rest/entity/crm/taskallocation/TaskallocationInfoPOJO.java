package nc.api.cy.rest.entity.crm.taskallocation;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.itf.ParentPojoTagItf;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
import nc.api.cy.rest.entity.crm.refpojo.School;
/**
 * @ClassName  TaskallocationInfoPOJO
 * @Description TODO 任务分配基本信息
 * @Author huangcong
 * @Date 2021/8/14 17:00
 * @Version 1.0
 **/
public class TaskallocationInfoPOJO implements ParentPojoTagItf{

	/**
	 * 任务分配主键
	 */
	private String pk_taskallocation;
	
	
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_BILLMAKER)
	private RefPOJO billmaker;

	public RefPOJO getBillmaker() {
		return billmaker;
	}

	public void setBillmaker(RefPOJO billmaker) {
		this.billmaker = billmaker;
	}

	
	/**
	 * 创建单据时间
	 */
	private String creationtime;
	
	/**
	 * 业务员
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_PERSONDOC)
	private RefPOJO salesman;

	public String getPk_taskallocation() {
		return pk_taskallocation;
	}

	public void setPk_taskallocation(String pk_taskallocation) {
		this.pk_taskallocation = pk_taskallocation;
	}

	
	/**
	 * 指派开始时间
	 */
	private String assgindate;
	
	/**
	 * 指派结束时间
	 */
	private String enddate;
	
	/**
	 * 指派任务
	 */
	private String task;
	
	/**
	 * 学校档案（自定义字段参照学校档案）
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_SCHOOLEXP)
	private School def1;
	
	public School getDef1() {
		return def1;
	}

	public void setDef1(School def1) {
		this.def1 = def1;
	}

	public String getAssgindate() {
		return assgindate;
	}

	public void setAssgindate(String assgindate) {
		this.assgindate = assgindate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(String creationtime) {
		this.creationtime = creationtime;
	}

	public RefPOJO getSalesman() {
		return salesman;
	}

	public void setSalesman(RefPOJO salesman) {
		this.salesman = salesman;
	}
}
