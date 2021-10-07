package nc.vo.cy.workplan.pojo;

/*
 * 学校档案查询条件
 */
public class WorkplanQueryPOJO {

	// 学校主键
	private String pk_workplan;
	//开始日期
	private String startdate;
	//结束日期
	private String enddate;
	//业务员名称
	private String salesman;
	//部门名称
	private String dept;
	// 排序字段
	private String sort;
	// 第几页
	private Integer index;
	// 一页大小
	private Integer size;

	
	

	public String getPk_workplan() {
		return pk_workplan;
	}

	public void setPk_workplan(String pk_workplan) {
		this.pk_workplan = pk_workplan;
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

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}
