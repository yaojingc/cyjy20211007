package nc.vo.cy.schoolform.pojo;

/*
 * 学校申请查询条件
 */
public class SchoolFormQueryPOJO {

	// 学校名称
	private String school;
	// 学校主键
	private String pk_schoolf;
	// 所属大区
	private String region;
	// 申请人
	private String proposer;
	// 排序字段
	private String sort;
	// 第几页
	private Integer index;
	// 一页大小
	private Integer size;

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

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getPk_schoolf() {
		return pk_schoolf;
	}

	public void setPk_schoolf(String pk_schoolf) {
		this.pk_schoolf = pk_schoolf;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
}
