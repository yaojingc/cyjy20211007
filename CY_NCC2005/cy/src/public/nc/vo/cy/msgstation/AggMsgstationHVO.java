package nc.vo.cy.msgstation;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.msgstation.MsgstationHVO")

public class AggMsgstationHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggMsgstationHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public MsgstationHVO getParentVO(){
	  	return (MsgstationHVO)this.getParent();
	  }
	  
}