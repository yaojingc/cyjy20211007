
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.msgstation.AggMsgstationHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceMsgstationHVOUnSendApproveBP {

        public AggMsgstationHVO[] unSend(AggMsgstationHVO[] clientBills,
                        AggMsgstationHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggMsgstationHVO> update = new BillUpdate<AggMsgstationHVO>();
                AggMsgstationHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggMsgstationHVO[] clientBills) {
                for (AggMsgstationHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
