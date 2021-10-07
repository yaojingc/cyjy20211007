
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClasscyHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClasscyHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClasscyHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClasscyHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClasscyHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClasscyHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClasscyHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiClasscyHVOPubServiceImpl {
        // 新增
        public AggClasscyHVO[] pubinsertBills(AggClasscyHVO[] clientFullVOs,
                        AggClasscyHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggClasscyHVO> transferTool = new BillTransferTool<AggClasscyHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceClasscyHVOInsertBP action = new AceClasscyHVOInsertBP();
                                AggClasscyHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggClasscyHVO[] clientFullVOs,
                        AggClasscyHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggClasscyHVO> transferTool =
                        new BillTransferTool<AggClasscyHVO>(clientFullVOs);
                        // 调用BP
                        new AceClasscyHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggClasscyHVO[] pubupdateBills(AggClasscyHVO[] clientFullVOs,
                        AggClasscyHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggClasscyHVO> transferTool = new BillTransferTool<AggClasscyHVO>(
                                        clientFullVOs);
                        AceClasscyHVOUpdateBP bp = new AceClasscyHVOUpdateBP();
                        AggClasscyHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggClasscyHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggClasscyHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggClasscyHVO> query = new BillLazyQuery<AggClasscyHVO>(
                                        AggClasscyHVO.class);
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
        public AggClasscyHVO[] pubsendapprovebills(
                        AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggClasscyHVO> transferTool =
                new BillTransferTool<AggClasscyHVO>(clientFullVOs);
                AceClasscyHVOSendApproveBP bp = new AceClasscyHVOSendApproveBP();
                AggClasscyHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggClasscyHVO[] pubunsendapprovebills(
                        AggClasscyHVO[] clientFullVOs, AggClasscyHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggClasscyHVO> transferTool =
                        new BillTransferTool<AggClasscyHVO>(clientFullVOs);
                AceClasscyHVOUnSendApproveBP bp = new AceClasscyHVOUnSendApproveBP();
                AggClasscyHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggClasscyHVO[] pubapprovebills(AggClasscyHVO[] clientFullVOs,
                        AggClasscyHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggClasscyHVO> transferTool =
                        new BillTransferTool<AggClasscyHVO>(clientFullVOs);
                AceClasscyHVOApproveBP bp = new AceClasscyHVOApproveBP();
                AggClasscyHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggClasscyHVO[] pubunapprovebills(AggClasscyHVO[] clientFullVOs,
                        AggClasscyHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggClasscyHVO> transferTool =
                        new BillTransferTool<AggClasscyHVO>(clientFullVOs);
                AceClasscyHVOUnApproveBP bp = new AceClasscyHVOUnApproveBP();
                AggClasscyHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}