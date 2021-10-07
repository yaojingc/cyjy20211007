package nc.vo.cy.stumarkdata;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.stumarkdata.StumarkdataHVO")

public class AggStumarkdataHVO extends AbstractBill {

	@Override
	public IBillMeta getMetaData() {
		IBillMeta billMeta = BillMetaFactory.getInstance().getBillMeta(AggStumarkdataHVOMeta.class);
		return billMeta;
	}

	@Override
	public StumarkdataHVO getParentVO() {
		return (StumarkdataHVO) this.getParent();
	}

}