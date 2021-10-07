package nc.vo.cy.creditcontract;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggCreditcontractHVOMeta extends AbstractBillMeta {

	public AggCreditcontractHVOMeta() {
		this.init();
	}

	private void init() {
		this.setParent(nc.vo.cy.creditcontract.CreditcontractHVO.class);
		this.addChildren(nc.vo.cy.creditcontract.CreditcontractDetailVO.class);
	}
}