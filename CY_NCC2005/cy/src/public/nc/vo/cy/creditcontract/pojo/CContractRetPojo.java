package nc.vo.cy.creditcontract.pojo;

public class CContractRetPojo {
	/**
	 * 乙方身份证号 乙方电话号码 乙方姓名 乙方家长身份证号 乙方家长电话号码 乙方家长姓名 总价 学费 甲方授课代表
	 * 
	 * 甲方签订 年月日 乙方签订 年 月 日
	 */
	private String pk_contract;// 学生收款合同主键
	private String billcode;// 合同号
	private String idcard;// 学生身份证号
	private String sname;// 学生姓名
	private String ssex;
	private String sbirthday;
	private String amount;// 金额
	private String psndocname;// 老师 甲方授课代表
	private String startdate; // 甲/乙方签订
	private String enddate;// 合同结速日期
	private String year;
	private String month;
	private String day;
	private String othertype;// 其他方式三个月收费
	private String parentphone;// 家长电话
	private String parentname;//
	private String parentidcard;//
	private String schoolname;// 学校
	private String clasname;// 班级
	private String paystand;// 培训费用合计
	private String paystandCN;// 大写中文
	private String ksf;// 课时费
	private String relation;// 与学员关系
	private String afterreduceamount;// 减免后总金额
	private String currentpayamount;// 本次应缴金额
	private String beforepayamount;// 已缴金额
	private String paystatus;// 付款状态（"已付清"   "未付清"）
	private String pk_paymantrow;// 学费收款合同表体 付款行主键
	
	
	
	

	public String getBeforepayamount() {
		return beforepayamount;
	}

	public void setBeforepayamount(String beforepayamount) {
		this.beforepayamount = beforepayamount;
	}

	public String getPk_paymantrow() {
		return pk_paymantrow;
	}

	public void setPk_paymantrow(String pk_paymantrow) {
		this.pk_paymantrow = pk_paymantrow;
	}

	public String getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getAfterreduceamount() {
		return afterreduceamount;
	}

	public void setAfterreduceamount(String afterreduceamount) {
		this.afterreduceamount = afterreduceamount;
	}

	public String getCurrentpayamount() {
		return currentpayamount;
	}

	public void setCurrentpayamount(String currentpayamount) {
		this.currentpayamount = currentpayamount;
	}

	public String getPk_contract() {
		return pk_contract;
	}

	public void setPk_contract(String pk_contract) {
		this.pk_contract = pk_contract;
	}

	public String getBillcode() {
		return billcode;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode;
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

	public String getSsex() {
		return ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public String getSbirthday() {
		return sbirthday;
	}

	public void setSbirthday(String sbirthday) {
		this.sbirthday = sbirthday;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPsndocname() {
		return psndocname;
	}

	public void setPsndocname(String psndocname) {
		this.psndocname = psndocname;
	}

	public String getStartdate() {
		return startdate;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getOthertype() {
		return othertype;
	}

	public void setOthertype(String othertype) {
		this.othertype = othertype;
	}

	public String getParentphone() {
		return parentphone;
	}

	public void setParentphone(String parentphone) {
		this.parentphone = parentphone;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public String getParentidcard() {
		return parentidcard;
	}

	public void setParentidcard(String parentidcard) {
		this.parentidcard = parentidcard;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public String getClasname() {
		return clasname;
	}

	public void setClasname(String clasname) {
		this.clasname = clasname;
	}

	public String getPaystand() {
		return paystand;
	}

	public void setPaystand(String paystand) {
		this.paystand = paystand;
	}

	public String getPaystandCN() {
		return paystandCN;
	}

	public void setPaystandCN(String paystandCN) {
		this.paystandCN = paystandCN;
	}

	public String getKsf() {
		return ksf;
	}

	public void setKsf(String ksf) {
		this.ksf = ksf;
	}

}
