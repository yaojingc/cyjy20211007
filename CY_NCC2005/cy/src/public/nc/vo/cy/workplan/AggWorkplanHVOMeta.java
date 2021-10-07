package nc.vo.cy.workplan;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggWorkplanHVOMeta extends AbstractBillMeta{
	
	public AggWorkplanHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.cy.workplan.WorkplanHVO.class);
		this.addChildren(nc.vo.cy.workplan.WorkplanbVO.class);
	}
}