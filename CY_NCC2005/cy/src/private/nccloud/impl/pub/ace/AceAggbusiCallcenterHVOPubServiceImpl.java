
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceCallcenterHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceCallcenterHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceCallcenterHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceCallcenterHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceCallcenterHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceCallcenterHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceCallcenterHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.callcenter.AggCallcenterHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiCallcenterHVOPubServiceImpl {
	// 新增
	public AggCallcenterHVO[] pubinsertBills(AggCallcenterHVO[] clientFullVOs, AggCallcenterHVO[] originBills)
			throws BusinessException {
		try {
			synchronized (clientFullVOs) {
				// 数据库中数据和前台传递过来的差异VO合并后的结果
				BillTransferTool<AggCallcenterHVO> transferTool = new BillTransferTool<AggCallcenterHVO>(clientFullVOs);
				// 调用BP
				AceCallcenterHVOInsertBP action = new AceCallcenterHVOInsertBP();
				AggCallcenterHVO[] retvos = action.insert(clientFullVOs);
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
	public void pubdeleteBills(AggCallcenterHVO[] clientFullVOs, AggCallcenterHVO[] originBills)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggCallcenterHVO> transferTool = new BillTransferTool<AggCallcenterHVO>(clientFullVOs);
			// 调用BP
			new AceCallcenterHVODeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggCallcenterHVO[] pubupdateBills(AggCallcenterHVO[] clientFullVOs, AggCallcenterHVO[] originBills)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggCallcenterHVO> transferTool = new BillTransferTool<AggCallcenterHVO>(clientFullVOs);
			AceCallcenterHVOUpdateBP bp = new AceCallcenterHVOUpdateBP();
			AggCallcenterHVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggCallcenterHVO[] pubquerybills(IQueryScheme queryScheme) throws BusinessException {
		AggCallcenterHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggCallcenterHVO> query = new BillLazyQuery<AggCallcenterHVO>(AggCallcenterHVO.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggCallcenterHVO[] pubsendapprovebills(AggCallcenterHVO[] clientFullVOs, AggCallcenterHVO[] originBills)
			throws BusinessException {
		// 加锁 + 检查ts
		BillTransferTool<AggCallcenterHVO> transferTool = new BillTransferTool<AggCallcenterHVO>(clientFullVOs);
		AceCallcenterHVOSendApproveBP bp = new AceCallcenterHVOSendApproveBP();
		AggCallcenterHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggCallcenterHVO[] pubunsendapprovebills(AggCallcenterHVO[] clientFullVOs, AggCallcenterHVO[] originBills)
			throws BusinessException {
		// 加锁 + 检查ts
		BillTransferTool<AggCallcenterHVO> transferTool = new BillTransferTool<AggCallcenterHVO>(clientFullVOs);
		AceCallcenterHVOUnSendApproveBP bp = new AceCallcenterHVOUnSendApproveBP();
		AggCallcenterHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggCallcenterHVO[] pubapprovebills(AggCallcenterHVO[] clientFullVOs, AggCallcenterHVO[] originBills)
			throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 加锁 + 检查ts
		BillTransferTool<AggCallcenterHVO> transferTool = new BillTransferTool<AggCallcenterHVO>(clientFullVOs);
		AceCallcenterHVOApproveBP bp = new AceCallcenterHVOApproveBP();
		AggCallcenterHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggCallcenterHVO[] pubunapprovebills(AggCallcenterHVO[] clientFullVOs, AggCallcenterHVO[] originBills)
			throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 加锁 + 检查ts
		BillTransferTool<AggCallcenterHVO> transferTool = new BillTransferTool<AggCallcenterHVO>(clientFullVOs);
		AceCallcenterHVOUnApproveBP bp = new AceCallcenterHVOUnApproveBP();
		AggCallcenterHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}