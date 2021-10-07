package nc.vo.cy.taskfeedback;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.taskfeedback.TaskfeedbackHVO")

public class AggTaskfeedbackHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggTaskfeedbackHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public TaskfeedbackHVO getParentVO(){
	  	return (TaskfeedbackHVO)this.getParent();
	  }
	  
}