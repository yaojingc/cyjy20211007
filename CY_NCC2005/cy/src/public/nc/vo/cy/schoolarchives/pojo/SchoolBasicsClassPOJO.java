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

public class SchoolBasicsClassPOJO {

	// 年纪情况主键
	private String pk_class;
	// 年级
	private String grade;
	// 人数
	private String psnnum;
	// 是否开班
	private RefPOJO whetherclas;
	// 是否开班
	private String ufwhetherclas;
	// 未开班说明
	private String cause;
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

	public String getPk_class() {
		return pk_class;
	}

	public void setPk_class(String pk_class) {
		this.pk_class = pk_class;
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

	public String getUfwhetherclas() {
		return ufwhetherclas;
	}

	public void setUfwhetherclas(String ufwhetherclas) {
		this.ufwhetherclas = ufwhetherclas;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getRowno() {
		return rowno;
	}

	public void setRowno(String rowno) {
		this.rowno = rowno;
	}
}
