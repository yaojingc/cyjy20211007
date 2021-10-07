
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.guide.AggGuideHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceGuideHVOUnApproveBP {

        public AggGuideHVO[] unApprove(AggGuideHVO[] clientBills,
                        AggGuideHVO[] originBills) {
                for (AggGuideHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggGuideHVO> update = new BillUpdate<AggGuideHVO>();
                AggGuideHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
