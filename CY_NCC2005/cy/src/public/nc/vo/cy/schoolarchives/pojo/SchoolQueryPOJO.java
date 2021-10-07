package nc.vo.cy.schoolarchives.pojo;

/*
 * 学校档案查询条件
 */
public class SchoolQueryPOJO {

	// 学校主键
	private String pk_school;
	// 学校名称
	private String sname;
	// 排序字段
	private String sort;
	// 第几页
	private Integer index;
	// 一页大小
	private Integer size;

	public String getPk_school() {
		return pk_school;
	}

	public void setPk_school(String pk_school) {
		this.pk_school = pk_school;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
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
