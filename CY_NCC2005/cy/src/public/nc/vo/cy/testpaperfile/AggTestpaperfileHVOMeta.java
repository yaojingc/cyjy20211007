package nc.vo.cy.testpaperfile;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggTestpaperfileHVOMeta extends AbstractBillMeta {

	public AggTestpaperfileHVOMeta() {
		this.init();
	}

	private void init() {
		this.setParent(nc.vo.cy.testpaperfile.TestpaperfileHVO.class);
		this.addChildren(nc.vo.cy.testpaperfile.TestpaperfileBVO.class);
	}
}