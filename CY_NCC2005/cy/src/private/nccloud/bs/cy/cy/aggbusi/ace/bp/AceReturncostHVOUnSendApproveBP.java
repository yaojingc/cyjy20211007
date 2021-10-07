
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.returncost.AggReturncostHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceReturncostHVOUnSendApproveBP {

        public AggReturncostHVO[] unSend(AggReturncostHVO[] clientBills,
                        AggReturncostHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggReturncostHVO> update = new BillUpdate<AggReturncostHVO>();
                AggReturncostHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggReturncostHVO[] clientBills) {
                for (AggReturncostHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
