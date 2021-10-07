package nc.api.cy.rest.entity.crm.jiedaiapply;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.CrmBaseTag;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.itf.ParentPojoTagItf;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
import nc.api.cy.rest.entity.crm.refpojo.School;
/**
 * @ClassName JiedaiInfoPOJO
 * @Description TODO 客情接待申请单基本信息
 * @Author huangcong
 * @Date 2021/8/16 16:07
 * @Version 1.0
 **/
public class JiedaiInfoPOJO implements ParentPojoTagItf{
 /**
     * 客情接待申请单主键
 */
private String pk_jiedaiapply;

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
 * 考察目的
 */
private String destination;

/**
 * 备注
 */
private String vnote;

/**
 * 交通工具
 */
private String traffic;

/**
 * 学校规模
 */
private String standard;

/**
 * 升学率
 */
private String rate;

/**
 * 到访人数
 */
private String visitnum;

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

public String getPk_jiedaiapply() {
	return pk_jiedaiapply;
}

public void setPk_jiedaiapply(String pk_jiedaiapply) {
	this.pk_jiedaiapply = pk_jiedaiapply;
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

public String getDestination() {
	return destination;
}

public void setDestination(String destination) {
	this.destination = destination;
}

public String getVnote() {
	return vnote;
}

public void setVnote(String vnote) {
	this.vnote = vnote;
}

public String getTraffic() {
	return traffic;
}

public void setTraffic(String traffic) {
	this.traffic = traffic;
}

public String getStandard() {
	return standard;
}

public void setStandard(String standard) {
	this.standard = standard;
}

public String getRate() {
	return rate;
}

public void setRate(String rate) {
	this.rate = rate;
}

public String getVisitnum() {
	return visitnum;
}

public void setVisitnum(String visitnum) {
	this.visitnum = visitnum;
}


}
