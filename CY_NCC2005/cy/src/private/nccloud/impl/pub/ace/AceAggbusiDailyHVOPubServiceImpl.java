
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceDailyHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceDailyHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceDailyHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceDailyHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceDailyHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceDailyHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceDailyHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.daily.AggDailyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiDailyHVOPubServiceImpl {
        // 新增
        public AggDailyHVO[] pubinsertBills(AggDailyHVO[] clientFullVOs,
                        AggDailyHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggDailyHVO> transferTool = new BillTransferTool<AggDailyHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceDailyHVOInsertBP action = new AceDailyHVOInsertBP();
                                AggDailyHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggDailyHVO[] clientFullVOs,
                        AggDailyHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggDailyHVO> transferTool =
                        new BillTransferTool<AggDailyHVO>(clientFullVOs);
                        // 调用BP
                        new AceDailyHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggDailyHVO[] pubupdateBills(AggDailyHVO[] clientFullVOs,
                        AggDailyHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggDailyHVO> transferTool = new BillTransferTool<AggDailyHVO>(
                                        clientFullVOs);
                        AceDailyHVOUpdateBP bp = new AceDailyHVOUpdateBP();
                        AggDailyHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggDailyHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggDailyHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggDailyHVO> query = new BillLazyQuery<AggDailyHVO>(
                                        AggDailyHVO.class);
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
        public AggDailyHVO[] pubsendapprovebills(
                        AggDailyHVO[] clientFullVOs, AggDailyHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggDailyHVO> transferTool =
                new BillTransferTool<AggDailyHVO>(clientFullVOs);
                AceDailyHVOSendApproveBP bp = new AceDailyHVOSendApproveBP();
                AggDailyHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggDailyHVO[] pubunsendapprovebills(
                        AggDailyHVO[] clientFullVOs, AggDailyHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggDailyHVO> transferTool =
                        new BillTransferTool<AggDailyHVO>(clientFullVOs);
                AceDailyHVOUnSendApproveBP bp = new AceDailyHVOUnSendApproveBP();
                AggDailyHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggDailyHVO[] pubapprovebills(AggDailyHVO[] clientFullVOs,
                        AggDailyHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggDailyHVO> transferTool =
                        new BillTransferTool<AggDailyHVO>(clientFullVOs);
                AceDailyHVOApproveBP bp = new AceDailyHVOApproveBP();
                AggDailyHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggDailyHVO[] pubunapprovebills(AggDailyHVO[] clientFullVOs,
                        AggDailyHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggDailyHVO> transferTool =
                        new BillTransferTool<AggDailyHVO>(clientFullVOs);
                AceDailyHVOUnApproveBP bp = new AceDailyHVOUnApproveBP();
                AggDailyHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}