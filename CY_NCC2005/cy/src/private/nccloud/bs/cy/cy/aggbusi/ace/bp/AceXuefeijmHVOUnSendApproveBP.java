
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.xuefeiapply.AggXuefeijmHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceXuefeijmHVOUnSendApproveBP {

        public AggXuefeijmHVO[] unSend(AggXuefeijmHVO[] clientBills,
                        AggXuefeijmHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggXuefeijmHVO> update = new BillUpdate<AggXuefeijmHVO>();
                AggXuefeijmHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggXuefeijmHVO[] clientBills) {
                for (AggXuefeijmHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
