
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTaskfeedbackHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTaskfeedbackHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTaskfeedbackHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTaskfeedbackHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTaskfeedbackHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTaskfeedbackHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTaskfeedbackHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.taskfeedback.AggTaskfeedbackHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiTaskfeedbackHVOPubServiceImpl {
        // 新增
        public AggTaskfeedbackHVO[] pubinsertBills(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggTaskfeedbackHVO> transferTool = new BillTransferTool<AggTaskfeedbackHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceTaskfeedbackHVOInsertBP action = new AceTaskfeedbackHVOInsertBP();
                                AggTaskfeedbackHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggTaskfeedbackHVO> transferTool =
                        new BillTransferTool<AggTaskfeedbackHVO>(clientFullVOs);
                        // 调用BP
                        new AceTaskfeedbackHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggTaskfeedbackHVO[] pubupdateBills(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggTaskfeedbackHVO> transferTool = new BillTransferTool<AggTaskfeedbackHVO>(
                                        clientFullVOs);
                        AceTaskfeedbackHVOUpdateBP bp = new AceTaskfeedbackHVOUpdateBP();
                        AggTaskfeedbackHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggTaskfeedbackHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggTaskfeedbackHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggTaskfeedbackHVO> query = new BillLazyQuery<AggTaskfeedbackHVO>(
                                        AggTaskfeedbackHVO.class);
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
        public AggTaskfeedbackHVO[] pubsendapprovebills(
                        AggTaskfeedbackHVO[] clientFullVOs, AggTaskfeedbackHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggTaskfeedbackHVO> transferTool =
                new BillTransferTool<AggTaskfeedbackHVO>(clientFullVOs);
                AceTaskfeedbackHVOSendApproveBP bp = new AceTaskfeedbackHVOSendApproveBP();
                AggTaskfeedbackHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggTaskfeedbackHVO[] pubunsendapprovebills(
                        AggTaskfeedbackHVO[] clientFullVOs, AggTaskfeedbackHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggTaskfeedbackHVO> transferTool =
                        new BillTransferTool<AggTaskfeedbackHVO>(clientFullVOs);
                AceTaskfeedbackHVOUnSendApproveBP bp = new AceTaskfeedbackHVOUnSendApproveBP();
                AggTaskfeedbackHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggTaskfeedbackHVO[] pubapprovebills(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggTaskfeedbackHVO> transferTool =
                        new BillTransferTool<AggTaskfeedbackHVO>(clientFullVOs);
                AceTaskfeedbackHVOApproveBP bp = new AceTaskfeedbackHVOApproveBP();
                AggTaskfeedbackHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggTaskfeedbackHVO[] pubunapprovebills(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggTaskfeedbackHVO> transferTool =
                        new BillTransferTool<AggTaskfeedbackHVO>(clientFullVOs);
                AceTaskfeedbackHVOUnApproveBP bp = new AceTaskfeedbackHVOUnApproveBP();
                AggTaskfeedbackHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}