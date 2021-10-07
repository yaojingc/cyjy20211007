
package nccloud.itf.cy.cy;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nc.vo.cy.creditcontract.pojo.CContractCreateParamPojo;
import nc.vo.cy.creditcontract.pojo.CContractQueryPojo;
import nc.vo.cy.creditcontract.pojo.CContractQuerydelmsgsPojo;
import nc.vo.cy.creditcontract.pojo.CContractRetPojo;
import nc.vo.cy.studentfile.StudentBindVO;
import nc.vo.pub.BusinessException;

public interface ICreditcontracthvoMaintain {

	public void delete(AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills)
			throws BusinessException;

	public AggCreditcontractHVO[] insert(AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills)
			throws BusinessException;

	public AggCreditcontractHVO[] update(AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills)
			throws BusinessException;

	public AggCreditcontractHVO[] query(IQueryScheme queryScheme) throws BusinessException;

	public AggCreditcontractHVO[] save(AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills)
			throws BusinessException;

	public AggCreditcontractHVO[] unsave(AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills)
			throws BusinessException;

	public AggCreditcontractHVO[] approve(AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills)
			throws BusinessException;

	public AggCreditcontractHVO[] unapprove(AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills)
			throws BusinessException;

	public AggCreditcontractHVO[] createContract(StudentBindVO contractCreateVO) throws BusinessException;

	public CContractRetPojo ccontractQuery(CContractQueryPojo queryvo) throws BusinessException;

	public CContractQuerydelmsgsPojo ccontractQuerymsgs(String idcard) throws BusinessException;

	public boolean ccontractUpdate(CContractQueryPojo queryvo) throws BusinessException;

	public void contractModifyWithAr(AggCreditcontractHVO aggVO) throws BusinessException;

	public Integer queryLatestVersion(String pk_student) throws BusinessException;

	public String queryFormalContract(String id) throws BusinessException;

	public void queryDunningContract(String days) throws BusinessException;

	/**
	 * 回写正式合同收款计划
	 * 
	 * @param pk_detail, payNo
	 * @return
	 * @throws BusinessException
	 */
	public Boolean updateFctplan(String pk_plan, String amount, String rate) throws BusinessException;

	/**
	 * 回写正式合同合同基本
	 * 
	 * @param pk_detail, payNo
	 * @return
	 * @throws BusinessException
	 */
	public Boolean updateFctbasic(String pk_plan, String jmhje, String bhsje, String se) throws BusinessException;

	/**
	 * 回写正式合同累计减免金额
	 * 
	 * @param pk_detail, payNo
	 * @return
	 * @throws BusinessException
	 */
	public Boolean updateFctLjjm(String pk_fct_ar, String ljjm) throws BusinessException;

}
