
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceTaskallocationHVOUnSendApproveBP {

        public AggTaskallocationHVO[] unSend(AggTaskallocationHVO[] clientBills,
                        AggTaskallocationHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggTaskallocationHVO> update = new BillUpdate<AggTaskallocationHVO>();
                AggTaskallocationHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggTaskallocationHVO[] clientBills) {
                for (AggTaskallocationHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
