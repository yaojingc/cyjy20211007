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

public class SchoolBasicsTopPOJO {
	// 高管学校维护记录主键
	private String pk_top;
	// 拜访对象
	private String visitobj;
	// 解决的问题
	private String solveproblem;
	// 拜访巡视情况
	private String visitpatrol;
	// 稳定级别
	private String ufstablelevel;
	// 稳定级别
	private RefPOJO stablelevel;
	// 是否可挽回
	private String ufredornot;
	// 是否可挽回
	private RefPOJO redornot;
	// 年级人数分布
	private String gradepsnnum;
	// 是否与竞争机构并存
	private String ufisexistside;
	// 是否与竞争机构并存
	private RefPOJO isexistside;
	// 特殊事项说明
	private String specialnote;
	// 拜访日期 格式（YYYY-MM-dd）
	private String visitdate;
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

	public String getPk_top() {
		return pk_top;
	}

	public void setPk_top(String pk_top) {
		this.pk_top = pk_top;
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

	public String getUfstablelevel() {
		return ufstablelevel;
	}

	public void setUfstablelevel(String ufstablelevel) {
		this.ufstablelevel = ufstablelevel;
	}

	public RefPOJO getStablelevel() {
		return stablelevel;
	}

	public void setStablelevel(RefPOJO stablelevel) {
		this.stablelevel = stablelevel;
	}

	public String getUfredornot() {
		return ufredornot;
	}

	public void setUfredornot(String ufredornot) {
		this.ufredornot = ufredornot;
	}

	public RefPOJO getRedornot() {
		return redornot;
	}

	public void setRedornot(RefPOJO redornot) {
		this.redornot = redornot;
	}

	public String getUfisexistside() {
		return ufisexistside;
	}

	public void setUfisexistside(String ufisexistside) {
		this.ufisexistside = ufisexistside;
	}

	public RefPOJO getIsexistside() {
		return isexistside;
	}

	public void setIsexistside(RefPOJO isexistside) {
		this.isexistside = isexistside;
	}

	public String getGradepsnnum() {
		return gradepsnnum;
	}

	public void setGradepsnnum(String gradepsnnum) {
		this.gradepsnnum = gradepsnnum;
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
}
