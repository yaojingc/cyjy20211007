package nc.api.cy.rest.entity.crm.workplan;

import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.vo.pub.lang.UFDate;
/**
 * @ClassName WorkPlanInfoPOJO
 * @Description TODO 日报查询当天和次日工作计划的入参VO类
 * @Author huangcong
 * @Date 2021/8/3 17:00
 * @Version 1.0
 **/
public class RestQueryDetail {

	/**
	 * 用户信息
	 */
    private UserInfoByDiworkPOJO userinfo;

	public UserInfoByDiworkPOJO getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfoByDiworkPOJO userinfo) {
		this.userinfo = userinfo;
	} 
	private String reportdate;

	public String getReportdate() {
		return reportdate;
	}

	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
	}

	

	
	
}
