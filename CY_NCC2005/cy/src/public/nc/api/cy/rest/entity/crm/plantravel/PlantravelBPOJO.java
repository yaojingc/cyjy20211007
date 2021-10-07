package nc.api.cy.rest.entity.crm.plantravel;

public class PlantravelBPOJO {
	 /**
     * 行程安排主键
 */
private String pk_scheduling;

/**
     * 行号
 */
private String rowno;


/**
     * 客户
 */
private String customer;

/**
 *日期
*/
private String sdate;


/**
 * 时间段
*/
private String timeslot;

/**
 * 项目
*/
private String project;

/**
 * 地点
*/
private String location;

/**
 * 联系人
*/
private String linkman;

/**
 * 联系电话
*/
private String linkphone;


/**
  * 数据状态（1更新行、2新增行、3删除行）
 */
private String vostatus;


public String getPk_scheduling() {
	return pk_scheduling;
}


public void setPk_scheduling(String pk_scheduling) {
	this.pk_scheduling = pk_scheduling;
}


public String getRowno() {
	return rowno;
}


public void setRowno(String rowno) {
	this.rowno = rowno;
}


public String getCustomer() {
	return customer;
}


public void setCustomer(String customer) {
	this.customer = customer;
}


public String getSdate() {
	return sdate;
}


public void setSdate(String sdate) {
	this.sdate = sdate;
}


public String getTimeslot() {
	return timeslot;
}


public void setTimeslot(String timeslot) {
	this.timeslot = timeslot;
}


public String getProject() {
	return project;
}


public void setProject(String project) {
	this.project = project;
}


public String getLocation() {
	return location;
}


public void setLocation(String location) {
	this.location = location;
}


public String getLinkman() {
	return linkman;
}


public void setLinkman(String linkman) {
	this.linkman = linkman;
}


public String getLinkphone() {
	return linkphone;
}


public void setLinkphone(String linkphone) {
	this.linkphone = linkphone;
}


public String getVostatus() {
	return vostatus;
}


public void setVostatus(String vostatus) {
	this.vostatus = vostatus;
}



}
