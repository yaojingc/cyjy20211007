package nccloud.cy.cy.classxzhvo.action;

import java.util.Map;

import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.framework.web.processor.refgrid.RefQueryInfo;
import nccloud.framework.web.ui.meta.RefMeta;
import nccloud.web.refer.DefaultGridRefAction;
/**
 * 行政班级参照
 * 
 * @author rongleia
 *
 */
public class ClassXzGridReferQueryAction extends DefaultGridRefAction {

	@Override
	public RefMeta getRefMeta(RefQueryInfo paramRefQueryInfo) {
		RefMeta meta = new RefMeta();
		meta.setCodeField("bill_no");
		meta.setNameField("clasname");
		meta.setPidField("pk_classxz");
		meta.setExtraFields(new String[] { "clasname", "bill_no" });
		meta.setPkField("pk_classxz");
		meta.setTableName("cy_classxz");
		meta.setMutilLangNameRef(true);
		return meta;
	}

	@Override
	public String getExtraSql(RefQueryInfo refQueryInfo, RefMeta refMeta) {

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
