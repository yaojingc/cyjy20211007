package nc.api.cy.rest.entity.crm.schoolarchives;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.CrmBaseTag;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.itf.ParentPojoTagItf;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;

/**
 * @ClassName SchoolPOJO
 * @Description TODO 学校基本信息实体
 * @Author NCC
 * @Date 2021/7/5 14:26
 * @Version 1.0
 **/
public class SchoolPOJO implements ParentPojoTagItf {

    /**
         * 学校基本信息主键
     */
    private String pk_school;

    /**
         * 单据日期
     */
    private String dbilldate;

    /**
         * 单据状态
     */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_BILLSTATUS)
    private RefPOJO billstatus;
    
    /**
	  * 审批状态
	 */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_BILLSTATUS)
    private RefPOJO approvestatus;

    /**
         * 单据号
     */
    private String bill_no;

    /**
         * 学校名称
     */
    private String sname;

    /**
         * 学校具体地点
     */
    private String saddress;

    /**
         * 学校电话
     */
    private String sphone;

    /**
         * 所属大区
     */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_AREA)
    private RefPOJO region;

    /**
         * 省份
     */
    @ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_REGION)
    private RefPOJO province;

    /**
         * 是否启用
     */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
    private RefPOJO isuseing;

    /**
         * 备注
     */
    private String vnote;
    
    /**
     * 开发进程
     */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_DEVELOPSITUATION)
    private RefPOJO develprocess;
    
    /**
	  * 所属大区（自定义字段参照大区自定义档案）
	 */
	@ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_AREA)
	private RefPOJO def11;
    
    /**
         * 开发进程（自定义字段参照开发进程自定义档案）
     */
    @ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_DEVELOPSITUATION)
    private RefPOJO def12;

	public String getPk_school() {
		return pk_school;
	}

	public void setPk_school(String pk_school) {
		this.pk_school = pk_school;
	}

	public String getDbilldate() {
		return dbilldate;
	}

	public void setDbilldate(String dbilldate) {
		this.dbilldate = dbilldate;
	}

	public RefPOJO getBillstatus() {
		return billstatus;
	}

	public void setBillstatus(RefPOJO billstatus) {
		this.billstatus = billstatus;
	}
	
	public RefPOJO getApprovestatus() {
		return approvestatus;
	}

	public void setApprovestatus(RefPOJO approvestatus) {
		this.approvestatus = approvestatus;
	}

	public String getBill_no() {
		return bill_no;
	}

	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	public String getSphone() {
		return sphone;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	public RefPOJO getRegion() {
		return region;
	}

	public void setRegion(RefPOJO region) {
		this.region = region;
	}
	
	public RefPOJO getDef11() {
		return def11;
	}

	public void setDef11(RefPOJO def11) {
		this.def11 = def11;
	}

	public RefPOJO getProvince() {
		return province;
	}

	public void setProvince(RefPOJO province) {
		this.province = province;
	}

	public RefPOJO getIsuseing() {
		return isuseing;
	}

	public void setIsuseing(RefPOJO isuseing) {
		this.isuseing = isuseing;
	}

	public String getVnote() {
		return vnote;
	}

	public void setVnote(String vnote) {
		this.vnote = vnote;
	}

	public RefPOJO getDevelprocess() {
		return develprocess;
	}

	public void setDevelprocess(RefPOJO develprocess) {
		this.develprocess = develprocess;
	}

	public RefPOJO getDef12() {
		return def12;
	}

	public void setDef12(RefPOJO def12) {
		this.def12 = def12;
	}
	
}
