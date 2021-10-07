package nccloud.cy.cy.classcyhvo.action;

import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.framework.web.processor.refgrid.RefQueryInfo;
import nccloud.framework.web.ui.meta.RefMeta;
import nccloud.web.refer.DefaultGridRefAction;

/**
 * 词源班级参照
 * 
 * @author rongleia
 *
 */
public class ClassCyGridReferQueryAction extends DefaultGridRefAction {

	@Override
	public RefMeta getRefMeta(RefQueryInfo paramRefQueryInfo) {
		RefMeta meta = new RefMeta();
		meta.setCodeField("bill_no");
		meta.setNameField("clasname");
		meta.setPidField("pk_classcy");
		meta.setExtraFields(new String[] { "clasname", "bill_no" });
		meta.setPkField("pk_classcy");
		meta.setTableName("cy_classcy");
		meta.setMutilLangNameRef(true);
		return meta;
	}

	@Override
	public String getExtraSql(RefQueryInfo refQueryInfo, RefMeta refMeta) {

//		String pk_school = refQueryInfo.getQueryCondition().get("pk_school");
		//修改key的code，前端代码传的key是schoolValue
		String pk_school = refQueryInfo.getQueryCondition().get("schoolValue");
		try {
			SqlBuilder builder = CyCommonUtils.getInstance(SqlBuilder.class);
			builder.reset();
			builder.append(" AND ");
			builder.append("dr",0);
			builder.append(" AND ");
			builder.append("def1",pk_school);
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
