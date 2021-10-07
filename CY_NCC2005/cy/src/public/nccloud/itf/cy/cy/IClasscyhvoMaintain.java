
package nccloud.itf.cy.cy;

import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.classfilecy.RestClassSave;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.classfilecy.ClasscyDetailVO;
import nc.vo.cy.classfilecy.ClasscyHVO;
import nc.vo.cy.classfilecy.pojo.ClassCyCardReturnPOJO;
import nc.vo.cy.classfilecy.pojo.ClassCyQueryPOJO;
import nc.vo.cy.studentfile.StudentBindVO;
import nc.vo.pub.BusinessException;

public interface IClasscyhvoMaintain {

	public void delete(AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills) throws BusinessException;

	public AggClasscyHVO[] insert(AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills) throws BusinessException;

	public AggClasscyHVO[] update(AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills) throws BusinessException;

	public AggClasscyHVO[] query(IQueryScheme queryScheme) throws BusinessException;

	public AggClasscyHVO[] save(AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills) throws BusinessException;

	public AggClasscyHVO[] unsave(AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills) throws BusinessException;

	public AggClasscyHVO[] approve(AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills) throws BusinessException;

	public AggClasscyHVO[] unapprove(AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills)
			throws BusinessException;

	@SuppressWarnings("rawtypes")
	public ResponseList queryClassCyList(ClassCyQueryPOJO queryvo) throws BusinessException;
	

	public ClassCyCardReturnPOJO queryClassCyCard(String pk_classcy) throws BusinessException;
	

	public Map<String, String> classcyDataQuery(Set<String> pk);
	
	
	public ClasscyHVO queryClasscyAndCheck(StudentBindVO sbind) throws BusinessException ;
	
	
	public List<AggClasscyHVO> queryCyClassBillByCondition(String field,String fieldvalue[]) throws BusinessException;

	
	public List<ClasscyDetailVO> getCyClassDetailFromClassMap(String pk_cyclass_head)  throws BusinessException;

	public void commit(RestClassSave restSave) throws BusinessException;

	
	
}
