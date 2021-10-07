package nc.api.cy.rest.entity.crm.daily;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.CrmBaseTag;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.itf.ParentPojoTagItf;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
import nc.api.cy.rest.entity.crm.refpojo.School;

/**
 * @ClassName DailyInfoPOJO
 * @Description TODO 工作日报基本信息
 * @Author huangcong
 * @Date 2021/8/3 17:00
 * @Version 1.0
 **/
public class DailyInfoPOJO implements ParentPojoTagItf {
	
	/**
	 * 日报主键
	 */
	private String pk_daily;
	
	
//	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_BILLMAKER)
//	private RefPOJO billmaker;
//
//	public RefPOJO getBillmaker() {
//		return billmaker;
//	}
//
//	public void setBillmaker(RefPOJO billmaker) {
//		this.billmaker = billmaker;
//	}
	
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_BILLMAKER)
	private RefPOJO def3;

	/**
	 * 汇报日期
	 */
	private String reportdate;
	
//	/**
//	 * 任务紧急程度
//	 */
//	@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_URGENCY)
//	private RefPOJO urgency;
//	
//	/**
//	 * 拜访学校
//	 */
//	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_SCHOOL)
//	private RefPOJO school;
	
	/**
	 * 今日工作
	 */
	private String todaywork;
	
	/**
	 * 明日计划
	 */
	private String tomwork;
	
	/**
	 * 特殊事项说明
	 */
	private String explain;
	
	/**
	 * 省区经理
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_PERSONDOC)
	private RefPOJO provincem;
	
	/**
	 * 区域经理
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_PERSONDOC)
	private RefPOJO aream;
	
	/**
	 * 是否见到一号
	 */
	@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
	private RefPOJO ifsaw;
	
	/**
	  * 计划今日工作是否完成
	 */
	@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
	private RefPOJO iscompete;
	
//	/**
//	 * 新老学校
//	 */
//	@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_NEWOROLDSCHOOL)
//	private RefPOJO isnew;
	
	/**
	 * 学校档案（自定义字段参照学校档案）
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_SCHOOLEXP)
	private School def1;
	
	/**
	 * 任务紧急程度（自定义字段参照学校档案）
	 */
	@ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_URGENCY)
	private RefPOJO def2;
	
	/**
	 * 新老学校（自定义字段参照学校档案）
	 */
	@ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_NEWOROLDSCHOOL)
	private RefPOJO def9;


	public String getPk_daily() {
		return pk_daily;
	}

	public void setPk_daily(String pk_daily) {
		this.pk_daily = pk_daily;
	}

	public String getReportdate() {
		return reportdate;
	}

	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
	}


	public String getTodaywork() {
		return todaywork;
	}

	public void setTodaywork(String todaywork) {
		this.todaywork = todaywork;
	}

	public String getTomwork() {
		return tomwork;
	}

	public void setTomwork(String tomwork) {
		this.tomwork = tomwork;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public RefPOJO getProvincem() {
		return provincem;
	}

	public void setProvincem(RefPOJO provincem) {
		this.provincem = provincem;
	}

	public RefPOJO getAream() {
		return aream;
	}

	public void setAream(RefPOJO aream) {
		this.aream = aream;
	}

	public RefPOJO getIfsaw() {
		return ifsaw;
	}

	public void setIfsaw(RefPOJO ifsaw) {
		this.ifsaw = ifsaw;
	}

	public RefPOJO getIscompete() {
		return iscompete;
	}

	public void setIscompete(RefPOJO iscompete) {
		this.iscompete = iscompete;
	}



	public School getDef1() {
		return def1;
	}

	public void setDef1(School def1) {
		this.def1 = def1;
	}

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

	public RefPOJO getDef9() {
		return def9;
	}

	public void setDef9(RefPOJO def9) {
		this.def9 = def9;
	}
	
	

}
