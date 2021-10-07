package nc.api.cy.rest.entity.crm.basepojo;

/**
 * @ClassName QueryPage
 * @Description TODO 公共分页查询条件
 * @Author NCC
 * @Date 2021/7/14 15:42
 * @Version 1.0
 **/
public class QueryPage {

	/**
	 * 第几页
	 */
	private Integer index;

	/**
	 * 一页大小
	 */
	private Integer size;

	/**
	 * 排序字段名称(字段编码+asc/desc,如:name asc)
	 */
	private String sort;
	/**
	 * 用户手机号
	 */
	private String userMobile;

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
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

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
