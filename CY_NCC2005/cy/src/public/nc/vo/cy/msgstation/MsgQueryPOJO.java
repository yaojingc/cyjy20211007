package nc.vo.cy.msgstation;

import nccloud.print.pub.print.page.Page;

public class MsgQueryPOJO  {
	// 接收人身份证号
	private String receiverid;
	
	private Integer index;
	private Integer size;

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

	public String getReceiverid() {
		return receiverid;
	}

	public void setReceiverid(String receiverid) {
		this.receiverid = receiverid;
	}
}
