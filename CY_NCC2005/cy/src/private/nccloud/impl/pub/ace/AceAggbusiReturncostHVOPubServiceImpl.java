
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceReturncostHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceReturncostHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceReturncostHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceReturncostHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceReturncostHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceReturncostHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceReturncostHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.returncost.AggReturncostHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiReturncostHVOPubServiceImpl {
        // 新增
        public AggReturncostHVO[] pubinsertBills(AggReturncostHVO[] clientFullVOs,
                        AggReturncostHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggReturncostHVO> transferTool = new BillTransferTool<AggReturncostHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceReturncostHVOInsertBP action = new AceReturncostHVOInsertBP();
                                AggReturncostHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggReturncostHVO[] clientFullVOs,
                        AggReturncostHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggReturncostHVO> transferTool =
                        new BillTransferTool<AggReturncostHVO>(clientFullVOs);
                        // 调用BP
                        new AceReturncostHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggReturncostHVO[] pubupdateBills(AggReturncostHVO[] clientFullVOs,
                        AggReturncostHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggReturncostHVO> transferTool = new BillTransferTool<AggReturncostHVO>(
                                        clientFullVOs);
                        AceReturncostHVOUpdateBP bp = new AceReturncostHVOUpdateBP();
                        AggReturncostHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggReturncostHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggReturncostHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggReturncostHVO> query = new BillLazyQuery<AggReturncostHVO>(
                                        AggReturncostHVO.class);
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
        public AggReturncostHVO[] pubsendapprovebills(
                        AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggReturncostHVO> transferTool =
                new BillTransferTool<AggReturncostHVO>(clientFullVOs);
                AceReturncostHVOSendApproveBP bp = new AceReturncostHVOSendApproveBP();
                AggReturncostHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggReturncostHVO[] pubunsendapprovebills(
                        AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggReturncostHVO> transferTool =
                        new BillTransferTool<AggReturncostHVO>(clientFullVOs);
                AceReturncostHVOUnSendApproveBP bp = new AceReturncostHVOUnSendApproveBP();
                AggReturncostHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggReturncostHVO[] pubapprovebills(AggReturncostHVO[] clientFullVOs,
                        AggReturncostHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggReturncostHVO> transferTool =
                        new BillTransferTool<AggReturncostHVO>(clientFullVOs);
                AceReturncostHVOApproveBP bp = new AceReturncostHVOApproveBP();
                AggReturncostHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggReturncostHVO[] pubunapprovebills(AggReturncostHVO[] clientFullVOs,
                        AggReturncostHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggReturncostHVO> transferTool =
                        new BillTransferTool<AggReturncostHVO>(clientFullVOs);
                AceReturncostHVOUnApproveBP bp = new AceReturncostHVOUnApproveBP();
                AggReturncostHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}