
package nccloud.itf.cy.cy;

import java.util.List;

import nc.api.cy.rest.entity.crm.teacherform.RestTeacherSave;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.pojo.GlbdefVO;
import nc.vo.cy.teacherform.AggTeacherformHVO;
import nc.vo.cy.teacherform.pojo.CContent;
import nc.vo.cy.teacherform.pojo.CContentRet;
import nc.vo.cy.teacherform.pojo.LContent;
import nc.vo.cy.teacherform.pojo.LContentRet;
import nc.vo.cy.teacherform.pojo.DataContent;
import nc.vo.cy.teacherform.pojo.QueryParmPOJO;
import nc.vo.cy.teacherform.pojo.TeacherAssign;
import nc.vo.pub.BusinessException;


public interface ITeacherformhvoMaintain {

	public void delete(AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills) throws BusinessException;

	public AggTeacherformHVO[] insert(AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills)
			throws BusinessException;

	public AggTeacherformHVO[] update(AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills)
			throws BusinessException;

	public AggTeacherformHVO[] query(IQueryScheme queryScheme) throws BusinessException;

	public AggTeacherformHVO[] save(AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills)
			throws BusinessException;

	public AggTeacherformHVO[] unsave(AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills)
			throws BusinessException;

	public AggTeacherformHVO[] approve(AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills)
			throws BusinessException, Exception;

	public AggTeacherformHVO[] unapprove(AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills)
			throws BusinessException;

	public DataContent<LContentRet> queryTeacherList(QueryParmPOJO querypojo) throws BusinessException, IllegalArgumentException, IllegalAccessException;
	
	
	public CContentRet queryTeacherDetail(QueryParmPOJO querypojo) throws BusinessException, IllegalArgumentException, IllegalAccessException;

	public void glbdefUpdate(GlbdefVO[] glbdefVO,String[] fieldNames) throws BusinessException;
	public String glbdefInsert(GlbdefVO glbdefVO) throws BusinessException;

	public void glbdefDelete(String sql) throws BusinessException;

	public void commit(RestTeacherSave restTeacherQuery) throws BusinessException;


	public void assign(TeacherAssign teachervo) throws BusinessException;
	
}
