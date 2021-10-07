package nc.vo.cy.xuefeiapply;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggXuefeijmHVOMeta extends AbstractBillMeta{
	
	public AggXuefeijmHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.cy.xuefeiapply.XuefeijmHVO.class);
		this.addChildren(nc.vo.cy.xuefeiapply.XuefeiBVO.class);
	}
}