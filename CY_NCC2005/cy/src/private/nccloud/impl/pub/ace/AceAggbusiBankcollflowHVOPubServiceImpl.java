
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceBankcollflowHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceBankcollflowHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceBankcollflowHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceBankcollflowHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceBankcollflowHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceBankcollflowHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceBankcollflowHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.bankcollflow.AggBankcollflowHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiBankcollflowHVOPubServiceImpl {
        // 新增
        public AggBankcollflowHVO[] pubinsertBills(AggBankcollflowHVO[] clientFullVOs,
                        AggBankcollflowHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggBankcollflowHVO> transferTool = new BillTransferTool<AggBankcollflowHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceBankcollflowHVOInsertBP action = new AceBankcollflowHVOInsertBP();
                                AggBankcollflowHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggBankcollflowHVO[] clientFullVOs,
                        AggBankcollflowHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggBankcollflowHVO> transferTool =
                        new BillTransferTool<AggBankcollflowHVO>(clientFullVOs);
                        // 调用BP
                        new AceBankcollflowHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggBankcollflowHVO[] pubupdateBills(AggBankcollflowHVO[] clientFullVOs,
                        AggBankcollflowHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggBankcollflowHVO> transferTool = new BillTransferTool<AggBankcollflowHVO>(
                                        clientFullVOs);
                        AceBankcollflowHVOUpdateBP bp = new AceBankcollflowHVOUpdateBP();
                        AggBankcollflowHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggBankcollflowHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggBankcollflowHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggBankcollflowHVO> query = new BillLazyQuery<AggBankcollflowHVO>(
                                        AggBankcollflowHVO.class);
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
        public AggBankcollflowHVO[] pubsendapprovebills(
                        AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggBankcollflowHVO> transferTool =
                new BillTransferTool<AggBankcollflowHVO>(clientFullVOs);
                AceBankcollflowHVOSendApproveBP bp = new AceBankcollflowHVOSendApproveBP();
                AggBankcollflowHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggBankcollflowHVO[] pubunsendapprovebills(
                        AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggBankcollflowHVO> transferTool =
                        new BillTransferTool<AggBankcollflowHVO>(clientFullVOs);
                AceBankcollflowHVOUnSendApproveBP bp = new AceBankcollflowHVOUnSendApproveBP();
                AggBankcollflowHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggBankcollflowHVO[] pubapprovebills(AggBankcollflowHVO[] clientFullVOs,
                        AggBankcollflowHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggBankcollflowHVO> transferTool =
                        new BillTransferTool<AggBankcollflowHVO>(clientFullVOs);
                AceBankcollflowHVOApproveBP bp = new AceBankcollflowHVOApproveBP();
                AggBankcollflowHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggBankcollflowHVO[] pubunapprovebills(AggBankcollflowHVO[] clientFullVOs,
                        AggBankcollflowHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggBankcollflowHVO> transferTool =
                        new BillTransferTool<AggBankcollflowHVO>(clientFullVOs);
                AceBankcollflowHVOUnApproveBP bp = new AceBankcollflowHVOUnApproveBP();
                AggBankcollflowHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}