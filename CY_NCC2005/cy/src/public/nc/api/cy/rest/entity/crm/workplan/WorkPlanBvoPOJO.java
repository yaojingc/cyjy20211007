package nc.api.cy.rest.entity.crm.workplan;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.CrmBaseTag;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
import nc.api.cy.rest.entity.crm.refpojo.School;

/**
 * @ClassName WorkPlanBvoPOJO
 * @Description TODO 工作计划子表信息
 * @Author huangcong
 * @Date 2021/8/3 17:00
 * @Version 1.0
 **/
public class WorkPlanBvoPOJO {
	
	/**
	 * 工作计划子表主键
	 */
	private String pk_workplan_b;
	
	/**
	 * 拜访日期
	 */
	private String visitdate;
	
	/**
	 * 拜访学校
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_SCHOOL)
	private RefPOJO school;

	/**
	 * 拜访计划/目的
	 */
	private String destination;
	
	/**
	 * 交通工具	
	 */
	private String traffic;
	
	/**
	 * 联系人
	 */
	private String linkman;
	
	/**
	 * 计划是否完成
	 */
	@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
	private RefPOJO iscompete;
	
	/**
	 * 联系电话
	 */
	private String tel;
	
	/**
	 * 数据状态（1更新行、2新增行、3删除行）
	 */
	private String vostatus;
	
	/**
	 * 学校档案（自定义字段参照学校档案）
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_SCHOOLEXP)
	private School def1;
	
	
	/**
	 * 星期
	 */
	private String week;
	

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getPk_workplan_b() {
		return pk_workplan_b;
	}

	public void setPk_workplan_b(String pk_workplan_b) {
		this.pk_workplan_b = pk_workplan_b;
	}

	public String getVisitdate() {
		return visitdate;
	}

	public void setVisitdate(String visitdate) {
		this.visitdate = visitdate;
	}

	public RefPOJO getSchool() {
		return school;
	}

	public void setSchool(RefPOJO school) {
		this.school = school;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

//	public RefPOJO getIscompeted() {
//		return iscompeted;
//	}
//
//	public void setIscompeted(RefPOJO iscompeted) {
//		this.iscompeted = iscompeted;
//	}

	public String getTel() {
		return tel;
	}

	public RefPOJO getIscompete() {
		return iscompete;
	}

	public void setIscompete(RefPOJO iscompete) {
		this.iscompete = iscompete;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getVostatus() {
		return vostatus;
	}

	public void setVostatus(String vostatus) {
		this.vostatus = vostatus;
	}

	public School getDef1() {
		return def1;
	}

	public void setDef1(School def1) {
		this.def1 = def1;
	}


}
