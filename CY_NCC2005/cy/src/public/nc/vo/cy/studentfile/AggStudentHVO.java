package nc.vo.cy.studentfile;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.studentfile.StudentHVO")

public class AggStudentHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggStudentHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public StudentHVO getParentVO(){
	  	return (StudentHVO)this.getParent();
	  }
	  
}