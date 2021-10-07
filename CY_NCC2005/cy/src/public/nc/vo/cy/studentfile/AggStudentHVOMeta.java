package nc.vo.cy.studentfile;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggStudentHVOMeta extends AbstractBillMeta{
	
	public AggStudentHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.cy.studentfile.StudentHVO.class);
		this.addChildren(nc.vo.cy.studentfile.StudentGradeVO.class);
		this.addChildren(nc.vo.cy.studentfile.StudentChangeVO.class);
		this.addChildren(nc.vo.cy.studentfile.StudentParentVO.class);
	}
}