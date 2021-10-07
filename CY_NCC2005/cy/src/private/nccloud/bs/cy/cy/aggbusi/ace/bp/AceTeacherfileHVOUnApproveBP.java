
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.teacherfile.AggTeacherfileHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceTeacherfileHVOUnApproveBP {

        public AggTeacherfileHVO[] unApprove(AggTeacherfileHVO[] clientBills,
                        AggTeacherfileHVO[] originBills) {
                for (AggTeacherfileHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggTeacherfileHVO> update = new BillUpdate<AggTeacherfileHVO>();
                AggTeacherfileHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
