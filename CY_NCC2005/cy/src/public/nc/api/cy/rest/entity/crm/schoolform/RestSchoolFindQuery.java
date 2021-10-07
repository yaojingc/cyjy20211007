package nc.api.cy.rest.entity.crm.schoolform;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;

/**
 * @ClassName RestSchoolFindQuery
 * @Description TODO 新发现学校查询信息实体
 * @Author NCC
 * @Date 2021/7/9 9:19
 * @Version 1.0
 **/
public class RestSchoolFindQuery extends QueryPage {

	/**
	 * 学校主键
	 */
	private String pk_schoolf;

	/**
	 * 学校名称
	 */
	private String school;
	
	
	/**
	 * 学校名称
	 */
	private String def1;

	public String getDef1() {
		return def1;
	}

	public void setDef1(String def1) {
		this.def1 = def1;
	}

	/**
	 * 所属大区
	 */
	private String region;

	/**
	 * 详细地址
	 */
	private String address;

	/**
	 * 特殊情况说明
	 */
	private String express;

	/**
	 * 申请人
	 */
	private RefPOJO proposer;
	
	/**
	 * 制单人
	 */
	private String billmaker;
	
	public String getBillmaker() {
		return billmaker;
	}

	public void setBillmaker(String billmaker) {
		this.billmaker = billmaker;
	}

	public String getPk_schoolf() {
		return pk_schoolf;
	}

	public void setPk_schoolf(String pk_schoolf) {
		this.pk_schoolf = pk_schoolf;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public RefPOJO getProposer() {
		return proposer;
	}

	public void setProposer(RefPOJO proposer) {
		this.proposer = proposer;
	}
}
