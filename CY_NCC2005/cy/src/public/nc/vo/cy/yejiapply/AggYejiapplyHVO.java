package nc.vo.cy.yejiapply;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.yejiapply.YejiapplyHVO")

public class AggYejiapplyHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggYejiapplyHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public YejiapplyHVO getParentVO(){
	  	return (YejiapplyHVO)this.getParent();
	  }
	  
}