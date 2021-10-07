
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.jiedaiapply.AggJiedaiapplyHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceJiedaiapplyHVOUnSendApproveBP {

        public AggJiedaiapplyHVO[] unSend(AggJiedaiapplyHVO[] clientBills,
                        AggJiedaiapplyHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggJiedaiapplyHVO> update = new BillUpdate<AggJiedaiapplyHVO>();
                AggJiedaiapplyHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggJiedaiapplyHVO[] clientBills) {
                for (AggJiedaiapplyHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
