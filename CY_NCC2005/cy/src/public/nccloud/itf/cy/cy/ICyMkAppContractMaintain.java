package nccloud.itf.cy.cy;

import org.json.JSONString;

import nc.vo.cy.pojo.PsnQueryPOJO;
import nc.vo.pub.BusinessException;

public interface ICyMkAppContractMaintain {

	/*
	 * 查询人员数据
	 */
	public JSONString QueryPsndocToApp(PsnQueryPOJO queryvo) throws BusinessException;
}
