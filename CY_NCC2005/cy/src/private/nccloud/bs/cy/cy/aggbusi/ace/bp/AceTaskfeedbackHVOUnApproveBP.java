
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.taskfeedback.AggTaskfeedbackHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceTaskfeedbackHVOUnApproveBP {

        public AggTaskfeedbackHVO[] unApprove(AggTaskfeedbackHVO[] clientBills,
                        AggTaskfeedbackHVO[] originBills) {
                for (AggTaskfeedbackHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggTaskfeedbackHVO> update = new BillUpdate<AggTaskfeedbackHVO>();
                AggTaskfeedbackHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
