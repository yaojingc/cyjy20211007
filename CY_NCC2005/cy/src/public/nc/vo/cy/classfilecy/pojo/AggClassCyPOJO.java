package nc.vo.cy.classfilecy.pojo;

import nc.vo.cy.pojo.RefPOJO;
import nc.vo.cy.pojo.SchoolPOJO;
import nc.vo.cy.pojo.UserInfoPOJO;

/*
 * 学生档案更新接收数据实体
 */
public class AggClassCyPOJO {

	// 单据主键
	private String pk_classcy;
	// 单据日期 格式（YYYY-MM-DD）
	private String dbilldate;
	// 学校信息
	private SchoolPOJO school;
	// 学校信息
	private String def1;
	// 大区
	private String region;
	// 班级编码
	private String bill_no;
	// 带班开始日期
	private String startdate;
	// 带班结束日期
	private String enddate;
	// 班级人数
	private String gradenum;
	// 带课老师
	private RefPOJO teacher;
	// 小组长（年级）
	private RefPOJO gangboss;
	// 督导（年级）
	private RefPOJO supervisor;
	// 市场助理
	private RefPOJO marketass;
	// 开发人（业务员）
	private RefPOJO salesman;
	// 责任人
	private RefPOJO dutypsn;
	// 带课老师
	private String ufteacher;
	// 小组长（年级）
	private String ufgangboss;
	// 督导（年级）
	private String ufsupervisor;
	// 市场助理
	private String ufmarketass;
	// 开发人（业务员）
	private String ufsalesman;
	// 责任人
	private String ufdutypsn;
	// 班级名称
	private String clasname;
	// 开班年级
	private String openclas;
	// 缴费标准
	private String paystand;
	// 是否签订纸质协议
	private RefPOJO paperagre;
	// 是否均分
	private RefPOJO isaverage;
	// 是否签订纸质协议
	private String ufpaperagre;
	// 是否均分
	private String ufisaverage;
	// 艺术均分
	private String artaverage;
	// 文化均分
	private String culaverage;
	// 英语均分
	private String engaverage;
	// 经费预算
	private String budget;
	// 备注
	private String vnote;

	public SchoolPOJO getSchool() {
		return school;
	}

	public void setSchool(SchoolPOJO school) {
		this.school = school;
	}

	public String getDef1() {
		return def1;
	}

	public void setDef1(String def1) {
		this.def1 = def1;
	}

	public String getPk_classcy() {
		return pk_classcy;
	}

	public String getUfteacher() {
		return ufteacher;
	}

	public void setUfteacher(String ufteacher) {
		this.ufteacher = ufteacher;
	}

	public String getUfgangboss() {
		return ufgangboss;
	}

	public void setUfgangboss(String ufgangboss) {
		this.ufgangboss = ufgangboss;
	}

	public String getUfsupervisor() {
		return ufsupervisor;
	}

	public void setUfsupervisor(String ufsupervisor) {
		this.ufsupervisor = ufsupervisor;
	}

	public String getUfmarketass() {
		return ufmarketass;
	}

	public void setUfmarketass(String ufmarketass) {
		this.ufmarketass = ufmarketass;
	}

	public String getUfsalesman() {
		return ufsalesman;
	}

	public void setUfsalesman(String ufsalesman) {
		this.ufsalesman = ufsalesman;
	}

	public String getUfdutypsn() {
		return ufdutypsn;
	}

	public void setUfdutypsn(String ufdutypsn) {
		this.ufdutypsn = ufdutypsn;
	}

	public String getPaystand() {
		return paystand;
	}

	public void setPaystand(String paystand) {
		this.paystand = paystand;
	}

	public RefPOJO getIsaverage() {
		return isaverage;
	}

	public void setIsaverage(RefPOJO isaverage) {
		this.isaverage = isaverage;
	}

	public String getUfpaperagre() {
		return ufpaperagre;
	}

	public void setUfpaperagre(String ufpaperagre) {
		this.ufpaperagre = ufpaperagre;
	}

	public String getUfisaverage() {
		return ufisaverage;
	}

	public void setUfisaverage(String ufisaverage) {
		this.ufisaverage = ufisaverage;
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

	public RefPOJO getPaperagre() {
		return paperagre;
	}

	public void setPaperagre(RefPOJO paperagre) {
		this.paperagre = paperagre;
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
}
