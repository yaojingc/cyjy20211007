package nc.vo.cy.schoolarchives;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.schoolarchives.SchoolBasicsHVO")

public class AggSchoolBasicsHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggSchoolBasicsHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public SchoolBasicsHVO getParentVO(){
	  	return (SchoolBasicsHVO)this.getParent();
	  }
	  
}