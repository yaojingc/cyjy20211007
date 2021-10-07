package nc.vo.cy.pojo;

public class PsnQueryPOJO {

	// 排序字段
	private String sort;
	// 第几页
	private Integer index;
	// 一页大小
	private Integer size;
	// 人员编码
	private String code;
	// 人员姓名
	private String name;
	// 人员类别
	private String type;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
