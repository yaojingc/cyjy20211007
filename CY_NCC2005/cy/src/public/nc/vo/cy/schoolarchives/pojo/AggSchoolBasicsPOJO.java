package nc.vo.cy.schoolarchives.pojo;

import java.util.List;

import nc.vo.cy.pojo.UserInfoPOJO;

/*
 * 学生档案返回信息
 */
public class AggSchoolBasicsPOJO {

	// 学校主键
	private SchoolBasicsPOJO schoolbaseinfo;
	// 学校名称
	private List<SchoolBasicsTopPOJO> id_schoolbasicstopvo;
	// 学校名称
	private List<SchoolBasicsVisitPOJO> id_schoolbasicsvisitvo;
	// 学校名称
	private List<SchoolBasicsClassPOJO> id_schoolbasicsclassvo;
	// 是否启用
	private List<SchoolBasicsLinkPOJO> id_schoolbasicslinkvo;
	// 当前登录用户信息
	private UserInfoPOJO userinfo;

	public UserInfoPOJO getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfoPOJO userinfo) {
		this.userinfo = userinfo;
	}

	public SchoolBasicsPOJO getSchoolbaseinfo() {
		return schoolbaseinfo;
	}

	public void setSchoolbaseinfo(SchoolBasicsPOJO schoolbaseinfo) {
		this.schoolbaseinfo = schoolbaseinfo;
	}

	public List<SchoolBasicsTopPOJO> getId_schoolbasicstopvo() {
		return id_schoolbasicstopvo;
	}

	public void setId_schoolbasicstopvo(List<SchoolBasicsTopPOJO> id_schoolbasicstopvo) {
		this.id_schoolbasicstopvo = id_schoolbasicstopvo;
	}

	public List<SchoolBasicsVisitPOJO> getId_schoolbasicsvisitvo() {
		return id_schoolbasicsvisitvo;
	}

	public void setId_schoolbasicsvisitvo(List<SchoolBasicsVisitPOJO> id_schoolbasicsvisitvo) {
		this.id_schoolbasicsvisitvo = id_schoolbasicsvisitvo;
	}

	public List<SchoolBasicsClassPOJO> getId_schoolbasicsclassvo() {
		return id_schoolbasicsclassvo;
	}

	public void setId_schoolbasicsclassvo(List<SchoolBasicsClassPOJO> id_schoolbasicsclassvo) {
		this.id_schoolbasicsclassvo = id_schoolbasicsclassvo;
	}

	public List<SchoolBasicsLinkPOJO> getId_schoolbasicslinkvo() {
		return id_schoolbasicslinkvo;
	}

	public void setId_schoolbasicslinkvo(List<SchoolBasicsLinkPOJO> id_schoolbasicslinkvo) {
		this.id_schoolbasicslinkvo = id_schoolbasicslinkvo;
	}

}
