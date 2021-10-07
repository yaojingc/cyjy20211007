package nc.vo.cy.msgstation;

import java.util.List;

import nc.vo.pub.lang.UFDate;

public class MsgListReturnPOJO {

	// 总数量
		private Integer total;
		// 总页数
		private Integer totalPages;
		// 当前页
		private Integer currentPage;
		// 页的大小
		private Integer pageSize;
		// 接收时间
		private List<MsgReturnPOJO> msgs;
		public Integer getTotal() {
			return total;
		}
		public void setTotal(Integer total) {
			this.total = total;
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
		public List<MsgReturnPOJO> getMsgs() {
			return msgs;
		}
		public void setMsgs(List<MsgReturnPOJO> msgs) {
			this.msgs = msgs;
		}

		
}
