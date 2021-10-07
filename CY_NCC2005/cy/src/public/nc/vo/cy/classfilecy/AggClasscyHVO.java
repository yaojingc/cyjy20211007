package nc.vo.cy.classfilecy;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.classfilecy.ClasscyHVO")

public class AggClasscyHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggClasscyHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public ClasscyHVO getParentVO(){
	  	return (ClasscyHVO)this.getParent();
	  }
	  
}