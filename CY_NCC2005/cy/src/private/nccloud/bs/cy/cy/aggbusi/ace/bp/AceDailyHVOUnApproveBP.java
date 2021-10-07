
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.daily.AggDailyHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceDailyHVOUnApproveBP {

        public AggDailyHVO[] unApprove(AggDailyHVO[] clientBills,
                        AggDailyHVO[] originBills) {
                for (AggDailyHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggDailyHVO> update = new BillUpdate<AggDailyHVO>();
                AggDailyHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
