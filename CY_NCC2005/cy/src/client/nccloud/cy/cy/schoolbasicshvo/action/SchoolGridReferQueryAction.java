package nccloud.cy.cy.schoolbasicshvo.action;

import nccloud.framework.web.processor.refgrid.RefQueryInfo;
import nccloud.framework.web.ui.meta.RefMeta;
import nccloud.web.refer.DefaultGridRefAction;
/**
 * 学校参照
 * 
 * @author rongleia
 *
 */
public class SchoolGridReferQueryAction extends DefaultGridRefAction {

	@Override
	public RefMeta getRefMeta(RefQueryInfo paramRefQueryInfo) {
		RefMeta meta = new RefMeta();
		meta.setCodeField("bill_no");
		meta.setNameField("sname");
		meta.setPidField("pk_school");
		meta.setExtraFields(new String[] { "sname", "bill_no" });
		meta.setPkField("pk_school");
		meta.setTableName("cy_schoolBasics");
		meta.setMutilLangNameRef(true);
		return meta;
	}

	@Override
	public String getExtraSql(RefQueryInfo refQueryInfo, RefMeta refMeta) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(" and dr = '0' ");
		Object userIdValue = refQueryInfo.getQueryCondition().get("userIdValue");
		//增加过滤条件，学校参照根据登陆人过滤---学生成绩库
		if(userIdValue != null) {
			sb.append(" and  pk_school in (select def1 from cy_classcy where creator = '"+userIdValue.toString()+"') ");
		}
		return sb.toString();
	}
}
