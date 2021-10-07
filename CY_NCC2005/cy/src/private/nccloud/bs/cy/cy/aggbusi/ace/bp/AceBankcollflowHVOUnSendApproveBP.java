
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.bankcollflow.AggBankcollflowHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceBankcollflowHVOUnSendApproveBP {

        public AggBankcollflowHVO[] unSend(AggBankcollflowHVO[] clientBills,
                        AggBankcollflowHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggBankcollflowHVO> update = new BillUpdate<AggBankcollflowHVO>();
                AggBankcollflowHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggBankcollflowHVO[] clientBills) {
                for (AggBankcollflowHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
