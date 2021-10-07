
package nccloud.impl.cy.cy;

import nccloud.impl.pub.ace.AceAggbusiTestpaperfileHVOPubServiceImpl;
import nccloud.itf.cy.cy.ITestpaperfilehvoMaintain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.vo.cy.testpaperfile.AggTestpaperfileHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class TestpaperfilehvoMaintainImpl extends AceAggbusiTestpaperfileHVOPubServiceImpl
		implements ITestpaperfilehvoMaintain {

	@Override
	public void delete(AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills)
			throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggTestpaperfileHVO[] insert(AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills)
			throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggTestpaperfileHVO[] update(AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills)
			throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggTestpaperfileHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggTestpaperfileHVO[] save(AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills)
			throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggTestpaperfileHVO[] unsave(AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills)
			throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggTestpaperfileHVO[] approve(AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills)
			throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggTestpaperfileHVO[] unapprove(AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills)
			throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public Map<String, String> testPaperDataQuery(Set<String> setpks) {
		Map<String, String> retMap = new HashMap<String, String>();
		if (MMValueCheck.isEmpty(setpks)) {
			return retMap;
		}
		IUAPQueryBS queryBS = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		try {
			SqlBuilder sql = new SqlBuilder();
			sql.append(" SELECT pk_testpaperfile pk,testname name FROM cy_testpaperfile WHERE nvl(dr,0)=0 ");
			sql.append(" and pk_testpaperfile ", setpks.toArray(new String[0]));
			List<Map<String, String>> queryListMap = (List<Map<String, String>>) queryBS.executeQuery(sql.toString(),
					new MapListProcessor());
			if (MMValueCheck.isNotEmpty(queryListMap)) {
				for (Map<String, String> map : queryListMap) {
					retMap.put(map.get("pk"), map.get("name"));
				}
			}
		} catch (Exception e) {
			return retMap;
		}
		return retMap;

	}

}
