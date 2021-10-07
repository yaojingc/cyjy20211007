package nc.vo.cy.bankcollflow;

/*
 * 缴费记录查询条件
 */
public class BankQueryPOJO {

	// 身份证号
	private String idcard;
	// 页码
	private String index;
	// 每页大小
	private String pageSize;
	
	

	

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

}
