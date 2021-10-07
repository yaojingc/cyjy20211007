
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.classfilexz.AggClassxzHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceClassxzHVOUnApproveBP {

        public AggClassxzHVO[] unApprove(AggClassxzHVO[] clientBills,
                        AggClassxzHVO[] originBills) {
                for (AggClassxzHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggClassxzHVO> update = new BillUpdate<AggClassxzHVO>();
                AggClassxzHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
