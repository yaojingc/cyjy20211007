package nc.vo.cy.workplan.pojo;

import java.util.List;

import nc.vo.cy.pojo.RefPOJO;
import nc.vo.cy.pojo.SchoolPOJO;
import nc.vo.cy.pojo.UserInfoPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolBasicsLinkPOJO;
import nc.vo.cy.workplan.AggWorkplanHVO;

/*
 * 拜访计划更新接收数据实体
 */
public class AggWorkplanPOJO {
	
	private UserInfoPOJO userinfo;
	
	private WorkplanPOJO workplanbaseInfo;
	
	private WorkplanbPOJO [] id_workplanInfo;
	// 是否启用
//	private List<SchoolBasicsLinkPOJO> id_schoolbasicslinkvo;
//	public SchoolPOJO getSchool() {
//		return school;
//	}
//
//	public void setSchool(SchoolPOJO school) {
//		this.school = school;
//	}

	public UserInfoPOJO getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfoPOJO userinfo) {
		this.userinfo = userinfo;
	}

	public WorkplanPOJO getWorkplanbaseInfo() {
		return workplanbaseInfo;
	}

	public void setWorkplanbaseInfo(WorkplanPOJO workplanbaseInfo) {
		this.workplanbaseInfo = workplanbaseInfo;
	}

	public WorkplanbPOJO[] getId_workplanInfo() {
		return id_workplanInfo;
	}

	public void setId_workplanInfo(WorkplanbPOJO[] id_workplanInfo) {
		this.id_workplanInfo = id_workplanInfo;
	}


	
}
