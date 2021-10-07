
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceStumarkdataHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceStumarkdataHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceStumarkdataHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceStumarkdataHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceStumarkdataHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceStumarkdataHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceStumarkdataHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.stumarkdata.AggStumarkdataHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiStumarkdataHVOPubServiceImpl {
        // 新增
        public AggStumarkdataHVO[] pubinsertBills(AggStumarkdataHVO[] clientFullVOs,
                        AggStumarkdataHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggStumarkdataHVO> transferTool = new BillTransferTool<AggStumarkdataHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceStumarkdataHVOInsertBP action = new AceStumarkdataHVOInsertBP();
                                AggStumarkdataHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggStumarkdataHVO[] clientFullVOs,
                        AggStumarkdataHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggStumarkdataHVO> transferTool =
                        new BillTransferTool<AggStumarkdataHVO>(clientFullVOs);
                        // 调用BP
                        new AceStumarkdataHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggStumarkdataHVO[] pubupdateBills(AggStumarkdataHVO[] clientFullVOs,
                        AggStumarkdataHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggStumarkdataHVO> transferTool = new BillTransferTool<AggStumarkdataHVO>(
                                        clientFullVOs);
                        AceStumarkdataHVOUpdateBP bp = new AceStumarkdataHVOUpdateBP();
                        AggStumarkdataHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggStumarkdataHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggStumarkdataHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggStumarkdataHVO> query = new BillLazyQuery<AggStumarkdataHVO>(
                                        AggStumarkdataHVO.class);
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
        public AggStumarkdataHVO[] pubsendapprovebills(
                        AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggStumarkdataHVO> transferTool =
                new BillTransferTool<AggStumarkdataHVO>(clientFullVOs);
                AceStumarkdataHVOSendApproveBP bp = new AceStumarkdataHVOSendApproveBP();
                AggStumarkdataHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggStumarkdataHVO[] pubunsendapprovebills(
                        AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggStumarkdataHVO> transferTool =
                        new BillTransferTool<AggStumarkdataHVO>(clientFullVOs);
                AceStumarkdataHVOUnSendApproveBP bp = new AceStumarkdataHVOUnSendApproveBP();
                AggStumarkdataHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggStumarkdataHVO[] pubapprovebills(AggStumarkdataHVO[] clientFullVOs,
                        AggStumarkdataHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggStumarkdataHVO> transferTool =
                        new BillTransferTool<AggStumarkdataHVO>(clientFullVOs);
                AceStumarkdataHVOApproveBP bp = new AceStumarkdataHVOApproveBP();
                AggStumarkdataHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggStumarkdataHVO[] pubunapprovebills(AggStumarkdataHVO[] clientFullVOs,
                        AggStumarkdataHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggStumarkdataHVO> transferTool =
                        new BillTransferTool<AggStumarkdataHVO>(clientFullVOs);
                AceStumarkdataHVOUnApproveBP bp = new AceStumarkdataHVOUnApproveBP();
                AggStumarkdataHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}