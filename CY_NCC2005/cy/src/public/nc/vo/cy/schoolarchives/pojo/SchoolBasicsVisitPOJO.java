package nc.vo.cy.schoolarchives.pojo;

import nc.vo.cy.pojo.RefPOJO;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 * �˴�����۵�������Ϣ
 * </p>
 * ��������:2021-7-2
 * 
 * @author yonyouBQ
 * @version NCPrj ??
 */

public class SchoolBasicsVisitPOJO {
	// 拜访记录主键
	private String pk_visit;
	// 学校商务级别
	private RefPOJO busilevel;
	// 本公司开发情况
	private RefPOJO develsituation;
	// 学校商务级别
	private String ufbusilevel;
	// 本公司开发情况
	private String ufdevelsituation;
	// 各年级人数
	private String gradenum;
	// 竞争对手
	private String competitor;
	// 竞争机构开发情况
	private String rivaldevelsituation;
	// 高考成绩
	private String gkgrade;
	// 意向度
	private RefPOJO intedegree;
	// 意向度
	private String ufintedegree;
	// 特殊事项说明
	private String specialnote;
	// 行号
	private String rowno;
	// 数据状态 1更新行 2新增行 3删除行
	private String vostatus;

	public String getVostatus() {
		return vostatus;
	}

	public void setVostatus(String vostatus) {
		this.vostatus = vostatus;
	}

	public String getRowno() {
		return rowno;
	}

	public void setRowno(String rowno) {
		this.rowno = rowno;
	}

	public String getPk_visit() {
		return pk_visit;
	}

	public void setPk_visit(String pk_visit) {
		this.pk_visit = pk_visit;
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

	public String getUfbusilevel() {
		return ufbusilevel;
	}

	public void setUfbusilevel(String ufbusilevel) {
		this.ufbusilevel = ufbusilevel;
	}

	public String getUfdevelsituation() {
		return ufdevelsituation;
	}

	public void setUfdevelsituation(String ufdevelsituation) {
		this.ufdevelsituation = ufdevelsituation;
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

	public String getUfintedegree() {
		return ufintedegree;
	}

	public void setUfintedegree(String ufintedegree) {
		this.ufintedegree = ufintedegree;
	}

	public String getSpecialnote() {
		return specialnote;
	}

	public void setSpecialnote(String specialnote) {
		this.specialnote = specialnote;
	}
}
