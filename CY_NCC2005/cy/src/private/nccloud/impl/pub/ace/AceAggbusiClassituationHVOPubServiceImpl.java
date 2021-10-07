
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClassituationHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClassituationHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClassituationHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClassituationHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClassituationHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClassituationHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceClassituationHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.classituation.AggClassituationHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiClassituationHVOPubServiceImpl {
        // 新增
        public AggClassituationHVO[] pubinsertBills(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggClassituationHVO> transferTool = new BillTransferTool<AggClassituationHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceClassituationHVOInsertBP action = new AceClassituationHVOInsertBP();
                                AggClassituationHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggClassituationHVO> transferTool =
                        new BillTransferTool<AggClassituationHVO>(clientFullVOs);
                        // 调用BP
                        new AceClassituationHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggClassituationHVO[] pubupdateBills(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggClassituationHVO> transferTool = new BillTransferTool<AggClassituationHVO>(
                                        clientFullVOs);
                        AceClassituationHVOUpdateBP bp = new AceClassituationHVOUpdateBP();
                        AggClassituationHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggClassituationHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggClassituationHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggClassituationHVO> query = new BillLazyQuery<AggClassituationHVO>(
                                        AggClassituationHVO.class);
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
        public AggClassituationHVO[] pubsendapprovebills(
                        AggClassituationHVO[] clientFullVOs, AggClassituationHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggClassituationHVO> transferTool =
                new BillTransferTool<AggClassituationHVO>(clientFullVOs);
                AceClassituationHVOSendApproveBP bp = new AceClassituationHVOSendApproveBP();
                AggClassituationHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggClassituationHVO[] pubunsendapprovebills(
                        AggClassituationHVO[] clientFullVOs, AggClassituationHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggClassituationHVO> transferTool =
                        new BillTransferTool<AggClassituationHVO>(clientFullVOs);
                AceClassituationHVOUnSendApproveBP bp = new AceClassituationHVOUnSendApproveBP();
                AggClassituationHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggClassituationHVO[] pubapprovebills(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggClassituationHVO> transferTool =
                        new BillTransferTool<AggClassituationHVO>(clientFullVOs);
                AceClassituationHVOApproveBP bp = new AceClassituationHVOApproveBP();
                AggClassituationHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggClassituationHVO[] pubunapprovebills(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggClassituationHVO> transferTool =
                        new BillTransferTool<AggClassituationHVO>(clientFullVOs);
                AceClassituationHVOUnApproveBP bp = new AceClassituationHVOUnApproveBP();
                AggClassituationHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}