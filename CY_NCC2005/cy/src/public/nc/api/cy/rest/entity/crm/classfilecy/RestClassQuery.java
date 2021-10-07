package nc.api.cy.rest.entity.crm.classfilecy;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;
import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;

/**
 * @ClassName RestClassQuery
 * @Description TODO 班级信息查询实体
 * @Author NCC
 * @Date 2021/7/5 16:56
 * @Version 1.0
 **/
public class RestClassQuery extends QueryPage {

	/**
	 * 学校名称
	 */
	private String sname;

	/**
	 * 班级编码
	 */
	private String bill_no;

	/**
	 * 班级名称
	 */
	private String clasname;

	/**
	 * 是否签订纸质协议
	 */
	private String paperagre;

	/**
	 * 学校主键
	 */
	private String pk_school;

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

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getBill_no() {
		return bill_no;
	}

	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}

	public String getClasname() {
		return clasname;
	}

	public void setClasname(String clasname) {
		this.clasname = clasname;
	}

	public String getPaperagre() {
		return paperagre;
	}

	public void setPaperagre(String paperagre) {
		this.paperagre = paperagre;
	}

	public String getPk_school() {
		return pk_school;
	}

	public void setPk_school(String pk_school) {
		this.pk_school = pk_school;
	}

}
