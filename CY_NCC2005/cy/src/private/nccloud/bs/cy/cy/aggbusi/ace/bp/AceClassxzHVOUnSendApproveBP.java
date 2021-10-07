
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.classfilexz.AggClassxzHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceClassxzHVOUnSendApproveBP {

        public AggClassxzHVO[] unSend(AggClassxzHVO[] clientBills,
                        AggClassxzHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggClassxzHVO> update = new BillUpdate<AggClassxzHVO>();
                AggClassxzHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggClassxzHVO[] clientBills) {
                for (AggClassxzHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
