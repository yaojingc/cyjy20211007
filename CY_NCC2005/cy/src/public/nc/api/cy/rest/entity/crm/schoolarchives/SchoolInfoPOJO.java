package nc.api.cy.rest.entity.crm.schoolarchives;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.CrmBaseTag;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;

/**
 * @ClassName SchoolInfoPOJO
 * @Description TODO 高管学校维护记录信息
 * @Author NCC
 * @Date 2021/7/5 16:36
 * @Version 1.0
 **/
public class SchoolInfoPOJO {

    /**
         * 高管学校维护记录信息pk主键
     */
    private String pk_top;

    /**
         * 行号
     */
    private String rowno;

    /**
         * 拜访对象
     */
    private String visitobj;

    /**
         * 解决的问题
     */
    private String solveproblem;

    /**
         * 拜访巡视情况
     */
    private String visitpatrol;

    /**
         * 稳定级别
     */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_STABLELEVEL)
    private RefPOJO stablelevel;

    /**
         * 是否可挽回
     */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
    private RefPOJO redornot;

    /**
         * 年级人数分布
     */
    private String gradepsnnum;

    /**
         * 是否与竞争机构并存
     */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
    private RefPOJO isexistside;

    /**
         * 特殊事项说明
     */
    private String specialnote;

    /**
         * 拜访日期
     */
    private String visitdate;

    /**
         * 数据状态（1更新行、2新增行、3删除行）
     */
    private String vostatus;
    
    /**
	  * 稳定级别（自定义字段参照年级自定义档案）
	 */
    @ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_STABLELEVEL)
    private RefPOJO def1;

	public String getPk_top() {
		return pk_top;
	}

	public void setPk_top(String pk_top) {
		this.pk_top = pk_top;
	}

	public String getRowno() {
		return rowno;
	}

	public void setRowno(String rowno) {
		this.rowno = rowno;
	}

	public String getVisitobj() {
		return visitobj;
	}

	public void setVisitobj(String visitobj) {
		this.visitobj = visitobj;
	}

	public String getSolveproblem() {
		return solveproblem;
	}

	public void setSolveproblem(String solveproblem) {
		this.solveproblem = solveproblem;
	}

	public String getVisitpatrol() {
		return visitpatrol;
	}

	public void setVisitpatrol(String visitpatrol) {
		this.visitpatrol = visitpatrol;
	}

	public RefPOJO getStablelevel() {
		return stablelevel;
	}

	public void setStablelevel(RefPOJO stablelevel) {
		this.stablelevel = stablelevel;
	}

	public RefPOJO getRedornot() {
		return redornot;
	}

	public void setRedornot(RefPOJO redornot) {
		this.redornot = redornot;
	}

	public String getGradepsnnum() {
		return gradepsnnum;
	}

	public void setGradepsnnum(String gradepsnnum) {
		this.gradepsnnum = gradepsnnum;
	}

	public RefPOJO getIsexistside() {
		return isexistside;
	}

	public void setIsexistside(RefPOJO isexistside) {
		this.isexistside = isexistside;
	}

	public String getSpecialnote() {
		return specialnote;
	}

	public void setSpecialnote(String specialnote) {
		this.specialnote = specialnote;
	}

	public String getVisitdate() {
		return visitdate;
	}

	public void setVisitdate(String visitdate) {
		this.visitdate = visitdate;
	}

	public String getVostatus() {
		return vostatus;
	}

	public void setVostatus(String vostatus) {
		this.vostatus = vostatus;
	}

	public RefPOJO getDef1() {
		return def1;
	}

	public void setDef1(RefPOJO def1) {
		this.def1 = def1;
	}
}
