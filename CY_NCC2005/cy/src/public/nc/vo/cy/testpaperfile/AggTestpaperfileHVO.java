package nc.vo.cy.testpaperfile;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.testpaperfile.TestpaperfileHVO")

public class AggTestpaperfileHVO extends AbstractBill {

	@Override
	public IBillMeta getMetaData() {
		IBillMeta billMeta = BillMetaFactory.getInstance().getBillMeta(AggTestpaperfileHVOMeta.class);
		return billMeta;
	}

	@Override
	public TestpaperfileHVO getParentVO() {
		return (TestpaperfileHVO) this.getParent();
	}

}