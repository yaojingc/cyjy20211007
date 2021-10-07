package nc.api.cy.rest.entity.crm.classfilecy;

/**
 * @ClassName ClassDetailPOJO
 * @Description TODO 班级子表信息
 * @Author NCC
 * @Date 2021/7/5 17:00
 * @Version 1.0
 **/
public class ClassDetailPOJO {
	
	/**
	 * 明细金额
	 */
	private String pk_detail;
	
	/**
	 * 行号
	 */
	private String rowno;
	
	/**
	 * 开始日期
	 */
	private String startdate;
	
	/**
	 * 结束日期
	 */
	private String enddate;
	
	/**
	 * 收款金额
	 */
	private String planamount;
	
	/**
	 * 数据状态（1更新行、2新增行、3删除行）
	 */
	private String vostatus;

	public String getPk_detail() {
		return pk_detail;
	}

	public void setPk_detail(String pk_detail) {
		this.pk_detail = pk_detail;
	}

	public String getRowno() {
		return rowno;
	}

	public void setRowno(String rowno) {
		this.rowno = rowno;
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

	public String getPlanamount() {
		return planamount;
	}

	public void setPlanamount(String planamount) {
		this.planamount = planamount;
	}

	public String getVostatus() {
		return vostatus;
	}

	public void setVostatus(String vostatus) {
		this.vostatus = vostatus;
	}
}
