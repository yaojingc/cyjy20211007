package nc.vo.cy.classituation;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggClassituationHVOMeta extends AbstractBillMeta{
	
	public AggClassituationHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.cy.classituation.ClassituationHVO.class);
		this.addChildren(nc.vo.cy.classituation.ClassituationDetailVO.class);
	}
}