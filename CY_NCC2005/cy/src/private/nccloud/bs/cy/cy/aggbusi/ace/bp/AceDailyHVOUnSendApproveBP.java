
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.daily.AggDailyHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceDailyHVOUnSendApproveBP {

        public AggDailyHVO[] unSend(AggDailyHVO[] clientBills,
                        AggDailyHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggDailyHVO> update = new BillUpdate<AggDailyHVO>();
                AggDailyHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggDailyHVO[] clientBills) {
                for (AggDailyHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
