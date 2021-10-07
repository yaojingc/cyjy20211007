package nc.vo.cy.creditcontract;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.creditcontract.CreditcontractHVO")

public class AggCreditcontractHVO extends AbstractBill {

	@Override
	public IBillMeta getMetaData() {
		IBillMeta billMeta = BillMetaFactory.getInstance().getBillMeta(AggCreditcontractHVOMeta.class);
		return billMeta;
	}

	@Override
	public CreditcontractHVO getParentVO() {
		return (CreditcontractHVO) this.getParent();
	}

}