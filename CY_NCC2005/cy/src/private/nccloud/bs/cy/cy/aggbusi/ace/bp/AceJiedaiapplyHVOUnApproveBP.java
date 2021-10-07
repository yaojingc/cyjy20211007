
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.jiedaiapply.AggJiedaiapplyHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceJiedaiapplyHVOUnApproveBP {

        public AggJiedaiapplyHVO[] unApprove(AggJiedaiapplyHVO[] clientBills,
                        AggJiedaiapplyHVO[] originBills) {
                for (AggJiedaiapplyHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggJiedaiapplyHVO> update = new BillUpdate<AggJiedaiapplyHVO>();
                AggJiedaiapplyHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
