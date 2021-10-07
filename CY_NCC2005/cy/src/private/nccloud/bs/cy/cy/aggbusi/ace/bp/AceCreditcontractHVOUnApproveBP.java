
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceCreditcontractHVOUnApproveBP {

        public AggCreditcontractHVO[] unApprove(AggCreditcontractHVO[] clientBills,
                        AggCreditcontractHVO[] originBills) {
                for (AggCreditcontractHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggCreditcontractHVO> update = new BillUpdate<AggCreditcontractHVO>();
                AggCreditcontractHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
