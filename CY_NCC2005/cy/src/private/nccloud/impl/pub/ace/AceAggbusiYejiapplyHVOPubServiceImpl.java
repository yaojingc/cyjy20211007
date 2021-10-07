
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceYejiapplyHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceYejiapplyHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceYejiapplyHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceYejiapplyHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceYejiapplyHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceYejiapplyHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceYejiapplyHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.yejiapply.AggYejiapplyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiYejiapplyHVOPubServiceImpl {
        // 新增
        public AggYejiapplyHVO[] pubinsertBills(AggYejiapplyHVO[] clientFullVOs,
                        AggYejiapplyHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggYejiapplyHVO> transferTool = new BillTransferTool<AggYejiapplyHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceYejiapplyHVOInsertBP action = new AceYejiapplyHVOInsertBP();
                                AggYejiapplyHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggYejiapplyHVO[] clientFullVOs,
                        AggYejiapplyHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggYejiapplyHVO> transferTool =
                        new BillTransferTool<AggYejiapplyHVO>(clientFullVOs);
                        // 调用BP
                        new AceYejiapplyHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggYejiapplyHVO[] pubupdateBills(AggYejiapplyHVO[] clientFullVOs,
                        AggYejiapplyHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggYejiapplyHVO> transferTool = new BillTransferTool<AggYejiapplyHVO>(
                                        clientFullVOs);
                        AceYejiapplyHVOUpdateBP bp = new AceYejiapplyHVOUpdateBP();
                        AggYejiapplyHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggYejiapplyHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggYejiapplyHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggYejiapplyHVO> query = new BillLazyQuery<AggYejiapplyHVO>(
                                        AggYejiapplyHVO.class);
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
        public AggYejiapplyHVO[] pubsendapprovebills(
                        AggYejiapplyHVO[] clientFullVOs, AggYejiapplyHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggYejiapplyHVO> transferTool =
                new BillTransferTool<AggYejiapplyHVO>(clientFullVOs);
                AceYejiapplyHVOSendApproveBP bp = new AceYejiapplyHVOSendApproveBP();
                AggYejiapplyHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggYejiapplyHVO[] pubunsendapprovebills(
                        AggYejiapplyHVO[] clientFullVOs, AggYejiapplyHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggYejiapplyHVO> transferTool =
                        new BillTransferTool<AggYejiapplyHVO>(clientFullVOs);
                AceYejiapplyHVOUnSendApproveBP bp = new AceYejiapplyHVOUnSendApproveBP();
                AggYejiapplyHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggYejiapplyHVO[] pubapprovebills(AggYejiapplyHVO[] clientFullVOs,
                        AggYejiapplyHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggYejiapplyHVO> transferTool =
                        new BillTransferTool<AggYejiapplyHVO>(clientFullVOs);
                AceYejiapplyHVOApproveBP bp = new AceYejiapplyHVOApproveBP();
                AggYejiapplyHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggYejiapplyHVO[] pubunapprovebills(AggYejiapplyHVO[] clientFullVOs,
                        AggYejiapplyHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggYejiapplyHVO> transferTool =
                        new BillTransferTool<AggYejiapplyHVO>(clientFullVOs);
                AceYejiapplyHVOUnApproveBP bp = new AceYejiapplyHVOUnApproveBP();
                AggYejiapplyHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}