
package nccloud.itf.cy.cy;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bd.bankaccount.BankAccbasVO;
import nc.vo.cy.returncost.AggReturncostHVO;
import nc.vo.cy.returncost.MiniReturnCostInsertPojo;
import nc.vo.fct.ar.entity.AggCtArVO;
import nc.vo.pub.BusinessException;

public interface IReturncosthvoMaintain {

	public void delete(AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills) throws BusinessException;

	public AggReturncostHVO[] insert(AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills)
			throws BusinessException;

	public AggReturncostHVO[] update(AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills)
			throws BusinessException;

	public AggReturncostHVO[] query(IQueryScheme queryScheme) throws BusinessException;

	public AggReturncostHVO[] save(AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills)
			throws BusinessException;

	public AggReturncostHVO[] unsave(AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills)
			throws BusinessException;

	public AggReturncostHVO[] approve(AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills)
			throws BusinessException;

	public AggReturncostHVO[] unapprove(AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills)
			throws BusinessException;

	/**
	 * 通过家长端小程序提交的退费申请单新增接口
	 * @param miniReturnCostInsertPojo
	 * @return
	 * @throws BusinessException
	 */
	public AggReturncostHVO[] insertByMiniPro(MiniReturnCostInsertPojo miniReturnCostInsertPojo)
			throws BusinessException;
	
	

	/**
	 * 判断当前操作人员是否有设置退款金额的操作权限
	 * 
	 * @param pk_bill  单据主键
	 * @param checkman 当前操作人
	 * @return
	 * @throws BusinessException
	 */
	public boolean checkManIsEditReturnMoney(String pk_bill, String checkman) throws BusinessException;

	/**
	 * 设置退款金额
	 * 
	 * @param pk_bill单据主键
	 * @param returnMoney 退款金额
	 * @return
	 * @throws BusinessException
	 */
	public AggReturncostHVO setReturnMoney(String pk_bill, String returnMoney) throws BusinessException;
	
	/**
	  * 通过学生(客户)信息，统计该学生存在有几个合同数据
	 * @param pk_customer
	 * @return
	 * @throws BusinessException
	 */
	public int countCtByStudent(String pk_customer) throws BusinessException;

	/**
	 * 通过学生(客户)信息，找到对应的收款合同
	 * 
	 * @param pk_customer 客户主键
	 * @return
	 * @throws BusinessException
	 */
	public AggCtArVO getAggCtArVOByStudent(String pk_customer) throws BusinessException;

	/**
	 * 查询财务组织下的退款银行账号数据
	 * 
	 * @param bankaccVO 银行账户数据
	 * @return
	 * @throws BusinessException
	 */
	public BankAccbasVO queryReturnBankData(BankAccbasVO bankaccVO) throws BusinessException;
}
