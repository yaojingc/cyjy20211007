package nc.vo.cy.taskallocation;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.taskallocation.TaskallocationHVO")

public class AggTaskallocationHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggTaskallocationHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public TaskallocationHVO getParentVO(){
	  	return (TaskallocationHVO)this.getParent();
	  }
	  
}