
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTeacherfileHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTeacherfileHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTeacherfileHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTeacherfileHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTeacherfileHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTeacherfileHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTeacherfileHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.teacherfile.AggTeacherfileHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiTeacherfileHVOPubServiceImpl {
        // 新增
        public AggTeacherfileHVO[] pubinsertBills(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggTeacherfileHVO> transferTool = new BillTransferTool<AggTeacherfileHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceTeacherfileHVOInsertBP action = new AceTeacherfileHVOInsertBP();
                                AggTeacherfileHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggTeacherfileHVO> transferTool =
                        new BillTransferTool<AggTeacherfileHVO>(clientFullVOs);
                        // 调用BP
                        new AceTeacherfileHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggTeacherfileHVO[] pubupdateBills(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggTeacherfileHVO> transferTool = new BillTransferTool<AggTeacherfileHVO>(
                                        clientFullVOs);
                        AceTeacherfileHVOUpdateBP bp = new AceTeacherfileHVOUpdateBP();
                        AggTeacherfileHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggTeacherfileHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggTeacherfileHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggTeacherfileHVO> query = new BillLazyQuery<AggTeacherfileHVO>(
                                        AggTeacherfileHVO.class);
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
        public AggTeacherfileHVO[] pubsendapprovebills(
                        AggTeacherfileHVO[] clientFullVOs, AggTeacherfileHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggTeacherfileHVO> transferTool =
                new BillTransferTool<AggTeacherfileHVO>(clientFullVOs);
                AceTeacherfileHVOSendApproveBP bp = new AceTeacherfileHVOSendApproveBP();
                AggTeacherfileHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggTeacherfileHVO[] pubunsendapprovebills(
                        AggTeacherfileHVO[] clientFullVOs, AggTeacherfileHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggTeacherfileHVO> transferTool =
                        new BillTransferTool<AggTeacherfileHVO>(clientFullVOs);
                AceTeacherfileHVOUnSendApproveBP bp = new AceTeacherfileHVOUnSendApproveBP();
                AggTeacherfileHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggTeacherfileHVO[] pubapprovebills(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggTeacherfileHVO> transferTool =
                        new BillTransferTool<AggTeacherfileHVO>(clientFullVOs);
                AceTeacherfileHVOApproveBP bp = new AceTeacherfileHVOApproveBP();
                AggTeacherfileHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggTeacherfileHVO[] pubunapprovebills(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggTeacherfileHVO> transferTool =
                        new BillTransferTool<AggTeacherfileHVO>(clientFullVOs);
                AceTeacherfileHVOUnApproveBP bp = new AceTeacherfileHVOUnApproveBP();
                AggTeacherfileHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}