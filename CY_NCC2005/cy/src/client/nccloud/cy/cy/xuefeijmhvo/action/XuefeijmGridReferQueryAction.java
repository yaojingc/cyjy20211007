package nccloud.cy.cy.xuefeijmhvo.action;

import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.framework.web.processor.refgrid.RefQueryInfo;
import nccloud.framework.web.ui.meta.RefMeta;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;
import nccloud.web.refer.DefaultGridRefAction;

/**
 * 学费减免单自定义参照类
 * 
 * @author yao
 */
public class XuefeijmGridReferQueryAction extends DefaultGridRefAction {

	@Override
	public RefMeta getRefMeta(RefQueryInfo paramRefQueryInfo) {
		RefMeta meta = new RefMeta();
		meta.setCodeField("bill_no");
		meta.setNameField("infonote");
		meta.setPidField("pk_xfapply");
		meta.setExtraFields(new String[] { "infonote", "bill_no", "reducation" });
		meta.setPkField("pk_xfapply");
		meta.setTableName("cy_xuefeijm");
		meta.setMutilLangNameRef(true);
		return meta;
	}

	@Override
	public String getExtraSql(RefQueryInfo refQueryInfo, RefMeta refMeta) {
		/**
		 * 学费减免申请单: def2: 参照的学生档案 def5:是否已经被消费 学费收款合同： def2:参照的学生档案
		 */
		String pk_student = refQueryInfo.getQueryCondition().get("pk_student");
		// String pk_defdoc_no =
		// CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryDefdocPk("否", "是否");
		SqlBuilder builder = CyCommonUtils.getInstance(SqlBuilder.class);
		builder.reset();
		builder.append(" AND ");
		builder.append("dr", 0);
		builder.append(" AND ");
		builder.append("def2", pk_student);
		builder.append(" AND ");
		builder.append("def6", "否");
		builder.append(" AND ");
		builder.append("approvestatus", 1);
		return builder.toString();
	}
}
