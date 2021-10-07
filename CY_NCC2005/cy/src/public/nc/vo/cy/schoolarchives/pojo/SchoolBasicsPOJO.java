package nc.vo.cy.schoolarchives.pojo;

import nc.vo.cy.pojo.RefPOJO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 * �˴�����۵�������Ϣ
 * </p>
 * ��������:2021-7-2
 * 
 * @author yonyouBQ
 * @version NCPrj ??
 */

public class SchoolBasicsPOJO {
	// 学校主键
	private String pk_school;
	// 单据日期 格式（YYYY-MM-dd）
	private String dbilldate;
	// 单据状态
	private String approvestatus;
	// 单据状态
	private RefPOJO billstatus;
	// 单据号
	private String bill_no;
	// 学校名称
	private String sname;
	// 学校具体地点
	private String saddress;
	// 学校电话
	private String sphone;
	// 所属大区
	private String region;
	// 省份
	private String province;
	// 备注
	private String vnote;

	public String getPk_school() {
		return pk_school;
	}

	public void setPk_school(String pk_school) {
		this.pk_school = pk_school;
	}

	public String getDbilldate() {
		return dbilldate;
	}

	public void setDbilldate(String dbilldate) {
		this.dbilldate = dbilldate;
	}

	public String getApprovestatus() {
		return approvestatus;
	}

	public void setApprovestatus(String approvestatus) {
		this.approvestatus = approvestatus;
	}

	public RefPOJO getBillstatus() {
		return billstatus;
	}

	public void setBillstatus(RefPOJO billstatus) {
		this.billstatus = billstatus;
	}

	public String getBill_no() {
		return bill_no;
	}

	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	public String getSphone() {
		return sphone;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getVnote() {
		return vnote;
	}

	public void setVnote(String vnote) {
		this.vnote = vnote;
	}
}
