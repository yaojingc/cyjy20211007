
package nccloud.itf.cy.cy;

import java.util.List;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.bankcollflow.AggBankcollflowHVO;
import nc.vo.cy.bankcollflow.BankQueryPOJO;
import nc.vo.cy.bankcollflow.BankReturnPOJO;
import nc.vo.cy.bankcollflow.BankcollflowHVO;
import nc.vo.cy.bankcollflow.InvoiceSendPOJO;
import nc.vo.pub.BusinessException;

public interface IBankcollflowhvoMaintain {

	public void delete(AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills) throws BusinessException;

	public AggBankcollflowHVO[] insert(AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills)
			throws BusinessException;

	public AggBankcollflowHVO[] update(AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills)
			throws BusinessException;

	public AggBankcollflowHVO[] query(IQueryScheme queryScheme) throws BusinessException;

	public AggBankcollflowHVO[] save(AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills)
			throws BusinessException;

	public AggBankcollflowHVO[] unsave(AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills)
			throws BusinessException;

	public AggBankcollflowHVO[] approve(AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills)
			throws BusinessException;

	public AggBankcollflowHVO[] unapprove(AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills)
			throws BusinessException;

	public BankcollflowHVO[] bankFlowUpdate(BankcollflowHVO bfhvo) throws BusinessException;

	public Boolean queryVoucher(String paycode) throws BusinessException;

	public List<BankcollflowHVO> queryBakFlows(InvoiceSendPOJO bfvo) throws BusinessException;

	public List<BankReturnPOJO> queryBak(BankQueryPOJO querypojo, int pagetotal) throws BusinessException;

	public Boolean updateMakestate(List<String> pklist) throws BusinessException;
}
