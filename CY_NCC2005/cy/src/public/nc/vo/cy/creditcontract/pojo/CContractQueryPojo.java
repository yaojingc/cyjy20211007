package nc.vo.cy.creditcontract.pojo;

public class CContractQueryPojo {

	private String pk_contract;// 学生收款合同主键
	private String idcard;// 学生身份证号
	private String sname;// 学生姓名

	public String getPk_contract() {
		return pk_contract;
	}

	public void setPk_contract(String pk_contract) {
		this.pk_contract = pk_contract;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

}
