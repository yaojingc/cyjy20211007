package nc.api.cy.rest.entity.crm.schoolform;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.CrmBaseTag;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.itf.ParentPojoTagItf;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;

/**
 * @ClassName SchoolFindInfoPOJO
 * @Description TODO 新发现学校基本信息实体
 * @Author 黎兵
 * @Date 2021/7/8 14:16
 * @Version 1.0
 **/
public class SchoolFindInfoPOJO implements ParentPojoTagItf {

	/**
	 * 新发现学校基本信息主键
	 */
	private String pk_schoolf;

	/**
	 * 学校名称
	 */
	private String school;

	/**
	 * 详细地点
	 */
	private String address;
	
//	/**
//	 * 所属大区
//	 */
//	@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_AREA)
//	private RefPOJO region;
	/**
	 * 所属大区
	 */
	
    @ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_AREA)
    private RefPOJO def1;
	/**
	 * 申请人
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_PERSONDOC)
	private RefPOJO proposer;
	  
    /**
	 * 省
	 */	
    @ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_REGION)
    private RefPOJO province;
    /**
	 * 市
	 */
    @ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_REGION)
    private RefPOJO city;
    /**
	 * 区
	 */
    @ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_REGION)
    private RefPOJO county;
    
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_BILLSTATUS)
	private RefPOJO approvestatus;
    
    public RefPOJO getDef1() {
		return def1;
	}

	public void setDef1(RefPOJO def1) {
		this.def1 = def1;
	}

	public RefPOJO getProvince() {
		return province;
	}

	public void setProvince(RefPOJO province) {
		this.province = province;
	}

	public RefPOJO getCity() {
		return city;
	}

	public void setCity(RefPOJO city) {
		this.city = city;
	}

	public RefPOJO getCounty() {
		return county;
	}

	public void setCounty(RefPOJO county) {
		this.county = county;
	}

	public String getSphone() {
		return sphone;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	/**
	 * 电话
	 */
	private String sphone;
	/**
	 * 备注
	 */
	private String vnote;

	/**
	 * 特殊情况说明
	 */
	private String express;

	public String getPk_schoolf() {
		return pk_schoolf;
	}

	public void setPk_schoolf(String pk_schoolf) {
		this.pk_schoolf = pk_schoolf;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public RefPOJO getProposer() {
		return proposer;
	}

	public void setProposer(RefPOJO proposer) {
		this.proposer = proposer;
	}

	public String getVnote() {
		return vnote;
	}

	public void setVnote(String vnote) {
		this.vnote = vnote;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public RefPOJO getApprovestatus() {
		return approvestatus;
	}

	public void setApprovestatus(RefPOJO approvestatus) {
		this.approvestatus = approvestatus;
	}


	
	
}
