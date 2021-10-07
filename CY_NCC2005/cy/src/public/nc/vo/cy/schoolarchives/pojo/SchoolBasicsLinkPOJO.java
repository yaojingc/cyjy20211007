package nc.vo.cy.schoolarchives.pojo;

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

public class SchoolBasicsLinkPOJO {

	// 联系人信息主键
	private String pk_link;
	// 姓名
	private String name;
	// 职位
	private String position;
	// 职务
	private String duty;
	// 生日
	private String birthday;
	// 联系方式1
	private String linkman1;
	// 联系方式2
	private String linkman2;
	// 联系方式3
	private String linkman3;
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

	public String getPk_link() {
		return pk_link;
	}

	public void setPk_link(String pk_link) {
		this.pk_link = pk_link;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getLinkman1() {
		return linkman1;
	}

	public void setLinkman1(String linkman1) {
		this.linkman1 = linkman1;
	}

	public String getLinkman2() {
		return linkman2;
	}

	public void setLinkman2(String linkman2) {
		this.linkman2 = linkman2;
	}

	public String getLinkman3() {
		return linkman3;
	}

	public void setLinkman3(String linkman3) {
		this.linkman3 = linkman3;
	}
}
