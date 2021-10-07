package nc.vo.cy.xuefeiapply;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.xuefeiapply.XuefeijmHVO")

public class AggXuefeijmHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggXuefeijmHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public XuefeijmHVO getParentVO(){
	  	return (XuefeijmHVO)this.getParent();
	  }
	  
}