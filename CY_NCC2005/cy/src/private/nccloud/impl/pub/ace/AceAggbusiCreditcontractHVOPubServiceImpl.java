
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceCreditcontractHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceCreditcontractHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceCreditcontractHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceCreditcontractHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceCreditcontractHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceCreditcontractHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceCreditcontractHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

public abstract class AceAggbusiCreditcontractHVOPubServiceImpl {
	// 新增
	public AggCreditcontractHVO[] pubinsertBills(AggCreditcontractHVO[] clientFullVOs,
			AggCreditcontractHVO[] originBills) throws BusinessException {
		try {
			synchronized (clientFullVOs) {
				// 数据库中数据和前台传递过来的差异VO合并后的结果
				BillTransferTool<AggCreditcontractHVO> transferTool = new BillTransferTool<AggCreditcontractHVO>(
						clientFullVOs);
				// 调用BP
				AceCreditcontractHVOInsertBP action = new AceCreditcontractHVOInsertBP();
				AggCreditcontractHVO[] retvos = action.insert(clientFullVOs);
				// 构造返回数据
				// return transferTool.getBillForToClient(retvos);
				return retvos;
			}
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggCreditcontractHVO[] clientFullVOs, AggCreditcontractHVO[] originBills)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggCreditcontractHVO> transferTool = new BillTransferTool<AggCreditcontractHVO>(
					clientFullVOs);
			// 调用BP
			new AceCreditcontractHVODeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggCreditcontractHVO[] pubupdateBills(AggCreditcontractHVO[] clientFullVOs,
			AggCreditcontractHVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggCreditcontractHVO> transferTool = new BillTransferTool<AggCreditcontractHVO>(
					clientFullVOs);
			AceCreditcontractHVOUpdateBP bp = new AceCreditcontractHVOUpdateBP();
			AggCreditcontractHVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggCreditcontractHVO[] pubquerybills(IQueryScheme queryScheme) throws BusinessException {
		AggCreditcontractHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggCreditcontractHVO> query = new BillLazyQuery<AggCreditcontractHVO>(
					AggCreditcontractHVO.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * NCC学费收款合同前台查询时默认显示最新版本的
	 * Edit By yao
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
		QuerySchemeProcessor processer = new QuerySchemeProcessor(queryScheme);
		// 固定查询条件
	}

	// 提交
	public AggCreditcontractHVO[] pubsendapprovebills(AggCreditcontractHVO[] clientFullVOs,
			AggCreditcontractHVO[] originBills) throws BusinessException {
		// 加锁 + 检查ts
		BillTransferTool<AggCreditcontractHVO> transferTool = new BillTransferTool<AggCreditcontractHVO>(clientFullVOs);
		AceCreditcontractHVOSendApproveBP bp = new AceCreditcontractHVOSendApproveBP();
		AggCreditcontractHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggCreditcontractHVO[] pubunsendapprovebills(AggCreditcontractHVO[] clientFullVOs,
			AggCreditcontractHVO[] originBills) throws BusinessException {
		// 加锁 + 检查ts
		BillTransferTool<AggCreditcontractHVO> transferTool = new BillTransferTool<AggCreditcontractHVO>(clientFullVOs);
		AceCreditcontractHVOUnSendApproveBP bp = new AceCreditcontractHVOUnSendApproveBP();
		AggCreditcontractHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggCreditcontractHVO[] pubapprovebills(AggCreditcontractHVO[] clientFullVOs,
			AggCreditcontractHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 加锁 + 检查ts
		BillTransferTool<AggCreditcontractHVO> transferTool = new BillTransferTool<AggCreditcontractHVO>(clientFullVOs);
		AceCreditcontractHVOApproveBP bp = new AceCreditcontractHVOApproveBP();
		AggCreditcontractHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggCreditcontractHVO[] pubunapprovebills(AggCreditcontractHVO[] clientFullVOs,
			AggCreditcontractHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 加锁 + 检查ts
		BillTransferTool<AggCreditcontractHVO> transferTool = new BillTransferTool<AggCreditcontractHVO>(clientFullVOs);
		AceCreditcontractHVOUnApproveBP bp = new AceCreditcontractHVOUnApproveBP();
		AggCreditcontractHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}