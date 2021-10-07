package nc.vo.cy.clsschedule;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggClsscheduleHVOMeta extends AbstractBillMeta{
	
	public AggClsscheduleHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.cy.clsschedule.ClsscheduleHVO.class);
		this.addChildren(nc.vo.cy.clsschedule.ClsscheduleBVO.class);
	}
}