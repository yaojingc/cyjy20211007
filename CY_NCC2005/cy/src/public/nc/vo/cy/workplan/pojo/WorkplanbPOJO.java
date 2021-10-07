package nc.vo.cy.workplan.pojo;

import nc.vo.cy.pojo.RefPOJO;
import nc.vo.cy.pojo.SchoolPOJO;
import nc.vo.pub.lang.UFDate;

public class WorkplanbPOJO {
	private String pk_workplan_b;
	
	public String getPk_workplan_b() {
		return pk_workplan_b;
	}

	public void setPk_workplan_b(String pk_workplan_b) {
		this.pk_workplan_b = pk_workplan_b;
	}

	private String pk_workplan;
	
	public String getPk_workplan() {
		return pk_workplan;
	}

	public void setPk_workplan(String pk_workplan) {
		this.pk_workplan = pk_workplan;
	}

	private String visitdate;

	private String pkschool;

	private String destination;

	private String traffic;

	private String linkman;

	private String tel;

	
	private String ic;

	public String getIc() {
		return ic;
	}

	public void setIc(String ic) {
		this.ic = ic;
	}

	public String getVostatus() {
		return vostatus;
	}

	public void setVostatus(String vostatus) {
		this.vostatus = vostatus;
	}

	private String vostatus;

	public String getVisitdate() {
		return visitdate;
	}

	public void setVisitdate(String visitdate) {
		this.visitdate = visitdate;
	}

	public String getPkschool() {
		return pkschool;
	}

	public void setPkschool(String pkschool) {
		this.pkschool = pkschool;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}


}
