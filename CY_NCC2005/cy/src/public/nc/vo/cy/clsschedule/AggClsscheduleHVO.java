package nc.vo.cy.clsschedule;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.clsschedule.ClsscheduleHVO")

public class AggClsscheduleHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggClsscheduleHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public ClsscheduleHVO getParentVO(){
	  	return (ClsscheduleHVO)this.getParent();
	  }
	  
}