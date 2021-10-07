
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClassxzHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClassxzHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClassxzHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClassxzHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClassxzHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClassxzHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClassxzHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.classfilexz.AggClassxzHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiClassxzHVOPubServiceImpl {
        // 新增
        public AggClassxzHVO[] pubinsertBills(AggClassxzHVO[] clientFullVOs,
                        AggClassxzHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggClassxzHVO> transferTool = new BillTransferTool<AggClassxzHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceClassxzHVOInsertBP action = new AceClassxzHVOInsertBP();
                                AggClassxzHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggClassxzHVO[] clientFullVOs,
                        AggClassxzHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggClassxzHVO> transferTool =
                        new BillTransferTool<AggClassxzHVO>(clientFullVOs);
                        // 调用BP
                        new AceClassxzHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggClassxzHVO[] pubupdateBills(AggClassxzHVO[] clientFullVOs,
                        AggClassxzHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggClassxzHVO> transferTool = new BillTransferTool<AggClassxzHVO>(
                                        clientFullVOs);
                        AceClassxzHVOUpdateBP bp = new AceClassxzHVOUpdateBP();
                        AggClassxzHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggClassxzHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggClassxzHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggClassxzHVO> query = new BillLazyQuery<AggClassxzHVO>(
                                        AggClassxzHVO.class);
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
        public AggClassxzHVO[] pubsendapprovebills(
                        AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggClassxzHVO> transferTool =
                new BillTransferTool<AggClassxzHVO>(clientFullVOs);
                AceClassxzHVOSendApproveBP bp = new AceClassxzHVOSendApproveBP();
                AggClassxzHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggClassxzHVO[] pubunsendapprovebills(
                        AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggClassxzHVO> transferTool =
                        new BillTransferTool<AggClassxzHVO>(clientFullVOs);
                AceClassxzHVOUnSendApproveBP bp = new AceClassxzHVOUnSendApproveBP();
                AggClassxzHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggClassxzHVO[] pubapprovebills(AggClassxzHVO[] clientFullVOs,
                        AggClassxzHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggClassxzHVO> transferTool =
                        new BillTransferTool<AggClassxzHVO>(clientFullVOs);
                AceClassxzHVOApproveBP bp = new AceClassxzHVOApproveBP();
                AggClassxzHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggClassxzHVO[] pubunapprovebills(AggClassxzHVO[] clientFullVOs,
                        AggClassxzHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggClassxzHVO> transferTool =
                        new BillTransferTool<AggClassxzHVO>(clientFullVOs);
                AceClassxzHVOUnApproveBP bp = new AceClassxzHVOUnApproveBP();
                AggClassxzHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}