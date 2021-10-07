
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceJiedaiapplyHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceJiedaiapplyHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceJiedaiapplyHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceJiedaiapplyHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceJiedaiapplyHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceJiedaiapplyHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceJiedaiapplyHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.jiedaiapply.AggJiedaiapplyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiJiedaiapplyHVOPubServiceImpl {
        // 新增
        public AggJiedaiapplyHVO[] pubinsertBills(AggJiedaiapplyHVO[] clientFullVOs,
                        AggJiedaiapplyHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggJiedaiapplyHVO> transferTool = new BillTransferTool<AggJiedaiapplyHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceJiedaiapplyHVOInsertBP action = new AceJiedaiapplyHVOInsertBP();
                                AggJiedaiapplyHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggJiedaiapplyHVO[] clientFullVOs,
                        AggJiedaiapplyHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggJiedaiapplyHVO> transferTool =
                        new BillTransferTool<AggJiedaiapplyHVO>(clientFullVOs);
                        // 调用BP
                        new AceJiedaiapplyHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggJiedaiapplyHVO[] pubupdateBills(AggJiedaiapplyHVO[] clientFullVOs,
                        AggJiedaiapplyHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggJiedaiapplyHVO> transferTool = new BillTransferTool<AggJiedaiapplyHVO>(
                                        clientFullVOs);
                        AceJiedaiapplyHVOUpdateBP bp = new AceJiedaiapplyHVOUpdateBP();
                        AggJiedaiapplyHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggJiedaiapplyHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggJiedaiapplyHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggJiedaiapplyHVO> query = new BillLazyQuery<AggJiedaiapplyHVO>(
                                        AggJiedaiapplyHVO.class);
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
        public AggJiedaiapplyHVO[] pubsendapprovebills(
                        AggJiedaiapplyHVO[] clientFullVOs, AggJiedaiapplyHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggJiedaiapplyHVO> transferTool =
                new BillTransferTool<AggJiedaiapplyHVO>(clientFullVOs);
                AceJiedaiapplyHVOSendApproveBP bp = new AceJiedaiapplyHVOSendApproveBP();
                AggJiedaiapplyHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggJiedaiapplyHVO[] pubunsendapprovebills(
                        AggJiedaiapplyHVO[] clientFullVOs, AggJiedaiapplyHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggJiedaiapplyHVO> transferTool =
                        new BillTransferTool<AggJiedaiapplyHVO>(clientFullVOs);
                AceJiedaiapplyHVOUnSendApproveBP bp = new AceJiedaiapplyHVOUnSendApproveBP();
                AggJiedaiapplyHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggJiedaiapplyHVO[] pubapprovebills(AggJiedaiapplyHVO[] clientFullVOs,
                        AggJiedaiapplyHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggJiedaiapplyHVO> transferTool =
                        new BillTransferTool<AggJiedaiapplyHVO>(clientFullVOs);
                AceJiedaiapplyHVOApproveBP bp = new AceJiedaiapplyHVOApproveBP();
                AggJiedaiapplyHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggJiedaiapplyHVO[] pubunapprovebills(AggJiedaiapplyHVO[] clientFullVOs,
                        AggJiedaiapplyHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggJiedaiapplyHVO> transferTool =
                        new BillTransferTool<AggJiedaiapplyHVO>(clientFullVOs);
                AceJiedaiapplyHVOUnApproveBP bp = new AceJiedaiapplyHVOUnApproveBP();
                AggJiedaiapplyHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}