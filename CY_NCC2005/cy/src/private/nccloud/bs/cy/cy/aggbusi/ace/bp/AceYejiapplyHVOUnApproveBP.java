
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.yejiapply.AggYejiapplyHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceYejiapplyHVOUnApproveBP {

        public AggYejiapplyHVO[] unApprove(AggYejiapplyHVO[] clientBills,
                        AggYejiapplyHVO[] originBills) {
                for (AggYejiapplyHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggYejiapplyHVO> update = new BillUpdate<AggYejiapplyHVO>();
                AggYejiapplyHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
