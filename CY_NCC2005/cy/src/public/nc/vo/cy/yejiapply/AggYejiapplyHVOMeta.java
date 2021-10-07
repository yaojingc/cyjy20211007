package nc.vo.cy.yejiapply;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggYejiapplyHVOMeta extends AbstractBillMeta{
	
	public AggYejiapplyHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.cy.yejiapply.YejiapplyHVO.class);
		this.addChildren(nc.vo.cy.yejiapply.YejiapplyBVO.class);
	}
}