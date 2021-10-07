
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceXuefeijmHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceXuefeijmHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceXuefeijmHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceXuefeijmHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceXuefeijmHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceXuefeijmHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceXuefeijmHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.xuefeiapply.AggXuefeijmHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiXuefeijmHVOPubServiceImpl {
        // 新增
        public AggXuefeijmHVO[] pubinsertBills(AggXuefeijmHVO[] clientFullVOs,
                        AggXuefeijmHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggXuefeijmHVO> transferTool = new BillTransferTool<AggXuefeijmHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceXuefeijmHVOInsertBP action = new AceXuefeijmHVOInsertBP();
                                AggXuefeijmHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggXuefeijmHVO[] clientFullVOs,
                        AggXuefeijmHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggXuefeijmHVO> transferTool =
                        new BillTransferTool<AggXuefeijmHVO>(clientFullVOs);
                        // 调用BP
                        new AceXuefeijmHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggXuefeijmHVO[] pubupdateBills(AggXuefeijmHVO[] clientFullVOs,
                        AggXuefeijmHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggXuefeijmHVO> transferTool = new BillTransferTool<AggXuefeijmHVO>(
                                        clientFullVOs);
                        AceXuefeijmHVOUpdateBP bp = new AceXuefeijmHVOUpdateBP();
                        AggXuefeijmHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggXuefeijmHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggXuefeijmHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggXuefeijmHVO> query = new BillLazyQuery<AggXuefeijmHVO>(
                                        AggXuefeijmHVO.class);
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
        public AggXuefeijmHVO[] pubsendapprovebills(
                        AggXuefeijmHVO[] clientFullVOs, AggXuefeijmHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggXuefeijmHVO> transferTool =
                new BillTransferTool<AggXuefeijmHVO>(clientFullVOs);
                AceXuefeijmHVOSendApproveBP bp = new AceXuefeijmHVOSendApproveBP();
                AggXuefeijmHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggXuefeijmHVO[] pubunsendapprovebills(
                        AggXuefeijmHVO[] clientFullVOs, AggXuefeijmHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggXuefeijmHVO> transferTool =
                        new BillTransferTool<AggXuefeijmHVO>(clientFullVOs);
                AceXuefeijmHVOUnSendApproveBP bp = new AceXuefeijmHVOUnSendApproveBP();
                AggXuefeijmHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggXuefeijmHVO[] pubapprovebills(AggXuefeijmHVO[] clientFullVOs,
                        AggXuefeijmHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggXuefeijmHVO> transferTool =
                        new BillTransferTool<AggXuefeijmHVO>(clientFullVOs);
                AceXuefeijmHVOApproveBP bp = new AceXuefeijmHVOApproveBP();
                AggXuefeijmHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggXuefeijmHVO[] pubunapprovebills(AggXuefeijmHVO[] clientFullVOs,
                        AggXuefeijmHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggXuefeijmHVO> transferTool =
                        new BillTransferTool<AggXuefeijmHVO>(clientFullVOs);
                AceXuefeijmHVOUnApproveBP bp = new AceXuefeijmHVOUnApproveBP();
                AggXuefeijmHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}