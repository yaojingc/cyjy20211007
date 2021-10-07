
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTestpaperfileHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTestpaperfileHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTestpaperfileHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTestpaperfileHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTestpaperfileHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTestpaperfileHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceTestpaperfileHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.testpaperfile.AggTestpaperfileHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiTestpaperfileHVOPubServiceImpl {
        // 新增
        public AggTestpaperfileHVO[] pubinsertBills(AggTestpaperfileHVO[] clientFullVOs,
                        AggTestpaperfileHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggTestpaperfileHVO> transferTool = new BillTransferTool<AggTestpaperfileHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceTestpaperfileHVOInsertBP action = new AceTestpaperfileHVOInsertBP();
                                AggTestpaperfileHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggTestpaperfileHVO[] clientFullVOs,
                        AggTestpaperfileHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggTestpaperfileHVO> transferTool =
                        new BillTransferTool<AggTestpaperfileHVO>(clientFullVOs);
                        // 调用BP
                        new AceTestpaperfileHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggTestpaperfileHVO[] pubupdateBills(AggTestpaperfileHVO[] clientFullVOs,
                        AggTestpaperfileHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggTestpaperfileHVO> transferTool = new BillTransferTool<AggTestpaperfileHVO>(
                                        clientFullVOs);
                        AceTestpaperfileHVOUpdateBP bp = new AceTestpaperfileHVOUpdateBP();
                        AggTestpaperfileHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggTestpaperfileHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggTestpaperfileHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggTestpaperfileHVO> query = new BillLazyQuery<AggTestpaperfileHVO>(
                                        AggTestpaperfileHVO.class);
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
        public AggTestpaperfileHVO[] pubsendapprovebills(
                        AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggTestpaperfileHVO> transferTool =
                new BillTransferTool<AggTestpaperfileHVO>(clientFullVOs);
                AceTestpaperfileHVOSendApproveBP bp = new AceTestpaperfileHVOSendApproveBP();
                AggTestpaperfileHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggTestpaperfileHVO[] pubunsendapprovebills(
                        AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggTestpaperfileHVO> transferTool =
                        new BillTransferTool<AggTestpaperfileHVO>(clientFullVOs);
                AceTestpaperfileHVOUnSendApproveBP bp = new AceTestpaperfileHVOUnSendApproveBP();
                AggTestpaperfileHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggTestpaperfileHVO[] pubapprovebills(AggTestpaperfileHVO[] clientFullVOs,
                        AggTestpaperfileHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggTestpaperfileHVO> transferTool =
                        new BillTransferTool<AggTestpaperfileHVO>(clientFullVOs);
                AceTestpaperfileHVOApproveBP bp = new AceTestpaperfileHVOApproveBP();
                AggTestpaperfileHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggTestpaperfileHVO[] pubunapprovebills(AggTestpaperfileHVO[] clientFullVOs,
                        AggTestpaperfileHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggTestpaperfileHVO> transferTool =
                        new BillTransferTool<AggTestpaperfileHVO>(clientFullVOs);
                AceTestpaperfileHVOUnApproveBP bp = new AceTestpaperfileHVOUnApproveBP();
                AggTestpaperfileHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}