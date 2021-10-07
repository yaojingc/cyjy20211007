package nc.api.cy.rest.entity.crm.schoolarchives;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;
import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;

/**
 * @ClassName RestSchoolQuery
 * @Description TODO 学校查询信息实体
 * @Author NCC
 * @Date 2021/7/14 15:42
 * @Version 1.0
 **/
public class RestSchoolQuery extends QueryPage {

	/**
	 * 学校名称
	 */
	private String sname;

	/**
	 * 所属大区
	 */
	private String def11;

	/**
	 * 所属省份
	 */
	private String province;

	/**
	 * 开发进程
	 */
	private String def12;

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getDef11() {
		return def11;
	}

	public void setDef11(String def11) {
		this.def11 = def11;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDef12() {
		return def12;
	}

	public void setDef12(String def12) {
		this.def12 = def12;
	}
}
