package nc.vo.cy.stumarkdata;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggStumarkdataHVOMeta extends AbstractBillMeta{
	
	public AggStumarkdataHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.cy.stumarkdata.StumarkdataHVO.class);
		this.addChildren(nc.vo.cy.stumarkdata.StumarkdataBVO.class);
	}
}