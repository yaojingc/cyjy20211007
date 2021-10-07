package nc.api.cy.rest.entity.crm.jiedaiapply;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.CrmBaseTag;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
/**
 * @ClassName JiedaiInfoPOJO
 * @Description TODO 到访人员信息基本信息
 * @Author huangcong
 * @Date 2021/8/16 16:07
 * @Version 1.0
 **/
public class JiedaiVisitPOJO {

	 /**
     * 到访人员信息主键
 */
private String pk_visitor;

/**
     * 行号
 */
private String rowno;

/**
     * 姓名
 */
private String name;

/**
     * 职位
 */
private String position;

/**
     * 性别
 */
@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_SEX)
private RefPOJO sex;

/**
     * 电话
 */
private String phone;


/**
  * 数据状态（1更新行、2新增行、3删除行）
 */
private String vostatus;


public String getPk_visitor() {
	return pk_visitor;
}


public void setPk_visitor(String pk_visitor) {
	this.pk_visitor = pk_visitor;
}


public String getRowno() {
	return rowno;
}


public void setRowno(String rowno) {
	this.rowno = rowno;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getPosition() {
	return position;
}


public void setPosition(String position) {
	this.position = position;
}



public RefPOJO getSex() {
	return sex;
}


public void setSex(RefPOJO sex) {
	this.sex = sex;
}


public String getPhone() {
	return phone;
}


public void setPhone(String phone) {
	this.phone = phone;
}


public String getVostatus() {
	return vostatus;
}


public void setVostatus(String vostatus) {
	this.vostatus = vostatus;
}


}
