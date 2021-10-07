package nc.vo.cy.teacherform;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggTeacherformHVOMeta extends AbstractBillMeta{
	
	public AggTeacherformHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.cy.teacherform.TeacherformHVO.class);
		this.addChildren(nc.vo.cy.teacherform.TeacherformDetailVO.class);
	}
}