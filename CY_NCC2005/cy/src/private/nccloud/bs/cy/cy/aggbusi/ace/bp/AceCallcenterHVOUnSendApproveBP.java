
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.callcenter.AggCallcenterHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceCallcenterHVOUnSendApproveBP {

        public AggCallcenterHVO[] unSend(AggCallcenterHVO[] clientBills,
                        AggCallcenterHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggCallcenterHVO> update = new BillUpdate<AggCallcenterHVO>();
                AggCallcenterHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggCallcenterHVO[] clientBills) {
                for (AggCallcenterHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
