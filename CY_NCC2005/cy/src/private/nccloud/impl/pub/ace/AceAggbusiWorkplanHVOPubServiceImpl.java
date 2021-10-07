
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceWorkplanHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceWorkplanHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceWorkplanHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceWorkplanHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceWorkplanHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceWorkplanHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceWorkplanHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.workplan.AggWorkplanHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiWorkplanHVOPubServiceImpl {
        // 新增
        public AggWorkplanHVO[] pubinsertBills(AggWorkplanHVO[] clientFullVOs,
                        AggWorkplanHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggWorkplanHVO> transferTool = new BillTransferTool<AggWorkplanHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceWorkplanHVOInsertBP action = new AceWorkplanHVOInsertBP();
                                AggWorkplanHVO[] retvos = action.insert(clientFullVOs);
                                // 构造返回数据
        //                      return transferTool.getBillForToClient(retvos);
                                return retvos;
                        }
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        // 删除
        public void pubdeleteBills(AggWorkplanHVO[] clientFullVOs,
                        AggWorkplanHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggWorkplanHVO> transferTool =
                        new BillTransferTool<AggWorkplanHVO>(clientFullVOs);
                        // 调用BP
                        new AceWorkplanHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggWorkplanHVO[] pubupdateBills(AggWorkplanHVO[] clientFullVOs,
                        AggWorkplanHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggWorkplanHVO> transferTool = new BillTransferTool<AggWorkplanHVO>(
                                        clientFullVOs);
                        AceWorkplanHVOUpdateBP bp = new AceWorkplanHVOUpdateBP();
                        AggWorkplanHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggWorkplanHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggWorkplanHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggWorkplanHVO> query = new BillLazyQuery<AggWorkplanHVO>(
                                        AggWorkplanHVO.class);
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
        public AggWorkplanHVO[] pubsendapprovebills(
                        AggWorkplanHVO[] clientFullVOs, AggWorkplanHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggWorkplanHVO> transferTool =
                new BillTransferTool<AggWorkplanHVO>(clientFullVOs);
                AceWorkplanHVOSendApproveBP bp = new AceWorkplanHVOSendApproveBP();
                AggWorkplanHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggWorkplanHVO[] pubunsendapprovebills(
                        AggWorkplanHVO[] clientFullVOs, AggWorkplanHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggWorkplanHVO> transferTool =
                        new BillTransferTool<AggWorkplanHVO>(clientFullVOs);
                AceWorkplanHVOUnSendApproveBP bp = new AceWorkplanHVOUnSendApproveBP();
                AggWorkplanHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggWorkplanHVO[] pubapprovebills(AggWorkplanHVO[] clientFullVOs,
                        AggWorkplanHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggWorkplanHVO> transferTool =
                        new BillTransferTool<AggWorkplanHVO>(clientFullVOs);
                AceWorkplanHVOApproveBP bp = new AceWorkplanHVOApproveBP();
                AggWorkplanHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggWorkplanHVO[] pubunapprovebills(AggWorkplanHVO[] clientFullVOs,
                        AggWorkplanHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggWorkplanHVO> transferTool =
                        new BillTransferTool<AggWorkplanHVO>(clientFullVOs);
                AceWorkplanHVOUnApproveBP bp = new AceWorkplanHVOUnApproveBP();
                AggWorkplanHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}