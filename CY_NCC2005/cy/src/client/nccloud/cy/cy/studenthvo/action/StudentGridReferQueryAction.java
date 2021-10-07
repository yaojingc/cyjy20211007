package nccloud.cy.cy.studenthvo.action;

import nccloud.framework.web.processor.refgrid.RefQueryInfo;
import nccloud.framework.web.ui.meta.RefMeta;
import nccloud.web.refer.DefaultGridRefAction;

/**
 * 学生参照
 * 
 * @author tanrg
 *
 */
public class StudentGridReferQueryAction extends DefaultGridRefAction {

	@Override
	public RefMeta getRefMeta(RefQueryInfo paramRefQueryInfo) {
		RefMeta meta = new RefMeta();
		meta.setCodeField("bill_no");
		meta.setNameField("sname");
		meta.setPidField("pk_student");
		meta.setExtraFields(new String[] { "sname", "bill_no" });
		meta.setPkField("pk_student");
		meta.setTableName("cy_student");
		meta.setMutilLangNameRef(true);
		return meta;
	}

	@Override
	public String getExtraSql(RefQueryInfo refQueryInfo, RefMeta refMeta) {
		return "and dr = '0'";
	}
}
