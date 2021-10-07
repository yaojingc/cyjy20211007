
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClsscheduleHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClsscheduleHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClsscheduleHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClsscheduleHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClsscheduleHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClsscheduleHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClsscheduleHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.clsschedule.AggClsscheduleHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiClsscheduleHVOPubServiceImpl {
        // 新增
        public AggClsscheduleHVO[] pubinsertBills(AggClsscheduleHVO[] clientFullVOs,
                        AggClsscheduleHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggClsscheduleHVO> transferTool = new BillTransferTool<AggClsscheduleHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceClsscheduleHVOInsertBP action = new AceClsscheduleHVOInsertBP();
                                AggClsscheduleHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggClsscheduleHVO[] clientFullVOs,
                        AggClsscheduleHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggClsscheduleHVO> transferTool =
                        new BillTransferTool<AggClsscheduleHVO>(clientFullVOs);
                        // 调用BP
                        new AceClsscheduleHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggClsscheduleHVO[] pubupdateBills(AggClsscheduleHVO[] clientFullVOs,
                        AggClsscheduleHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggClsscheduleHVO> transferTool = new BillTransferTool<AggClsscheduleHVO>(
                                        clientFullVOs);
                        AceClsscheduleHVOUpdateBP bp = new AceClsscheduleHVOUpdateBP();
                        AggClsscheduleHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggClsscheduleHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggClsscheduleHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggClsscheduleHVO> query = new BillLazyQuery<AggClsscheduleHVO>(
                                        AggClsscheduleHVO.class);
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
        public AggClsscheduleHVO[] pubsendapprovebills(
                        AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggClsscheduleHVO> transferTool =
                new BillTransferTool<AggClsscheduleHVO>(clientFullVOs);
                AceClsscheduleHVOSendApproveBP bp = new AceClsscheduleHVOSendApproveBP();
                AggClsscheduleHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggClsscheduleHVO[] pubunsendapprovebills(
                        AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggClsscheduleHVO> transferTool =
                        new BillTransferTool<AggClsscheduleHVO>(clientFullVOs);
                AceClsscheduleHVOUnSendApproveBP bp = new AceClsscheduleHVOUnSendApproveBP();
                AggClsscheduleHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggClsscheduleHVO[] pubapprovebills(AggClsscheduleHVO[] clientFullVOs,
                        AggClsscheduleHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggClsscheduleHVO> transferTool =
                        new BillTransferTool<AggClsscheduleHVO>(clientFullVOs);
                AceClsscheduleHVOApproveBP bp = new AceClsscheduleHVOApproveBP();
                AggClsscheduleHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggClsscheduleHVO[] pubunapprovebills(AggClsscheduleHVO[] clientFullVOs,
                        AggClsscheduleHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggClsscheduleHVO> transferTool =
                        new BillTransferTool<AggClsscheduleHVO>(clientFullVOs);
                AceClsscheduleHVOUnApproveBP bp = new AceClsscheduleHVOUnApproveBP();
                AggClsscheduleHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}