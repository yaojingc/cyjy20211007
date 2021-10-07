
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.returncost.AggReturncostHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceReturncostHVOUnApproveBP {

        public AggReturncostHVO[] unApprove(AggReturncostHVO[] clientBills,
                        AggReturncostHVO[] originBills) {
                for (AggReturncostHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggReturncostHVO> update = new BillUpdate<AggReturncostHVO>();
                AggReturncostHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
