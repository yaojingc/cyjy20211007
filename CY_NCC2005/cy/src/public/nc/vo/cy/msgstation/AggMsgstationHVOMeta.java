package nc.vo.cy.msgstation;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggMsgstationHVOMeta extends AbstractBillMeta{
	
	public AggMsgstationHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.cy.msgstation.MsgstationHVO.class);
		this.addChildren(nc.vo.cy.msgstation.Msgchild.class);
	}
}