package nccloud.impl.rule;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.cy.returncost.AggReturncostHVO;
import nc.vo.cy.returncost.ReturncostHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * 校验退费申请单能否取消审批，如果存在下游单据则不允取消审批
 * 同时恢复更新合同金额
 * @author csh
 *
 * @param <T>
 */
public class CheckReturnBillIsUnApproveRule<T extends AbstractBill> implements IRule<T> {
	
	private static BaseDAO baseDao;
	
	private static BaseDAO getBaseDao() {
		if (baseDao == null) {
			baseDao = new BaseDAO();
		}
		return baseDao;
	}

	@Override
	public void process(T[] aggvos) {
		for(T obj : aggvos) {
			try {
				AggReturncostHVO aggvo = (AggReturncostHVO) obj;
				ReturncostHVO headvo = aggvo.getParentVO();
				boolean existPayTag = isExistPayBill(aggvo);
        		if(existPayTag) {
        			throw new BusinessException("单据号"+headvo.getBill_no()+"退费申请已存在下游付款单，不能弃审");
        		}
        		boolean existReceivableTag = isExistReceivableBill(aggvo);
        		if(existReceivableTag) {
        			throw new BusinessException("单据号"+headvo.getBill_no()+"退费申请单已存在下游应收单，不能弃审");
        		}
        		restoreContentMoney(aggvo);
			} catch (BusinessException e) {
				ExceptionUtils.wrappException(e);
			}
		}
		
	} 
	
	/**
	* 是否存在下游单据付款单
	* @param aggvo
	* @return
	*/
	private boolean isExistPayBill(AggReturncostHVO aggvo) throws BusinessException{
		try {
			ReturncostHVO returncostHVO = aggvo.getParentVO();
			String querySql = "select count(0) from ap_payitem where dr=0 and src_billid = '"+returncostHVO.getPrimaryKey()+"'";
			int countNum = (int) getBaseDao().executeQuery(querySql, new ColumnProcessor());
			if(countNum > 0) {
				return true;
			}
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
		return false;
	}
	
	/**
	* 是否存在下游单据应收单
	* @param aggvo
	* @return
	 * @throws DAOException 
	*/
	private boolean isExistReceivableBill(AggReturncostHVO aggvo) throws BusinessException{
		try {
			ReturncostHVO returncostHVO = aggvo.getParentVO();
			String querySql = "select count(0) from ar_recitem where dr=0 and src_billid = '"+returncostHVO.getPrimaryKey()+"'";
			int countNum = (int) getBaseDao().executeQuery(querySql, new ColumnProcessor());
			
			if(countNum>0) {
				return true;
			}
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
		return false;
	}
	
	/**
	 * 取消审批后，之前审批通过后联动更新变化的合同金额需要还原回去
	 * @param aggvo
	 */
	private void restoreContentMoney(AggReturncostHVO aggvo) {
		
	}
}
