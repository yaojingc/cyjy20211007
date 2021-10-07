package nc.api.cy.rest.entity.crm.basepojo;

import java.util.List;

/**
 * @ClassName ResponseList
 * @Description TODO 公共列表查询响应实体（分页）
 * @Author NCC
 * @Date 2021/7/14 16:31
 * @Version 1.0
 **/
public class ResponseList<T> {

    /**
         * 总数量
     */
    private Integer total;

    /**
         * 数据
     */
    private List<T> content;

    /**
         * 总页数
     */
    private Integer totalPages;

    /**
         * 当前页数
     */
    private Integer currentPage;

    /**
         * 页数大小
     */
    private Integer pageSize;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
