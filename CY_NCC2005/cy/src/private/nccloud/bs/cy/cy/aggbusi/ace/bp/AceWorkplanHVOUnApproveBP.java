
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.workplan.AggWorkplanHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceWorkplanHVOUnApproveBP {

        public AggWorkplanHVO[] unApprove(AggWorkplanHVO[] clientBills,
                        AggWorkplanHVO[] originBills) {
                for (AggWorkplanHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggWorkplanHVO> update = new BillUpdate<AggWorkplanHVO>();
                AggWorkplanHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
