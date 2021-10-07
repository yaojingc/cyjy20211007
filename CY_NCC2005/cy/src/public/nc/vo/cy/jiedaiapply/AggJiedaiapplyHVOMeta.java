package nc.vo.cy.jiedaiapply;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggJiedaiapplyHVOMeta extends AbstractBillMeta{
	
	public AggJiedaiapplyHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.cy.jiedaiapply.JiedaiapplyHVO.class);
		this.addChildren(nc.vo.cy.jiedaiapply.JiedaiapplyvisitVO.class);
		this.addChildren(nc.vo.cy.jiedaiapply.JiedaiapplyschVO.class);
		this.addChildren(nc.vo.cy.jiedaiapply.JiedaiapplykcVO.class);
	}
}