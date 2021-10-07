
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceMsgstationHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceMsgstationHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceMsgstationHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceMsgstationHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceMsgstationHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceMsgstationHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceMsgstationHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.msgstation.AggMsgstationHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiMsgstationHVOPubServiceImpl {
	// 新增
	public AggMsgstationHVO[] pubinsertBills(AggMsgstationHVO[] clientFullVOs, AggMsgstationHVO[] originBills)
			throws BusinessException {
		try {
			synchronized (clientFullVOs) {
				// 数据库中数据和前台传递过来的差异VO合并后的结果
				BillTransferTool<AggMsgstationHVO> transferTool = new BillTransferTool<AggMsgstationHVO>(clientFullVOs);
				// 调用BP
				AceMsgstationHVOInsertBP action = new AceMsgstationHVOInsertBP();
				AggMsgstationHVO[] retvos = action.insert(clientFullVOs);
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
	public void pubdeleteBills(AggMsgstationHVO[] clientFullVOs, AggMsgstationHVO[] originBills)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggMsgstationHVO> transferTool = new BillTransferTool<AggMsgstationHVO>(clientFullVOs);
			// 调用BP
			new AceMsgstationHVODeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggMsgstationHVO[] pubupdateBills(AggMsgstationHVO[] clientFullVOs, AggMsgstationHVO[] originBills)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggMsgstationHVO> transferTool = new BillTransferTool<AggMsgstationHVO>(clientFullVOs);
			AceMsgstationHVOUpdateBP bp = new AceMsgstationHVOUpdateBP();
			AggMsgstationHVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggMsgstationHVO[] pubquerybills(IQueryScheme queryScheme) throws BusinessException {
		AggMsgstationHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggMsgstationHVO> query = new BillLazyQuery<AggMsgstationHVO>(AggMsgstationHVO.class);
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
	public AggMsgstationHVO[] pubsendapprovebills(AggMsgstationHVO[] clientFullVOs, AggMsgstationHVO[] originBills)
			throws BusinessException {
		// 加锁 + 检查ts
		BillTransferTool<AggMsgstationHVO> transferTool = new BillTransferTool<AggMsgstationHVO>(clientFullVOs);
		AceMsgstationHVOSendApproveBP bp = new AceMsgstationHVOSendApproveBP();
		AggMsgstationHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggMsgstationHVO[] pubunsendapprovebills(AggMsgstationHVO[] clientFullVOs, AggMsgstationHVO[] originBills)
			throws BusinessException {
		// 加锁 + 检查ts
		BillTransferTool<AggMsgstationHVO> transferTool = new BillTransferTool<AggMsgstationHVO>(clientFullVOs);
		AceMsgstationHVOUnSendApproveBP bp = new AceMsgstationHVOUnSendApproveBP();
		AggMsgstationHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggMsgstationHVO[] pubapprovebills(AggMsgstationHVO[] clientFullVOs, AggMsgstationHVO[] originBills)
			throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 加锁 + 检查ts
		BillTransferTool<AggMsgstationHVO> transferTool = new BillTransferTool<AggMsgstationHVO>(clientFullVOs);
		AceMsgstationHVOApproveBP bp = new AceMsgstationHVOApproveBP();
		AggMsgstationHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggMsgstationHVO[] pubunapprovebills(AggMsgstationHVO[] clientFullVOs, AggMsgstationHVO[] originBills)
			throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 加锁 + 检查ts
		BillTransferTool<AggMsgstationHVO> transferTool = new BillTransferTool<AggMsgstationHVO>(clientFullVOs);
		AceMsgstationHVOUnApproveBP bp = new AceMsgstationHVOUnApproveBP();
		AggMsgstationHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}