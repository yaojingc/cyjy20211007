
package nccloud.itf.cy.cy;

import java.util.Map;
import java.util.Set;

import nc.api.cy.rest.entity.crm.schoolarchives.RestSchoolSave;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.cy.schoolarchives.SchoolBasicsHVO;
import nc.vo.cy.schoolarchives.pojo.AggSchoolBasicsPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolCardRetuenPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolQueryPOJO;
import nc.vo.pub.BusinessException;

public interface ISchoolbasicshvoMaintain {

	public void delete(AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills) throws BusinessException;

	public AggSchoolBasicsHVO[] insert(AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills)
			throws BusinessException;

	public AggSchoolBasicsHVO[] update(AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills)
			throws BusinessException;

	public AggSchoolBasicsHVO[] query(IQueryScheme queryScheme) throws BusinessException;

	public AggSchoolBasicsHVO[] save(AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills)
			throws BusinessException;

	public AggSchoolBasicsHVO[] unsave(AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills)
			throws BusinessException;

	public AggSchoolBasicsHVO[] approve(AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills)
			throws BusinessException;

	public AggSchoolBasicsHVO[] unapprove(AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills)
			throws BusinessException;

	public String UpdateSchoolList(AggSchoolBasicsPOJO aggpojo) throws BusinessException;

	public PagingPOJO querySchoolList(SchoolQueryPOJO queryvo) throws BusinessException;

	public SchoolCardRetuenPOJO querySchoolCard(String pk_school) throws BusinessException;

	public Map<String, String> schoolDataQuery(Set<String> pk);

	public int schoolAllot(String pk_psndoc, String pk_psndoc2, String pk_psndoc3, String pk_psndoc4,
			SchoolBasicsHVO[] schoolVOS) throws BusinessException;

	/**
	 * 通过学校编码查询学校主键
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public String querySchoolIdByCode(String code) throws BusinessException;

	/**
	 * 通过学校主键查询客户主键
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public String queryCustomerIdBySchoolId(String schoolid) throws BusinessException;

	public void commit(RestSchoolSave restSave) throws BusinessException;

}
