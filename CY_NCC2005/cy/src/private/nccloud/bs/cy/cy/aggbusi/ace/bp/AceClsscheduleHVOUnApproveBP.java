
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.clsschedule.AggClsscheduleHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceClsscheduleHVOUnApproveBP {

        public AggClsscheduleHVO[] unApprove(AggClsscheduleHVO[] clientBills,
                        AggClsscheduleHVO[] originBills) {
                for (AggClsscheduleHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggClsscheduleHVO> update = new BillUpdate<AggClsscheduleHVO>();
                AggClsscheduleHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
