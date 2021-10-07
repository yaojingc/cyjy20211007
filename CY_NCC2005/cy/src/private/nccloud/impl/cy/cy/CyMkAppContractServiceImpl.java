package nccloud.impl.cy.cy;

import java.util.List;

import org.json.JSONString;

import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.cy.pojo.PsnQueryPOJO;
import nc.vo.cy.pojo.PsnReturnPOJO;
import nc.vo.cy.pojo.RefPOJO;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nccloud.impl.dao.CyMkAppBaseDao;
import nccloud.itf.cy.cy.ICyMkAppContractMaintain;

public class CyMkAppContractServiceImpl implements ICyMkAppContractMaintain {
	/*
	 * 根据条件查询人员数据
	 */
	@Override
	public JSONString QueryPsndocToApp(PsnQueryPOJO queryvo) throws BusinessException {
		PagingPOJO revo = new PagingPOJO();
		List<PsnReturnPOJO> psndoclist = CyMkAppBaseDao.queryPsndocList(queryvo);
		if (CyCommonUtils.isEmpty(psndoclist)) {
			return null;
		}
		for (PsnReturnPOJO PsnReturnPOJO : psndoclist) {
			String ufdept = PsnReturnPOJO.getUfdept();// 部门主键
			RefPOJO rpojo = CyMkAppBaseDao.queryDeptByPsn(ufdept);
			PsnReturnPOJO.setDept(rpojo);
			PsnReturnPOJO.setUfdept(null);
		}
		String queryTotal = CyMkAppBaseDao.queryTotal(queryvo);
		int total = Integer.parseInt(queryTotal);
		int pagesize = queryvo.getSize().intValue();
		revo.setTotal(total);
		UFDouble totalPages;
		int split = new UFDouble(total).div(new UFDouble(pagesize)).intValue();
		UFDouble mod = new UFDouble(total).mod(new UFDouble(pagesize));
		if (mod.sub(new UFDouble(0)).doubleValue() > 0) {
			totalPages = new UFDouble(split).add(new UFDouble(1));
		} else {
			totalPages = new UFDouble(split);
		}
		revo.setTotalPages(totalPages.intValue());
		revo.setPageSize(pagesize);
		revo.setCurrentPage(queryvo.getIndex().intValue());
		revo.setContent(psndoclist);
		return ResponsePOJO.toJSON(revo, "200", "成功");
	}
}
