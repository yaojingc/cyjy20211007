package nccloud.cy.cy.testpaperfilehvo.action;

import nccloud.framework.web.processor.refgrid.RefQueryInfo;
import nccloud.framework.web.ui.meta.RefMeta;
import nccloud.web.refer.DefaultGridRefAction;

public class TestpaperfileGridReferQueryAction extends DefaultGridRefAction {

	@Override
	public RefMeta getRefMeta(RefQueryInfo paramRefQueryInfo) {
		RefMeta meta = new RefMeta();
		meta.setCodeField("testcode");
		meta.setNameField("testname");
		meta.setPidField("pk_testpaperfile");
		meta.setExtraFields(new String[] { "testname", "testcode" });
		meta.setPkField("pk_testpaperfile");
		meta.setTableName("cy_testpaperfile");
		meta.setMutilLangNameRef(true);
		return meta;
	}

	@Override
	public String getExtraSql(RefQueryInfo refQueryInfo, RefMeta refMeta) {
		return "and dr = '0'";
	}
}
