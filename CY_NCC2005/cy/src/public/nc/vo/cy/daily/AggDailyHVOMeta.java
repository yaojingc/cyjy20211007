package nc.vo.cy.daily;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggDailyHVOMeta extends AbstractBillMeta{
	
	public AggDailyHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.cy.daily.DailyHVO.class);
		this.addChildren(nc.vo.cy.daily.DailydHVO.class);
	}
}