package nc.api.cy.rest.entity.crm.yejiapply;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.CrmBaseTag;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.itf.ParentPojoTagItf;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
import nc.api.cy.rest.entity.crm.refpojo.School;
/**
 * @ClassName  YejiInfoPOJO 
 * @Description TODO 星级评定(业绩申请)基本信息
 * @Author huangcong
 * @Date 2021/8/14 17:00
 * @Version 1.0
 **/
public class YejiInfoPOJO implements ParentPojoTagItf {

	/**
	 * 业绩主键
	 */
	private String pk_yejiapply;
	
	
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_BILLMAKER)
	private RefPOJO billmaker;

	public RefPOJO getBillmaker() {
		return billmaker;
	}

	public void setBillmaker(RefPOJO billmaker) {
		this.billmaker = billmaker;
	}
	
	/**
	 * 学校档案（自定义字段参照学校档案）
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_SCHOOLEXP)
	private School def1;
	
	/**
	 * 开班年级档案（自定义字段参照年级档案）
	 */
	@ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_SESSION)
	private RefPOJO def3;
	
	/**
	 *班级档案（自定义字段参照班级档案）
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_CLASSCY)
	private RefPOJO def2;
	
	/**
	 *学校类型（自定义字段参照学校类型）
	 */
	@ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_NEWOROLDSCHOOL)
	private RefPOJO def4;
	/**
	 *招生缴费人数
	 */
	private String num;
	/**
	 *缴费标准
	 */	
	private String standard;
	/**
	 *特殊情况说明 
	 */	
	private String express;	
	
	/**
	 * 业务员
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_PERSONDOC)
	private RefPOJO salesman;
		
	
	@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
	private RefPOJO icompete;
	
	
	@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
	private RefPOJO iqz;
	
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

	public String getPk_yejiapply() {
		return pk_yejiapply;
	}

	public void setPk_yejiapply(String pk_yejiapply) {
		this.pk_yejiapply = pk_yejiapply;
	}

	public School getDef1() {
		return def1;
	}

	public void setDef1(School def1) {
		this.def1 = def1;
	}

	public RefPOJO getDef3() {
		return def3;
	}

	public void setDef3(RefPOJO def3) {
		this.def3 = def3;
	}

	public RefPOJO getDef2() {
		return def2;
	}

	public void setDef2(RefPOJO def2) {
		this.def2 = def2;
	}

	public RefPOJO getDef4() {
		return def4;
	}

	public void setDef4(RefPOJO def4) {
		this.def4 = def4;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public RefPOJO getSalesman() {
		return salesman;
	}

	public void setSalesman(RefPOJO salesman) {
		this.salesman = salesman;
	}

	public RefPOJO getIcompete() {
		return icompete;
	}

	public void setIcompete(RefPOJO icompete) {
		this.icompete = icompete;
	}

	public RefPOJO getIqz() {
		return iqz;
	}

	public void setIqz(RefPOJO iqz) {
		this.iqz = iqz;
	}
	
	
	
}
