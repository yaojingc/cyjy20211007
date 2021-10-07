package nc.api.cy.rest.entity.crm.schoolarchives;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.CrmBaseTag;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;

/**
 * @ClassName SchoolGradeInfoPOJO
 * @Description TODO 学校年级信息
 * @Author NCC
 * @Date 2021/7/5 16:29
 * @Version 1.0
 **/
public class SchoolGradeInfoPOJO {

    /**
         * 学校年级信息pk主键
     */
    private String pk_class;

    /**
         * 行号
     */
    private String rowno;

    /**
         * 年级
     */
    private String grade;

    /**
         * 人数
     */
    private String psnnum;

    /**
         * 是否开班
     */
    @ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
    private RefPOJO whetherclas;

    /**
         * 未开班说明
     */
    private String cause;

    /**
         * 数据状态（1更新行、2新增行、3删除行）
     */
    private String vostatus;
    
    /**
	  * 年级（自定义字段参照年级自定义档案）
	 */
    @ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_SESSION)
    private RefPOJO def1;

	public String getPk_class() {
		return pk_class;
	}

	public void setPk_class(String pk_class) {
		this.pk_class = pk_class;
	}

	public String getRowno() {
		return rowno;
	}

	public void setRowno(String rowno) {
		this.rowno = rowno;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPsnnum() {
		return psnnum;
	}

	public void setPsnnum(String psnnum) {
		this.psnnum = psnnum;
	}

	public RefPOJO getWhetherclas() {
		return whetherclas;
	}

	public void setWhetherclas(RefPOJO whetherclas) {
		this.whetherclas = whetherclas;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
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
