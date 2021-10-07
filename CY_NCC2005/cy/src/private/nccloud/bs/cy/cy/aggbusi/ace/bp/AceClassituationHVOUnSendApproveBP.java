
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.classituation.AggClassituationHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceClassituationHVOUnSendApproveBP {

        public AggClassituationHVO[] unSend(AggClassituationHVO[] clientBills,
                        AggClassituationHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggClassituationHVO> update = new BillUpdate<AggClassituationHVO>();
                AggClassituationHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggClassituationHVO[] clientBills) {
                for (AggClassituationHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
