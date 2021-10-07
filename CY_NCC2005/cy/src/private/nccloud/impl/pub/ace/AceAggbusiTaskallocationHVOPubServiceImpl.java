
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTaskallocationHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTaskallocationHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTaskallocationHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTaskallocationHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTaskallocationHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTaskallocationHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTaskallocationHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiTaskallocationHVOPubServiceImpl {
        // 新增
        public AggTaskallocationHVO[] pubinsertBills(AggTaskallocationHVO[] clientFullVOs,
                        AggTaskallocationHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggTaskallocationHVO> transferTool = new BillTransferTool<AggTaskallocationHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceTaskallocationHVOInsertBP action = new AceTaskallocationHVOInsertBP();
                                AggTaskallocationHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggTaskallocationHVO[] clientFullVOs,
                        AggTaskallocationHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggTaskallocationHVO> transferTool =
                        new BillTransferTool<AggTaskallocationHVO>(clientFullVOs);
                        // 调用BP
                        new AceTaskallocationHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggTaskallocationHVO[] pubupdateBills(AggTaskallocationHVO[] clientFullVOs,
                        AggTaskallocationHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggTaskallocationHVO> transferTool = new BillTransferTool<AggTaskallocationHVO>(
                                        clientFullVOs);
                        AceTaskallocationHVOUpdateBP bp = new AceTaskallocationHVOUpdateBP();
                        AggTaskallocationHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggTaskallocationHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggTaskallocationHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggTaskallocationHVO> query = new BillLazyQuery<AggTaskallocationHVO>(
                                        AggTaskallocationHVO.class);
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
        public AggTaskallocationHVO[] pubsendapprovebills(
                        AggTaskallocationHVO[] clientFullVOs, AggTaskallocationHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggTaskallocationHVO> transferTool =
                new BillTransferTool<AggTaskallocationHVO>(clientFullVOs);
                AceTaskallocationHVOSendApproveBP bp = new AceTaskallocationHVOSendApproveBP();
                AggTaskallocationHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggTaskallocationHVO[] pubunsendapprovebills(
                        AggTaskallocationHVO[] clientFullVOs, AggTaskallocationHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggTaskallocationHVO> transferTool =
                        new BillTransferTool<AggTaskallocationHVO>(clientFullVOs);
                AceTaskallocationHVOUnSendApproveBP bp = new AceTaskallocationHVOUnSendApproveBP();
                AggTaskallocationHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggTaskallocationHVO[] pubapprovebills(AggTaskallocationHVO[] clientFullVOs,
                        AggTaskallocationHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggTaskallocationHVO> transferTool =
                        new BillTransferTool<AggTaskallocationHVO>(clientFullVOs);
                AceTaskallocationHVOApproveBP bp = new AceTaskallocationHVOApproveBP();
                AggTaskallocationHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggTaskallocationHVO[] pubunapprovebills(AggTaskallocationHVO[] clientFullVOs,
                        AggTaskallocationHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggTaskallocationHVO> transferTool =
                        new BillTransferTool<AggTaskallocationHVO>(clientFullVOs);
                AceTaskallocationHVOUnApproveBP bp = new AceTaskallocationHVOUnApproveBP();
                AggTaskallocationHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}