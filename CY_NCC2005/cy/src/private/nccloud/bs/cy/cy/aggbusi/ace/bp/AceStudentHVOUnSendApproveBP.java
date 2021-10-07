
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.studentfile.AggStudentHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceStudentHVOUnSendApproveBP {

        public AggStudentHVO[] unSend(AggStudentHVO[] clientBills,
                        AggStudentHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggStudentHVO> update = new BillUpdate<AggStudentHVO>();
                AggStudentHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggStudentHVO[] clientBills) {
                for (AggStudentHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
