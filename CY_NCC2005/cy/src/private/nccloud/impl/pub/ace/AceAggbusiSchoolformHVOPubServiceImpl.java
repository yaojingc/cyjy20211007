
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSchoolformHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSchoolformHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSchoolformHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSchoolformHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSchoolformHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSchoolformHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSchoolformHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.schoolform.AggSchoolformHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiSchoolformHVOPubServiceImpl {
	// 新增
	public AggSchoolformHVO[] pubinsertBills(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException {
		try {
			synchronized (clientFullVOs) {
				// 数据库中数据和前台传递过来的差异VO合并后的结果
				BillTransferTool<AggSchoolformHVO> transferTool = new BillTransferTool<AggSchoolformHVO>(clientFullVOs);
				// 调用BP
				AceSchoolformHVOInsertBP action = new AceSchoolformHVOInsertBP();
				AggSchoolformHVO[] retvos = action.insert(clientFullVOs);
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
	public void pubdeleteBills(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggSchoolformHVO> transferTool = new BillTransferTool<AggSchoolformHVO>(clientFullVOs);
			// 调用BP
			new AceSchoolformHVODeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggSchoolformHVO[] pubupdateBills(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggSchoolformHVO> transferTool = new BillTransferTool<AggSchoolformHVO>(clientFullVOs);
			AceSchoolformHVOUpdateBP bp = new AceSchoolformHVOUpdateBP();
			AggSchoolformHVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggSchoolformHVO[] pubquerybills(IQueryScheme queryScheme) throws BusinessException {
		AggSchoolformHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggSchoolformHVO> query = new BillLazyQuery<AggSchoolformHVO>(AggSchoolformHVO.class);
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
	public AggSchoolformHVO[] pubsendapprovebills(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException {
		// 加锁 + 检查ts
		BillTransferTool<AggSchoolformHVO> transferTool = new BillTransferTool<AggSchoolformHVO>(clientFullVOs);
		AceSchoolformHVOSendApproveBP bp = new AceSchoolformHVOSendApproveBP();
		AggSchoolformHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggSchoolformHVO[] pubunsendapprovebills(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException {
		// 加锁 + 检查ts
		BillTransferTool<AggSchoolformHVO> transferTool = new BillTransferTool<AggSchoolformHVO>(clientFullVOs);
		AceSchoolformHVOUnSendApproveBP bp = new AceSchoolformHVOUnSendApproveBP();
		AggSchoolformHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggSchoolformHVO[] pubapprovebills(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 加锁 + 检查ts
		BillTransferTool<AggSchoolformHVO> transferTool = new BillTransferTool<AggSchoolformHVO>(clientFullVOs);
		AceSchoolformHVOApproveBP bp = new AceSchoolformHVOApproveBP();
		AggSchoolformHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggSchoolformHVO[] pubunapprovebills(AggSchoolformHVO[] clientFullVOs, AggSchoolformHVO[] originBills)
			throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 加锁 + 检查ts
		BillTransferTool<AggSchoolformHVO> transferTool = new BillTransferTool<AggSchoolformHVO>(clientFullVOs);
		AceSchoolformHVOUnApproveBP bp = new AceSchoolformHVOUnApproveBP();
		AggSchoolformHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}