
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.studentfile.AggStudentHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceStudentHVOUnApproveBP {

        public AggStudentHVO[] unApprove(AggStudentHVO[] clientBills,
                        AggStudentHVO[] originBills) {
                for (AggStudentHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggStudentHVO> update = new BillUpdate<AggStudentHVO>();
                AggStudentHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
