package nc.api.cy.rest.entity.crm.plantravel;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.refpojo.School;

public class PlantravelKcPOJO {
	 /**
     * 到访人员信息主键
 */
private String pk_kceffect;


/**
 * 学校档案（自定义字段参照学校档案）
 */
@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_SCHOOLEXP)
private School def1;
/**
     * 行号
 */
private String rowno;

/**
     * 考察结果维护
 */
private String effectmaintain;

/**
     * 预计启动时间
 */
private String prestartdate;

/**
 * 情况说明
*/
private String infonote;

/**
 * 数据状态（1更新行、2新增行、3删除行）
*/
private String vostatus;

public String getPk_kceffect() {
	return pk_kceffect;
}

public void setPk_kceffect(String pk_kceffect) {
	this.pk_kceffect = pk_kceffect;
}

public String getRowno() {
	return rowno;
}

public void setRowno(String rowno) {
	this.rowno = rowno;
}

public String getEffectmaintain() {
	return effectmaintain;
}

public void setEffectmaintain(String effectmaintain) {
	this.effectmaintain = effectmaintain;
}

public String getPrestartdate() {
	return prestartdate;
}

public void setPrestartdate(String prestartdate) {
	this.prestartdate = prestartdate;
}

public String getInfonote() {
	return infonote;
}

public void setInfonote(String infonote) {
	this.infonote = infonote;
}

public School getDef1() {
	return def1;
}

public void setDef1(School def1) {
	this.def1 = def1;
}

public String getVostatus() {
	return vostatus;
}

public void setVostatus(String vostatus) {
	this.vostatus = vostatus;
}

}
