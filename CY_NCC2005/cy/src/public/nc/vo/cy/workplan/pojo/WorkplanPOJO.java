package nc.vo.cy.workplan.pojo;

import java.util.List;

import nc.vo.cy.pojo.RefPOJO;
import nc.vo.cy.pojo.SchoolPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolBasicsLinkPOJO;
import nc.vo.cy.teacherform.pojo.BaseRef;

public class WorkplanPOJO {
	
		// 单据主键
		private String pk_workplan;
		// 单据日期 格式（YYYY-MM-DD）
		private String dbilldate;
		// 班级编码
		private String bill_no;
		private String pkdept;
		private String pksalesman;
	
		public String getPkdept() {
			return pkdept;
		}
		public void setPkdept(String pkdept) {
			this.pkdept = pkdept;
		}
		public String getPksalesman() {
			return pksalesman;
		}
		public void setPksalesman(String pksalesman) {
			this.pksalesman = pksalesman;
		}
		// 计划开始日期
		private String startdate;
		// 计划结束日期
		private String enddate;
		// 所属部门
		private RefPOJO dept;
		// 业务员
		private RefPOJO salesman;
		

		public List<WorkplanbPOJO> getId_workplanInfo() {
			return id_workplanInfo;
		}
		public void setId_workplanInfo(List<WorkplanbPOJO> id_workplanInfo) {
			this.id_workplanInfo = id_workplanInfo;
		}
		//子表
		private List<WorkplanbPOJO> id_workplanInfo;
	
		
		public String getPk_workplan() {
			return pk_workplan;
		}
		public void setPk_workplan(String pk_workplan) {
			this.pk_workplan = pk_workplan;
		}
		public String getDbilldate() {
			return dbilldate;
		}
		public void setDbilldate(String dbilldate) {
			this.dbilldate = dbilldate;
		}
		public String getStartdate() {
			return startdate;
		}
		public String getBill_no() {
			return bill_no;
		}
		public void setBill_no(String bill_no) {
			this.bill_no = bill_no;
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
		public RefPOJO getDept() {
			return dept;
		}
		public void setDept(RefPOJO dept) {
			this.dept = dept;
		}
		public RefPOJO getSalesman() {
			return salesman;
		}
		public void setSalesman(RefPOJO salesman) {
			this.salesman = salesman;
		}

}

