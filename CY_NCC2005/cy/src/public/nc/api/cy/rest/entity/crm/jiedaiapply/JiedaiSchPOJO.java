package nc.api.cy.rest.entity.crm.jiedaiapply;
/**
 * @ClassName JiedaiSchPOJO
 * @Description TODO 行程安排基本信息
 * @Author huangcong
 * @Date 2021/8/16 16:07
 * @Version 1.0
 **/
public class JiedaiSchPOJO {
	
	 /**
     * 行程安排主键
 */
private String pk_scheduling;

/**
     * 行号
 */
private String rowno;

/**
     * 到达日期
 */
private String startdate;

/**
     * 离开日期
 */
private String enddate;

/**
     * 客户
 */
private String customer;

/**
     * 紧急联系人
 */
private String econtract;

///**
// * 紧急联系电话
//*/
//private String ecphone;

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


public String getCustomer() {
	return customer;
}


public void setCustomer(String customer) {
	this.customer = customer;
}


public String getEcontract() {
	return econtract;
}


public void setEcontract(String econtract) {
	this.econtract = econtract;
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


//public String getEcphone() {
//	return ecphone;
//}
//
//
//public void setEcphone(String ecphone) {
//	this.ecphone = ecphone;
//}


}
