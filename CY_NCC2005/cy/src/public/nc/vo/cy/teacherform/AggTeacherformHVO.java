package nc.vo.cy.teacherform;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.teacherform.TeacherformHVO")

public class AggTeacherformHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggTeacherformHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public TeacherformHVO getParentVO(){
	  	return (TeacherformHVO)this.getParent();
	  }
	  
}