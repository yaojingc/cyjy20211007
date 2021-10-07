
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.workplan.AggWorkplanHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceWorkplanHVOUnSendApproveBP {

        public AggWorkplanHVO[] unSend(AggWorkplanHVO[] clientBills,
                        AggWorkplanHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggWorkplanHVO> update = new BillUpdate<AggWorkplanHVO>();
                AggWorkplanHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggWorkplanHVO[] clientBills) {
                for (AggWorkplanHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
