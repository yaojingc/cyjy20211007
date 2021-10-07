
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.guide.AggGuideHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceGuideHVOUnSendApproveBP {

        public AggGuideHVO[] unSend(AggGuideHVO[] clientBills,
                        AggGuideHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggGuideHVO> update = new BillUpdate<AggGuideHVO>();
                AggGuideHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggGuideHVO[] clientBills) {
                for (AggGuideHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
