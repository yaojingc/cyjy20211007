
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.bankcollflow.AggBankcollflowHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceBankcollflowHVOUnApproveBP {

        public AggBankcollflowHVO[] unApprove(AggBankcollflowHVO[] clientBills,
                        AggBankcollflowHVO[] originBills) {
                for (AggBankcollflowHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggBankcollflowHVO> update = new BillUpdate<AggBankcollflowHVO>();
                AggBankcollflowHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
