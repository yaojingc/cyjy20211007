package nc.vo.cy.bankcollflow;

/*
 * 缴费记录返回信息
 */
public class BankReturnPOJO {

	// 学生姓名
	private String payname;
	// 缴费项目
	private String paymsg;
	// 缴费金额
	private String paymoney;
	// 缴费日期
	private String paytime;
	// 缴费单号
	private String paybillno;
	// 开票状态
	private String paybilltype;

	public String getPayname() {
		return payname;
	}

	public void setPayname(String payname) {
		this.payname = payname;
	}

	public String getPaymsg() {
		return paymsg;
	}

	public void setPaymsg(String paymsg) {
		this.paymsg = paymsg;
	}

	public String getPaymoney() {
		return paymoney;
	}

	public void setPaymoney(String paymoney) {
		this.paymoney = paymoney;
	}

	public String getPaytime() {
		return paytime;
	}

	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}

	public String getPaybillno() {
		return paybillno;
	}

	public void setPaybillno(String paybillno) {
		this.paybillno = paybillno;
	}

	public String getPaybilltype() {
		return paybilltype;
	}

	public void setPaybilltype(String paybilltype) {
		this.paybilltype = paybilltype;
	}
}
