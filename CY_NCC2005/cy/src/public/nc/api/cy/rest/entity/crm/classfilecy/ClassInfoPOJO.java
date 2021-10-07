package nc.api.cy.rest.entity.crm.classfilecy;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.CrmBaseTag;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.itf.ParentPojoTagItf;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
import nc.api.cy.rest.entity.crm.refpojo.School;

/**
 * @ClassName ClassInfoPOJO
 * @Description TODO 班级基本信息
 * @Author NCC
 * @Date 2021/7/5 17:00
 * @Version 1.0
 **/
public class ClassInfoPOJO implements ParentPojoTagItf {

    /**
         * 班级基本信息pk主键
     */
    private String pk_classcy;

    /**
         * 单据日期
     */
    private String dbilldate;

    /**
         * 所属学校
     */
    @ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_SCHOOLEXP)
    private School school;

    /**
         * 大区
     */
    private String region;

    /**
         * 班级编码
     */
    private String bill_no;

    /**
         * 带课老师
     */
    @ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_TEACHER)
    private RefPOJO teacher;

    /**
         * 带班开始日期
     */
    private String startdate;

    /**
         * 带班结束日期
     */
    private String enddate;

    /**
         * 班级人数
     */
    private String gradenum;

    /**
         * 小组长（年级）
     */
    @ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_PERSONDOC)
    private RefPOJO gangboss;

    /**
         * 督导（年级）
     */
    @ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_PERSONDOC)
    private RefPOJO supervisor;

    /**
         * 市场助理
     */
    @ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_PERSONDOC)
    private RefPOJO marketass;

    /**
         * 开发人（业务员）
     */
    @ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_PERSONDOC)
    private RefPOJO salesman;

    /**
         * 责任人
     */
    @ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_PERSONDOC)
    private RefPOJO dutypsn;

    /**
         * 班级名称
     */
    private String clasname;

    /**
         * 开班年级
     */
    private String openclas;

    /**
         * 缴费标准
     */
    private String paystand;

    /**
         * 是否签订纸质协议
     */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
    private RefPOJO paperagre;

    /**
         * 是否均分
     */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
    private RefPOJO isaverage;

    /**
         * 艺术均分
     */
    private String artaverage;

    /**
         * 文化均分
     */
    private String culaverage;

    /**
         * 英语均分
     */
    private String engaverage;

    /**
         * 经费预算
     */
    private String budget;

    /**
         * 备注
     */
    private String vnote;

	/**
	  * 是否启用
	 */
    private String isuseing;
    
    /**
	     * 合同归属
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_ORG)
	private RefPOJO contractowner;
	
	/**
	 *  所属学校（自定义字段参照学校档案）
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_SCHOOLEXP)
	private School def1;
	
	/**
	 * 所属大区（自定义字段参照大区自定义档案）
	 */
	@ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_AREA)
	private RefPOJO def10;
	
	/**
	 * 省份（自定义字段参照行政区划档案）
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_REGION)
	private RefPOJO def11;
	
	/**
	 * 年级（自定义字段参照年级自定义档案）
	 */
	@ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_SESSION)
	private RefPOJO def12;

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
	public String getPk_classcy() {
		return pk_classcy;
	}

	public void setPk_classcy(String pk_classcy) {
		this.pk_classcy = pk_classcy;
	}

	public String getDbilldate() {
		return dbilldate;
	}

	public void setDbilldate(String dbilldate) {
		this.dbilldate = dbilldate;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getBill_no() {
		return bill_no;
	}

	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}

	public RefPOJO getTeacher() {
		return teacher;
	}

	public void setTeacher(RefPOJO teacher) {
		this.teacher = teacher;
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

	public String getGradenum() {
		return gradenum;
	}

	public void setGradenum(String gradenum) {
		this.gradenum = gradenum;
	}

	public RefPOJO getGangboss() {
		return gangboss;
	}

	public void setGangboss(RefPOJO gangboss) {
		this.gangboss = gangboss;
	}

	public RefPOJO getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(RefPOJO supervisor) {
		this.supervisor = supervisor;
	}

	public RefPOJO getMarketass() {
		return marketass;
	}

	public void setMarketass(RefPOJO marketass) {
		this.marketass = marketass;
	}

	public RefPOJO getSalesman() {
		return salesman;
	}

	public void setSalesman(RefPOJO salesman) {
		this.salesman = salesman;
	}

	public RefPOJO getDutypsn() {
		return dutypsn;
	}

	public void setDutypsn(RefPOJO dutypsn) {
		this.dutypsn = dutypsn;
	}

	public String getClasname() {
		return clasname;
	}

	public void setClasname(String clasname) {
		this.clasname = clasname;
	}

	public String getOpenclas() {
		return openclas;
	}

	public void setOpenclas(String openclas) {
		this.openclas = openclas;
	}

	public String getPaystand() {
		return paystand;
	}

	public void setPaystand(String paystand) {
		this.paystand = paystand;
	}

	public RefPOJO getPaperagre() {
		return paperagre;
	}

	public void setPaperagre(RefPOJO paperagre) {
		this.paperagre = paperagre;
	}

	public RefPOJO getIsaverage() {
		return isaverage;
	}

	public void setIsaverage(RefPOJO isaverage) {
		this.isaverage = isaverage;
	}

	public String getArtaverage() {
		return artaverage;
	}

	public void setArtaverage(String artaverage) {
		this.artaverage = artaverage;
	}

	public String getCulaverage() {
		return culaverage;
	}

	public void setCulaverage(String culaverage) {
		this.culaverage = culaverage;
	}

	public String getEngaverage() {
		return engaverage;
	}

	public void setEngaverage(String engaverage) {
		this.engaverage = engaverage;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getVnote() {
		return vnote;
	}

	public void setVnote(String vnote) {
		this.vnote = vnote;
	}

	public String getIsuseing() {
		return isuseing;
	}

	public void setIsuseing(String isuseing) {
		this.isuseing = isuseing;
	}

	public RefPOJO getContractowner() {
		return contractowner;
	}

	public void setContractowner(RefPOJO contractowner) {
		this.contractowner = contractowner;
	}

	public School getDef1() {
		return def1;
	}

	public void setDef1(School def1) {
		this.def1 = def1;
	}

	public RefPOJO getDef10() {
		return def10;
	}

	public void setDef10(RefPOJO def10) {
		this.def10 = def10;
	}

	public RefPOJO getDef11() {
		return def11;
	}

	public void setDef11(RefPOJO def11) {
		this.def11 = def11;
	}

	public RefPOJO getDef12() {
		return def12;
	}

	public void setDef12(RefPOJO def12) {
		this.def12 = def12;
	}
}
