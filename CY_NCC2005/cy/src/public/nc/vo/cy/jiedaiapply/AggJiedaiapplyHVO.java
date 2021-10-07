package nc.vo.cy.jiedaiapply;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.jiedaiapply.JiedaiapplyHVO")

public class AggJiedaiapplyHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggJiedaiapplyHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public JiedaiapplyHVO getParentVO(){
	  	return (JiedaiapplyHVO)this.getParent();
	  }
	  
}