package nc.api.cy.rest.entity.crm.plantravel;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.CrmBaseTag;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.itf.ParentPojoTagItf;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
import nc.api.cy.rest.entity.crm.refpojo.School;

public class PlantravelInfoPOJO implements ParentPojoTagItf {

/**
    * 行程安排主键
*/
private String pk_plantravel;

/**
 * 学校档案（自定义字段参照学校档案）
 */
@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_SCHOOLEXP)
private School def1;

/**
 * 到达日期
 */
private String startdate;

/**
 * 离开日期
 */
private String enddate;

/**
 * 业务员
 */
@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_PERSONDOC)
private RefPOJO salesman;

/**
 * 到访人数
 */
private String visitnum;

/**
 * 紧急联系人
 */
private String econtract;

/**
 * 紧急联系电话
 */
private String ephone;

/**
 * 班主任会议是否举行
*/
@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
private RefPOJO istatus;

public String getPk_plantravel() {
	return pk_plantravel;
}

public void setPk_plantravel(String pk_plantravel) {
	this.pk_plantravel = pk_plantravel;
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

public RefPOJO getSalesman() {
	return salesman;
}

public void setSalesman(RefPOJO salesman) {
	this.salesman = salesman;
}

public String getVisitnum() {
	return visitnum;
}

public void setVisitnum(String visitnum) {
	this.visitnum = visitnum;
}

public String getEcontract() {
	return econtract;
}

public void setEcontract(String econtract) {
	this.econtract = econtract;
}

public String getEphone() {
	return ephone;
}

public void setEphone(String ephone) {
	this.ephone = ephone;
}

public RefPOJO getIstatus() {
	return istatus;
}

public void setIstatus(RefPOJO istatus) {
	this.istatus = istatus;
}



}
