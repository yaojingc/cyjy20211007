
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.taskfeedback.AggTaskfeedbackHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceTaskfeedbackHVOUnSendApproveBP {

        public AggTaskfeedbackHVO[] unSend(AggTaskfeedbackHVO[] clientBills,
                        AggTaskfeedbackHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggTaskfeedbackHVO> update = new BillUpdate<AggTaskfeedbackHVO>();
                AggTaskfeedbackHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggTaskfeedbackHVO[] clientBills) {
                for (AggTaskfeedbackHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
