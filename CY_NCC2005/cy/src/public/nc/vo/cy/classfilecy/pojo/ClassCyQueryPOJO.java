package nc.vo.cy.classfilecy.pojo;

/*
 * 班级档案查询条件
 */
public class ClassCyQueryPOJO {

	// 班级主键
	private String pk_classcy;
	// 学校名称
	private String sname;
	// 班级编码
	private String bill_no;
	// 班级名称
	private String clasname;
	// 是否签定纸质协议
	private String paperagre;
	// 排序字段
	private String sort;
	// 第几页
	private Integer index;
	// 一页大小
	private Integer size;

	public String getPk_classcy() {
		return pk_classcy;
	}

	public void setPk_classcy(String pk_classcy) {
		this.pk_classcy = pk_classcy;
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

	public String getBill_no() {
		return bill_no;
	}

	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}

	public String getClasname() {
		return clasname;
	}

	public void setClasname(String clasname) {
		this.clasname = clasname;
	}

	public String getPaperagre() {
		return paperagre;
	}

	public void setPaperagre(String paperagre) {
		this.paperagre = paperagre;
	}
}
