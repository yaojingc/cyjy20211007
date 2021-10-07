
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSalesmanfileHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSalesmanfileHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSalesmanfileHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSalesmanfileHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSalesmanfileHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSalesmanfileHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSalesmanfileHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.salesmanfile.AggSalesmanfileHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiSalesmanfileHVOPubServiceImpl {
        // 新增
        public AggSalesmanfileHVO[] pubinsertBills(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggSalesmanfileHVO> transferTool = new BillTransferTool<AggSalesmanfileHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceSalesmanfileHVOInsertBP action = new AceSalesmanfileHVOInsertBP();
                                AggSalesmanfileHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggSalesmanfileHVO> transferTool =
                        new BillTransferTool<AggSalesmanfileHVO>(clientFullVOs);
                        // 调用BP
                        new AceSalesmanfileHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggSalesmanfileHVO[] pubupdateBills(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggSalesmanfileHVO> transferTool = new BillTransferTool<AggSalesmanfileHVO>(
                                        clientFullVOs);
                        AceSalesmanfileHVOUpdateBP bp = new AceSalesmanfileHVOUpdateBP();
                        AggSalesmanfileHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggSalesmanfileHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggSalesmanfileHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggSalesmanfileHVO> query = new BillLazyQuery<AggSalesmanfileHVO>(
                                        AggSalesmanfileHVO.class);
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
        public AggSalesmanfileHVO[] pubsendapprovebills(
                        AggSalesmanfileHVO[] clientFullVOs, AggSalesmanfileHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggSalesmanfileHVO> transferTool =
                new BillTransferTool<AggSalesmanfileHVO>(clientFullVOs);
                AceSalesmanfileHVOSendApproveBP bp = new AceSalesmanfileHVOSendApproveBP();
                AggSalesmanfileHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggSalesmanfileHVO[] pubunsendapprovebills(
                        AggSalesmanfileHVO[] clientFullVOs, AggSalesmanfileHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggSalesmanfileHVO> transferTool =
                        new BillTransferTool<AggSalesmanfileHVO>(clientFullVOs);
                AceSalesmanfileHVOUnSendApproveBP bp = new AceSalesmanfileHVOUnSendApproveBP();
                AggSalesmanfileHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggSalesmanfileHVO[] pubapprovebills(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggSalesmanfileHVO> transferTool =
                        new BillTransferTool<AggSalesmanfileHVO>(clientFullVOs);
                AceSalesmanfileHVOApproveBP bp = new AceSalesmanfileHVOApproveBP();
                AggSalesmanfileHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggSalesmanfileHVO[] pubunapprovebills(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggSalesmanfileHVO> transferTool =
                        new BillTransferTool<AggSalesmanfileHVO>(clientFullVOs);
                AceSalesmanfileHVOUnApproveBP bp = new AceSalesmanfileHVOUnApproveBP();
                AggSalesmanfileHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}