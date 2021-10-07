
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceClasscyHVOUnSendApproveBP {

        public AggClasscyHVO[] unSend(AggClasscyHVO[] clientBills,
                        AggClasscyHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggClasscyHVO> update = new BillUpdate<AggClasscyHVO>();
                AggClasscyHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggClasscyHVO[] clientBills) {
                for (AggClasscyHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
