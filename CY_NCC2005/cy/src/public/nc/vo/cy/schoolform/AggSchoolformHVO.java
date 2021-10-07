package nc.vo.cy.schoolform;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.schoolform.SchoolformHVO")

public class AggSchoolformHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggSchoolformHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public SchoolformHVO getParentVO(){
	  	return (SchoolformHVO)this.getParent();
	  }
	  
}