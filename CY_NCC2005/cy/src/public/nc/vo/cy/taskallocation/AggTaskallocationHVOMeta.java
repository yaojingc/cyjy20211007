package nc.vo.cy.taskallocation;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggTaskallocationHVOMeta extends AbstractBillMeta{
	
	public AggTaskallocationHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.cy.taskallocation.TaskallocationHVO.class);
		this.addChildren(nc.vo.cy.taskallocation.TaskallocationDetailVO.class);
	}
}