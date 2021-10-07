package nc.api.cy.rest.entity.teach.course;

import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;

/**
 * @ClassName RestCourseQuery
 * @Description TODO 课表查询信息实体
 * @Author NCC
 * @Date 2021/8/24 10:30
 * @Version 1.0
 **/
public class RestCourseQuery {
	
	/**
	 * 用户信息
	 */
    private UserInfoByDiworkPOJO userinfo;
    
    /**
         * 年度
     */
    private String year;
    
    /**
	  * 月度
	 */
	private String month;

	public UserInfoByDiworkPOJO getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfoByDiworkPOJO userinfo) {
		this.userinfo = userinfo;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}
