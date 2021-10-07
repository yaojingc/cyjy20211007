
package nccloud.impl.cy.cy;

import nccloud.impl.pub.ace.AceAggbusiClassxzHVOPubServiceImpl;
import nccloud.itf.cy.cy.IClassxzhvoMaintain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.vo.cy.classfilexz.AggClassxzHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class ClassxzhvoMaintainImpl extends AceAggbusiClassxzHVOPubServiceImpl implements IClassxzhvoMaintain {

	/**
	 * 行政班级信息查询
	 * 
	 * @param pk
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> classxzDataQuery(Set<String> pk) {
		Map<String, String> retMap = new HashMap<String, String>();
		if (MMValueCheck.isEmpty(pk)) {
			return retMap;
		}
		IUAPQueryBS queryBS = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		try {
			SqlBuilder sql = new SqlBuilder();
			sql.append("SELECT distinct pk_classxz pk,clasname name FROM cy_classxz WHERE nvl(dr,0)=0 ");
			sql.append(" and pk_classxz ", pk.toArray(new String[0]));
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

	@Override
	public void delete(AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggClassxzHVO[] insert(AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggClassxzHVO[] update(AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggClassxzHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggClassxzHVO[] save(AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggClassxzHVO[] unsave(AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggClassxzHVO[] approve(AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills)
			throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggClassxzHVO[] unapprove(AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills)
			throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
