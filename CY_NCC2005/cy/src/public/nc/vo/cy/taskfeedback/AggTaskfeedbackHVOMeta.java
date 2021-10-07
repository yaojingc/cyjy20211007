package nc.vo.cy.taskfeedback;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggTaskfeedbackHVOMeta extends AbstractBillMeta{
	
	public AggTaskfeedbackHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.cy.taskfeedback.TaskfeedbackHVO.class);
		this.addChildren(nc.vo.cy.taskfeedback.FeedbackDetail.class);
	}
}