package nc.vo.cy.classfilecy;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggClasscyHVOMeta extends AbstractBillMeta{
	
	public AggClasscyHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.cy.classfilecy.ClasscyHVO.class);
		this.addChildren(nc.vo.cy.classfilecy.ClasscyDetailVO.class);
	}
}