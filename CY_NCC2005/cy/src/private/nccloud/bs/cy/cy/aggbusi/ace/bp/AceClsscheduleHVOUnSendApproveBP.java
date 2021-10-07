
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.clsschedule.AggClsscheduleHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceClsscheduleHVOUnSendApproveBP {

        public AggClsscheduleHVO[] unSend(AggClsscheduleHVO[] clientBills,
                        AggClsscheduleHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggClsscheduleHVO> update = new BillUpdate<AggClsscheduleHVO>();
                AggClsscheduleHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggClsscheduleHVO[] clientBills) {
                for (AggClsscheduleHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
