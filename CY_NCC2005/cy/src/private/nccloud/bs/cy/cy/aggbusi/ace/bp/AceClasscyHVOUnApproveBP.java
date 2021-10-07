
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceClasscyHVOUnApproveBP {

        public AggClasscyHVO[] unApprove(AggClasscyHVO[] clientBills,
                        AggClasscyHVO[] originBills) {
                for (AggClasscyHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggClasscyHVO> update = new BillUpdate<AggClasscyHVO>();
                AggClasscyHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
