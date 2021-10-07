package nc.vo.cy.daily;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.daily.DailyHVO")

public class AggDailyHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggDailyHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public DailyHVO getParentVO(){
	  	return (DailyHVO)this.getParent();
	  }
	  
}