
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceCreditcontractHVOUnSendApproveBP {

        public AggCreditcontractHVO[] unSend(AggCreditcontractHVO[] clientBills,
                        AggCreditcontractHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggCreditcontractHVO> update = new BillUpdate<AggCreditcontractHVO>();
                AggCreditcontractHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggCreditcontractHVO[] clientBills) {
                for (AggCreditcontractHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
