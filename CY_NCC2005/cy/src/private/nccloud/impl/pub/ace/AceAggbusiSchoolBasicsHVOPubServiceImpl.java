
package nccloud.impl.pub.ace;

import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSchoolBasicsHVOApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSchoolBasicsHVODeleteBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSchoolBasicsHVOInsertBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSchoolBasicsHVOSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSchoolBasicsHVOUnApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSchoolBasicsHVOUnSendApproveBP;
import nccloud.bs.cy.cy.aggbusi.ace.bp.AceSchoolBasicsHVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiSchoolBasicsHVOPubServiceImpl {
        // 新增
        public AggSchoolBasicsHVO[] pubinsertBills(AggSchoolBasicsHVO[] clientFullVOs,
                        AggSchoolBasicsHVO[] originBills) throws BusinessException {
                try {
                        synchronized (clientFullVOs) {
                                // 数据库中数据和前台传递过来的差异VO合并后的结果
                                BillTransferTool<AggSchoolBasicsHVO> transferTool = new BillTransferTool<AggSchoolBasicsHVO>(
                                                clientFullVOs);
                                // 调用BP
                                AceSchoolBasicsHVOInsertBP action = new AceSchoolBasicsHVOInsertBP();
                                AggSchoolBasicsHVO[] retvos = action.insert(clientFullVOs);
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
        public void pubdeleteBills(AggSchoolBasicsHVO[] clientFullVOs,
                        AggSchoolBasicsHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                    BillTransferTool<AggSchoolBasicsHVO> transferTool =
                        new BillTransferTool<AggSchoolBasicsHVO>(clientFullVOs);
                        // 调用BP
                        new AceSchoolBasicsHVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggSchoolBasicsHVO[] pubupdateBills(AggSchoolBasicsHVO[] clientFullVOs,
                        AggSchoolBasicsHVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggSchoolBasicsHVO> transferTool = new BillTransferTool<AggSchoolBasicsHVO>(
                                        clientFullVOs);
                        AceSchoolBasicsHVOUpdateBP bp = new AceSchoolBasicsHVOUpdateBP();
                        AggSchoolBasicsHVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
                        return transferTool.getBillForToClient(retvos);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggSchoolBasicsHVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggSchoolBasicsHVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggSchoolBasicsHVO> query = new BillLazyQuery<AggSchoolBasicsHVO>(
                                        AggSchoolBasicsHVO.class);
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
        public AggSchoolBasicsHVO[] pubsendapprovebills(
                        AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
        BillTransferTool<AggSchoolBasicsHVO> transferTool =
                new BillTransferTool<AggSchoolBasicsHVO>(clientFullVOs);
                AceSchoolBasicsHVOSendApproveBP bp = new AceSchoolBasicsHVOSendApproveBP();
                AggSchoolBasicsHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggSchoolBasicsHVO[] pubunsendapprovebills(
                        AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills)
                        throws BusinessException {
                // 加锁 + 检查ts
                BillTransferTool<AggSchoolBasicsHVO> transferTool =
                        new BillTransferTool<AggSchoolBasicsHVO>(clientFullVOs);
                AceSchoolBasicsHVOUnSendApproveBP bp = new AceSchoolBasicsHVOUnSendApproveBP();
                AggSchoolBasicsHVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggSchoolBasicsHVO[] pubapprovebills(AggSchoolBasicsHVO[] clientFullVOs,
                        AggSchoolBasicsHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggSchoolBasicsHVO> transferTool =
                        new BillTransferTool<AggSchoolBasicsHVO>(clientFullVOs);
                AceSchoolBasicsHVOApproveBP bp = new AceSchoolBasicsHVOApproveBP();
                AggSchoolBasicsHVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

        public AggSchoolBasicsHVO[] pubunapprovebills(AggSchoolBasicsHVO[] clientFullVOs,
                        AggSchoolBasicsHVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 加锁 + 检查ts
                BillTransferTool<AggSchoolBasicsHVO> transferTool =
                        new BillTransferTool<AggSchoolBasicsHVO>(clientFullVOs);
                AceSchoolBasicsHVOUnApproveBP bp = new AceSchoolBasicsHVOUnApproveBP();
                AggSchoolBasicsHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}