package nc.vo.cy.workplan;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.workplan.WorkplanHVO")

public class AggWorkplanHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggWorkplanHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public WorkplanHVO getParentVO(){
	  	return (WorkplanHVO)this.getParent();
	  }
	  
}