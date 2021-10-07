package nc.vo.cy.classituation;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.classituation.ClassituationHVO")

public class AggClassituationHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggClassituationHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public ClassituationHVO getParentVO(){
	  	return (ClassituationHVO)this.getParent();
	  }
	  
}