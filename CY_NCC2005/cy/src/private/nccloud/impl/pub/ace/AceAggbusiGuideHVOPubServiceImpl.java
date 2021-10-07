
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceGuideHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceGuideHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceGuideHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceGuideHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceGuideHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceGuideHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceGuideHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.guide.AggGuideHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiGuideHVOPubServiceImpl {
        // 新增
        public AggGuideHVO[] pubinsertBills(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggGuideHVO> transferTool = new BillTransferTool<AggGuideHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceGuideHVOInsertBP action = new AceGuideHVOInsertBP();
                                AggGuideHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggGuideHVO> transferTool =
                        new BillTransferTool<AggGuideHVO>(clientFullVOs);
                        // 调用BP
                        new AceGuideHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggGuideHVO[] pubupdateBills(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggGuideHVO> transferTool = new BillTransferTool<AggGuideHVO>(
                                        clientFullVOs);
                        AceGuideHVOUpdateBP bp = new AceGuideHVOUpdateBP();
                        AggGuideHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggGuideHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggGuideHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggGuideHVO> query = new BillLazyQuery<AggGuideHVO>(
                                        AggGuideHVO.class);
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
        public AggGuideHVO[] pubsendapprovebills(
                        AggGuideHVO[] clientFullVOs, AggGuideHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggGuideHVO> transferTool =
                new BillTransferTool<AggGuideHVO>(clientFullVOs);
                AceGuideHVOSendApproveBP bp = new AceGuideHVOSendApproveBP();
                AggGuideHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggGuideHVO[] pubunsendapprovebills(
                        AggGuideHVO[] clientFullVOs, AggGuideHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggGuideHVO> transferTool =
                        new BillTransferTool<AggGuideHVO>(clientFullVOs);
                AceGuideHVOUnSendApproveBP bp = new AceGuideHVOUnSendApproveBP();
                AggGuideHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggGuideHVO[] pubapprovebills(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggGuideHVO> transferTool =
                        new BillTransferTool<AggGuideHVO>(clientFullVOs);
                AceGuideHVOApproveBP bp = new AceGuideHVOApproveBP();
                AggGuideHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggGuideHVO[] pubunapprovebills(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggGuideHVO> transferTool =
                        new BillTransferTool<AggGuideHVO>(clientFullVOs);
                AceGuideHVOUnApproveBP bp = new AceGuideHVOUnApproveBP();
                AggGuideHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}