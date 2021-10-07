
package nccloud.itf.cy.cy;

import java.util.Map;
import java.util.Set;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nc.vo.cy.miniprogram.MiniUserInfoPojo;
import nc.vo.cy.studentfile.AggStudentHVO;
import nc.vo.cy.studentfile.StudentBindVO;
import nc.vo.cy.studentfile.pojo.StudentClassMsg;
import nc.vo.cy.studentfile.pojo.StudentQueryPOJO;
import nc.vo.pub.BusinessException;

public interface IStudenthvoMaintain {

	public void delete(AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills) throws BusinessException;

	public AggStudentHVO[] insert(AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills) throws BusinessException;

	public AggStudentHVO[] update(AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills) throws BusinessException;

	public AggStudentHVO[] query(IQueryScheme queryScheme) throws BusinessException;

	public AggStudentHVO[] save(AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills) throws BusinessException;

	public AggStudentHVO[] unsave(AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills) throws BusinessException;

	public AggStudentHVO[] approve(AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills) throws BusinessException;

	public AggStudentHVO[] unapprove(AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills)
			throws BusinessException;
	
	
	public MiniUserInfoPojo miniLogin(MiniUserInfoPojo loginVO) throws BusinessException;
	
	
	public MiniUserInfoPojo miniPwUpdate(MiniUserInfoPojo loginVO) throws BusinessException;
	
	
	public AggStudentHVO[] queryStuBillByIdNO(String idno,String[] idnovalues) throws BusinessException;

	
	public AggCreditcontractHVO[] bindStuManager(StudentBindVO sbind) throws BusinessException;
	

	public StudentQueryPOJO queryStudentByIdcard2(String idcard) throws BusinessException;
	

	/**
	 * 学生档案查询针对家长端 根据学生身份证号 + 家长手机号 查询
	 */
	public String queryStudentByIdcard(String idcard, String parentphone, String openid) throws BusinessException;
	

	public String addToParentTab(String idcard, String parentphone, String openid) throws BusinessException;
	

	public String updateEmailByID(StudentQueryPOJO queryvo) throws BusinessException;
	

	public Map<String, String> studentDataQuery(Set<String> pks);
	

	public ResponseList queryForApp(StudentQueryPOJO stuquery) throws BusinessException;
	

	/**
	 * 通过学生编码查询所在行政词源班级
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public StudentClassMsg queryMsgByCode(String code) throws BusinessException;
}
