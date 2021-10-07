
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AcePlantravelHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AcePlantravelHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AcePlantravelHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AcePlantravelHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AcePlantravelHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AcePlantravelHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AcePlantravelHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.plantravel.AggPlantravelHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiPlantravelHVOPubServiceImpl {
        // 新增
        public AggPlantravelHVO[] pubinsertBills(AggPlantravelHVO[] clientFullVOs,
                        AggPlantravelHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggPlantravelHVO> transferTool = new BillTransferTool<AggPlantravelHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AcePlantravelHVOInsertBP action = new AcePlantravelHVOInsertBP();
                                AggPlantravelHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggPlantravelHVO[] clientFullVOs,
                        AggPlantravelHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggPlantravelHVO> transferTool =
                        new BillTransferTool<AggPlantravelHVO>(clientFullVOs);
                        // 调用BP
                        new AcePlantravelHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggPlantravelHVO[] pubupdateBills(AggPlantravelHVO[] clientFullVOs,
                        AggPlantravelHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggPlantravelHVO> transferTool = new BillTransferTool<AggPlantravelHVO>(
                                        clientFullVOs);
                        AcePlantravelHVOUpdateBP bp = new AcePlantravelHVOUpdateBP();
                        AggPlantravelHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggPlantravelHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggPlantravelHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggPlantravelHVO> query = new BillLazyQuery<AggPlantravelHVO>(
                                        AggPlantravelHVO.class);
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
        public AggPlantravelHVO[] pubsendapprovebills(
                        AggPlantravelHVO[] clientFullVOs, AggPlantravelHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggPlantravelHVO> transferTool =
                new BillTransferTool<AggPlantravelHVO>(clientFullVOs);
                AcePlantravelHVOSendApproveBP bp = new AcePlantravelHVOSendApproveBP();
                AggPlantravelHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggPlantravelHVO[] pubunsendapprovebills(
                        AggPlantravelHVO[] clientFullVOs, AggPlantravelHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggPlantravelHVO> transferTool =
                        new BillTransferTool<AggPlantravelHVO>(clientFullVOs);
                AcePlantravelHVOUnSendApproveBP bp = new AcePlantravelHVOUnSendApproveBP();
                AggPlantravelHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggPlantravelHVO[] pubapprovebills(AggPlantravelHVO[] clientFullVOs,
                        AggPlantravelHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggPlantravelHVO> transferTool =
                        new BillTransferTool<AggPlantravelHVO>(clientFullVOs);
                AcePlantravelHVOApproveBP bp = new AcePlantravelHVOApproveBP();
                AggPlantravelHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggPlantravelHVO[] pubunapprovebills(AggPlantravelHVO[] clientFullVOs,
                        AggPlantravelHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggPlantravelHVO> transferTool =
                        new BillTransferTool<AggPlantravelHVO>(clientFullVOs);
                AcePlantravelHVOUnApproveBP bp = new AcePlantravelHVOUnApproveBP();
                AggPlantravelHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}