
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceStudentHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceStudentHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceStudentHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceStudentHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceStudentHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceStudentHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceStudentHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.studentfile.AggStudentHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiStudentHVOPubServiceImpl {
        // 新增
        public AggStudentHVO[] pubinsertBills(AggStudentHVO[] clientFullVOs,
                        AggStudentHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggStudentHVO> transferTool = new BillTransferTool<AggStudentHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceStudentHVOInsertBP action = new AceStudentHVOInsertBP();
                                AggStudentHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggStudentHVO[] clientFullVOs,
                        AggStudentHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggStudentHVO> transferTool =
                        new BillTransferTool<AggStudentHVO>(clientFullVOs);
                        // 调用BP
                        new AceStudentHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggStudentHVO[] pubupdateBills(AggStudentHVO[] clientFullVOs,
                        AggStudentHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggStudentHVO> transferTool = new BillTransferTool<AggStudentHVO>(
                                        clientFullVOs);
                        AceStudentHVOUpdateBP bp = new AceStudentHVOUpdateBP();
                        AggStudentHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggStudentHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggStudentHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggStudentHVO> query = new BillLazyQuery<AggStudentHVO>(
                                        AggStudentHVO.class);
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
        public AggStudentHVO[] pubsendapprovebills(
                        AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggStudentHVO> transferTool =
                new BillTransferTool<AggStudentHVO>(clientFullVOs);
                AceStudentHVOSendApproveBP bp = new AceStudentHVOSendApproveBP();
                AggStudentHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggStudentHVO[] pubunsendapprovebills(
                        AggStudentHVO[] clientFullVOs, AggStudentHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggStudentHVO> transferTool =
                        new BillTransferTool<AggStudentHVO>(clientFullVOs);
                AceStudentHVOUnSendApproveBP bp = new AceStudentHVOUnSendApproveBP();
                AggStudentHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggStudentHVO[] pubapprovebills(AggStudentHVO[] clientFullVOs,
                        AggStudentHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggStudentHVO> transferTool =
                        new BillTransferTool<AggStudentHVO>(clientFullVOs);
                AceStudentHVOApproveBP bp = new AceStudentHVOApproveBP();
                AggStudentHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggStudentHVO[] pubunapprovebills(AggStudentHVO[] clientFullVOs,
                        AggStudentHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggStudentHVO> transferTool =
                        new BillTransferTool<AggStudentHVO>(clientFullVOs);
                AceStudentHVOUnApproveBP bp = new AceStudentHVOUnApproveBP();
                AggStudentHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}