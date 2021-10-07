
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceTaskallocationHVOUnApproveBP {

        public AggTaskallocationHVO[] unApprove(AggTaskallocationHVO[] clientBills,
                        AggTaskallocationHVO[] originBills) {
                for (AggTaskallocationHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggTaskallocationHVO> update = new BillUpdate<AggTaskallocationHVO>();
                AggTaskallocationHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
