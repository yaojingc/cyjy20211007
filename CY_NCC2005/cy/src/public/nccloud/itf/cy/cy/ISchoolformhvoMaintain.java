
package nccloud.itf.cy.cy;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.schoolform.RestSchoolFindQuery;
import nc.api.cy.rest.entity.crm.schoolform.RestSchoolFindSave;
import nc.api.cy.rest.entity.crm.schoolform.SchoolFindAggPOJO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.cy.schoolform.AggSchoolformHVO;
import nc.vo.cy.schoolform.pojo.SchoolFormCardReturnPOJO;
import nc.vo.cy.schoolform.pojo.SchoolFormQueryPOJO;
import nc.vo.pub.BusinessException;

public interface ISchoolformhvoMaintain {

	public void delete(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills) throws BusinessException;

	public AggSchoolformHVO[] insert(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException;

	public AggSchoolformHVO[] update(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException;

	public AggSchoolformHVO[] query(IQueryScheme queryScheme) throws BusinessException;

	public AggSchoolformHVO[] save(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException;

	public AggSchoolformHVO[] unsave(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException;

	public AggSchoolformHVO[] approve(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException;

	public AggSchoolformHVO[] unapprove(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException;

	public PagingPOJO querySchoolList(SchoolFormQueryPOJO queryvo) throws BusinessException;

	public SchoolFormCardReturnPOJO querySchoolCard(String pk_schoolf) throws BusinessException;

	public ResponseList queryListPage(RestSchoolFindQuery restQuery) throws BusinessException;

	public void billSave(RestSchoolFindSave restXuefei) throws BusinessException;

	public SchoolFindAggPOJO queryDeatil(String pk) throws BusinessException;

	public void commit(RestSchoolFindSave restSave) throws BusinessException;
}
