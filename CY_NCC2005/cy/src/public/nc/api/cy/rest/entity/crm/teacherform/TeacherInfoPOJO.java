package nc.api.cy.rest.entity.crm.teacherform;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.CrmBaseTag;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.itf.ParentPojoTagItf;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
import nc.api.cy.rest.entity.crm.refpojo.School;

/**
 * @ClassName TeacherInfoPOJO
 * @Description TODO 老师、讲师申请单基本信息(0老师申请单、1讲师申请单)
 * @Author NCC
 * @Date 2021/7/14 17:21
 * @Version 1.0
 **/
public class TeacherInfoPOJO implements ParentPojoTagItf {

    /**
     * 申请老师类型(0老师申请单、1讲师申请单)
     */
	@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_TEACHERTYPE)
    private RefPOJO teachertype;

    /**
     * 老师、讲师申请单主键
     */
    private String pk_teacherform;

    /**
     * 所属学校
     */
    @ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_SCHOOLEXP)
    private School school;

    /**
     * 单据日期
     */
    private String dbilldate;

    /**
          * 班级（词源）
     */
    @ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_CLASSCY)
    private RefPOJO classcy;

    /**
         * 预计到岗时间
     */
    private String startdate;

    /**
         * 岗位结束时间
     */
    private String enddate;

    /**
         * 班级人数
     */
    private String classnum;

    /**
         * 开班年级
     */
    private String ageclass;

    /**
         * 师资申请
     */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_TEACHERAPPLY)
    private RefPOJO teacherapply;

    /**
         * 班主任会议是否举行
     */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
    private RefPOJO ctmeeting;

    /**
         * 家长会议是否举行
     */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
    private RefPOJO ptmeeting;

    /**
         * 学生会议是否举行
     */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
    private RefPOJO stmeeting;
    
    
	 /**
	     * 老师是否已分配
	 */
	@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
	private RefPOJO def6;
	
	public RefPOJO getApprovestatus() {
		return approvestatus;
	}

	public void setApprovestatus(RefPOJO approvestatus) {
		this.approvestatus = approvestatus;
	}

	/**
         * 审批状态
     */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_BILLSTATUS)
	private RefPOJO approvestatus;
	
    /**
         * 第一届学生人数及师资带班情况简介
     */
    private String teacherinfos;

    /**
         * 预计招生人数
     */
    private String predictpsn;

    /**
         * 申请师资数量
     */
    private String teachernum;

    /**
         * 是否需要安排住宿
     */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
    private RefPOJO putup;

    /**
         * 新开班对应缴费公司
     */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_COMPANY)
    private RefPOJO paycompany;

    /**
         * 备注
     */
    private String vnote;

    /**
         * 老师姓名
     */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_PERSONDOC)
	private RefPOJO teacher;

    /**
         * 变动后词源班级
     */
    private String classcyafter;

    /**
         * 工作事项说明
     */
    private String workitems;
    
    /**
     * 学校（自定义字段参照学校档案）
     */
    @ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_SCHOOLEXP)
    private School def1;
    
    /**
     * 班级（自定义字段参照词源班级档案）
     */
    @ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_CLASSCY)
    private RefPOJO def2;
    
    /**
	 * 师资申请（自定义字段参照师资申请自定义档案）
	 */
    @ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_TEACHERAPPLY)
    private RefPOJO def3;
    
    /**
	 * 年级（自定义字段参照年级自定义档案）
	 */
    @ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_SESSION)
    private RefPOJO def4;
    
    /**
	 * 缴费公司（自定义字段参照缴费公司自定义档案）
	 */
    @ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_COMPANY)
    private RefPOJO def5;
    
    
	public RefPOJO getDef6() {
		return def6;
	}

	public void setDef6(RefPOJO def6) {
		this.def6 = def6;
	}

	public RefPOJO getTeachertype() {
		return teachertype;
	}

	public void setTeachertype(RefPOJO teachertype) {
		this.teachertype = teachertype;
	}

	public String getPk_teacherform() {
		return pk_teacherform;
	}

	public void setPk_teacherform(String pk_teacherform) {
		this.pk_teacherform = pk_teacherform;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getDbilldate() {
		return dbilldate;
	}

	public void setDbilldate(String dbilldate) {
		this.dbilldate = dbilldate;
	}

	public RefPOJO getClasscy() {
		return classcy;
	}

	public void setClasscy(RefPOJO classcy) {
		this.classcy = classcy;
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

	public String getClassnum() {
		return classnum;
	}

	public void setClassnum(String classnum) {
		this.classnum = classnum;
	}

	public String getAgeclass() {
		return ageclass;
	}

	public void setAgeclass(String ageclass) {
		this.ageclass = ageclass;
	}

	public RefPOJO getTeacherapply() {
		return teacherapply;
	}

	public void setTeacherapply(RefPOJO teacherapply) {
		this.teacherapply = teacherapply;
	}

	public RefPOJO getCtmeeting() {
		return ctmeeting;
	}

	public void setCtmeeting(RefPOJO ctmeeting) {
		this.ctmeeting = ctmeeting;
	}

	public RefPOJO getPtmeeting() {
		return ptmeeting;
	}

	public void setPtmeeting(RefPOJO ptmeeting) {
		this.ptmeeting = ptmeeting;
	}

	public RefPOJO getStmeeting() {
		return stmeeting;
	}

	public void setStmeeting(RefPOJO stmeeting) {
		this.stmeeting = stmeeting;
	}

	public String getTeacherinfos() {
		return teacherinfos;
	}

	public void setTeacherinfos(String teacherinfos) {
		this.teacherinfos = teacherinfos;
	}

	public String getPredictpsn() {
		return predictpsn;
	}

	public void setPredictpsn(String predictpsn) {
		this.predictpsn = predictpsn;
	}

	public String getTeachernum() {
		return teachernum;
	}

	public void setTeachernum(String teachernum) {
		this.teachernum = teachernum;
	}

	public RefPOJO getPutup() {
		return putup;
	}

	public void setPutup(RefPOJO putup) {
		this.putup = putup;
	}

	public RefPOJO getPaycompany() {
		return paycompany;
	}

	public void setPaycompany(RefPOJO paycompany) {
		this.paycompany = paycompany;
	}

	public String getVnote() {
		return vnote;
	}

	public void setVnote(String vnote) {
		this.vnote = vnote;
	}



	public RefPOJO getTeacher() {
		return teacher;
	}

	public void setTeacher(RefPOJO teacher) {
		this.teacher = teacher;
	}

	public String getClasscyafter() {
		return classcyafter;
	}

	public void setClasscyafter(String classcyafter) {
		this.classcyafter = classcyafter;
	}

	public String getWorkitems() {
		return workitems;
	}

	public void setWorkitems(String workitems) {
		this.workitems = workitems;
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

	public RefPOJO getDef4() {
		return def4;
	}

	public void setDef4(RefPOJO def4) {
		this.def4 = def4;
	}

	public RefPOJO getDef5() {
		return def5;
	}

	public void setDef5(RefPOJO def5) {
		this.def5 = def5;
	}
	
}
