package nc.vo.cy.returncost;

public class MiniReturnCostInsertPojo {
	
	// 个人银行账号
	private String bankNo;
	// 开户行
	private String bankName;
	// 开户支行
	private String branchBankName;
	// 收款人姓名
	private String sName;
	// 学生身份证号
	private String stuId;
	// 退学类型 (参照自定义档案cy23  01 退学退款    02 退学不退款)
	private String returnClassType;
	// 退学原因
	private String returnClassMsg;
	
	
	

	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranchBankName() {
		return branchBankName;
	}
	public void setBranchBankName(String branchBankName) {
		this.branchBankName = branchBankName;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getReturnClassType() {
		return returnClassType;
	}
	public void setReturnClassType(String returnClassType) {
		this.returnClassType = returnClassType;
	}
	public String getReturnClassMsg() {
		return returnClassMsg;
	}
	public void setReturnClassMsg(String returnClassMsg) {
		this.returnClassMsg = returnClassMsg;
	}
	
	
	
	
	

}
