package nc.vo.cy.schoolform;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggSchoolformHVOMeta extends AbstractBillMeta{
	
	public AggSchoolformHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.cy.schoolform.SchoolformHVO.class);
		this.addChildren(nc.vo.cy.schoolform.SchoolformDetailVO.class);
	}
}