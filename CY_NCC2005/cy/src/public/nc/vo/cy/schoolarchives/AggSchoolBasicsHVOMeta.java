package nc.vo.cy.schoolarchives;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggSchoolBasicsHVOMeta extends AbstractBillMeta{
	
	public AggSchoolBasicsHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.cy.schoolarchives.SchoolBasicsHVO.class);
		this.addChildren(nc.vo.cy.schoolarchives.SchoolBasicsVisitVO.class);
		this.addChildren(nc.vo.cy.schoolarchives.SchoolBasicsLinkVO.class);
		this.addChildren(nc.vo.cy.schoolarchives.SchoolBasicsClassVO.class);
		this.addChildren(nc.vo.cy.schoolarchives.SchoolBasicsTopVO.class);
		this.addChildren(nc.vo.cy.schoolarchives.SchoolBasicsBusinVO.class);
	}
}