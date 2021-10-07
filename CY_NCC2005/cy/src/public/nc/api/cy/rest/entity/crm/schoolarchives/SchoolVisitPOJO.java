package nc.api.cy.rest.entity.crm.schoolarchives;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.CrmBaseTag;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;

/**
 * @ClassName SchoolVisitPOJO
 * @Description TODO 学校拜访记录
 * @Author NCC
 * @Date 2021/7/5 16:19
 * @Version 1.0
 **/
public class SchoolVisitPOJO {

	/**
	 * 学校拜访记录pk主键
	 */
	private String pk_visit;

	/**
	 * 行号
	 */
	private String rowno;

	/**
	 * 学校商务级别
	 */
	@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_BUSILEVEL)
	private RefPOJO busilevel;

	/**
	 * 本公司开发情况
	 */
	@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
	private RefPOJO develsituation;

	/**
	 * 各年级人数
	 */
	private String gradenum;

	/**
	 * 竞争对手
	 */
	private String competitor;

	/**
	 * 竞争机构开发情况
	 */
	private String rivaldevelsituation;

	/**
	 * 高考成绩
	 */
	private String gkgrade;

	/**
	 * 意向度
	 */
	@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_INTEDEGREE)
	private RefPOJO intedegree;

	/**
	 * 特殊事项说明
	 */
	private String specialnote;

	/**
	 * 数据状态（1更新行、2新增行、3删除行）
	 */
	private String vostatus;

	/**
	 * 商务级别（自定义字段参照商务级别自定义档案）
	 */
	@ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_BUSILEVEL)
	private RefPOJO def1;

	/**
	 * 意向度（自定义字段参照意向度自定义档案）
	 */
	@ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_INTEDEGREE)
	private RefPOJO def2;

	/**
	 * 是否存在竞争对手
	 */
	@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
	private RefPOJO def3;

	public String getPk_visit() {
		return pk_visit;
	}

	public void setPk_visit(String pk_visit) {
		this.pk_visit = pk_visit;
	}

	public String getRowno() {
		return rowno;
	}

	public void setRowno(String rowno) {
		this.rowno = rowno;
	}

	public RefPOJO getBusilevel() {
		return busilevel;
	}

	public void setBusilevel(RefPOJO busilevel) {
		this.busilevel = busilevel;
	}

	public RefPOJO getDevelsituation() {
		return develsituation;
	}

	public void setDevelsituation(RefPOJO develsituation) {
		this.develsituation = develsituation;
	}

	public String getGradenum() {
		return gradenum;
	}

	public void setGradenum(String gradenum) {
		this.gradenum = gradenum;
	}

	public String getCompetitor() {
		return competitor;
	}

	public void setCompetitor(String competitor) {
		this.competitor = competitor;
	}

	public String getRivaldevelsituation() {
		return rivaldevelsituation;
	}

	public void setRivaldevelsituation(String rivaldevelsituation) {
		this.rivaldevelsituation = rivaldevelsituation;
	}

	public String getGkgrade() {
		return gkgrade;
	}

	public void setGkgrade(String gkgrade) {
		this.gkgrade = gkgrade;
	}

	public RefPOJO getIntedegree() {
		return intedegree;
	}

	public void setIntedegree(RefPOJO intedegree) {
		this.intedegree = intedegree;
	}

	public String getSpecialnote() {
		return specialnote;
	}

	public void setSpecialnote(String specialnote) {
		this.specialnote = specialnote;
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
}
