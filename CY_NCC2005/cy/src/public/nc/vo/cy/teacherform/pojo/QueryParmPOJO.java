package nc.vo.cy.teacherform.pojo;

/*
 * 老师、讲师查询条件
 */
public class QueryParmPOJO {

	// 学校主键
	private String pk_teacherform;
	// 学校名称
	private String sname;
	// 1表示讲师申请单 2表示老师申请单
	private String billType;
	// 排序字段
	private String sort;
	// 第几页
	private Integer index;
	// 一页大小
	private Integer size;

	public String getPk_teacherform() {
		return pk_teacherform;
	}

	public void setPk_teacherform(String pk_teacherform) {
		this.pk_teacherform = pk_teacherform;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
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
