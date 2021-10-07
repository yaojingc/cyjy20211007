
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTeacherformHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTeacherformHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTeacherformHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTeacherformHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTeacherformHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTeacherformHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTeacherformHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.teacherform.AggTeacherformHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiTeacherformHVOPubServiceImpl {
        // 新增
        public AggTeacherformHVO[] pubinsertBills(AggTeacherformHVO[] clientFullVOs,
                        AggTeacherformHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggTeacherformHVO> transferTool = new BillTransferTool<AggTeacherformHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceTeacherformHVOInsertBP action = new AceTeacherformHVOInsertBP();
                                AggTeacherformHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggTeacherformHVO[] clientFullVOs,
                        AggTeacherformHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggTeacherformHVO> transferTool =
                        new BillTransferTool<AggTeacherformHVO>(clientFullVOs);
                        // 调用BP
                        new AceTeacherformHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggTeacherformHVO[] pubupdateBills(AggTeacherformHVO[] clientFullVOs,
                        AggTeacherformHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggTeacherformHVO> transferTool = new BillTransferTool<AggTeacherformHVO>(
                                        clientFullVOs);
                        AceTeacherformHVOUpdateBP bp = new AceTeacherformHVOUpdateBP();
                        AggTeacherformHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggTeacherformHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggTeacherformHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggTeacherformHVO> query = new BillLazyQuery<AggTeacherformHVO>(
                                        AggTeacherformHVO.class);
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
        public AggTeacherformHVO[] pubsendapprovebills(
                        AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggTeacherformHVO> transferTool =
                new BillTransferTool<AggTeacherformHVO>(clientFullVOs);
                AceTeacherformHVOSendApproveBP bp = new AceTeacherformHVOSendApproveBP();
                AggTeacherformHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggTeacherformHVO[] pubunsendapprovebills(
                        AggTeacherformHVO[] clientFullVOs, AggTeacherformHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggTeacherformHVO> transferTool =
                        new BillTransferTool<AggTeacherformHVO>(clientFullVOs);
                AceTeacherformHVOUnSendApproveBP bp = new AceTeacherformHVOUnSendApproveBP();
                AggTeacherformHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggTeacherformHVO[] pubapprovebills(AggTeacherformHVO[] clientFullVOs,
                        AggTeacherformHVO[] originBills) throws Exception {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggTeacherformHVO> transferTool =
                        new BillTransferTool<AggTeacherformHVO>(clientFullVOs);
                AceTeacherformHVOApproveBP bp = new AceTeacherformHVOApproveBP();
                AggTeacherformHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggTeacherformHVO[] pubunapprovebills(AggTeacherformHVO[] clientFullVOs,
                        AggTeacherformHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggTeacherformHVO> transferTool =
                        new BillTransferTool<AggTeacherformHVO>(clientFullVOs);
                AceTeacherformHVOUnApproveBP bp = new AceTeacherformHVOUnApproveBP();
                AggTeacherformHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}