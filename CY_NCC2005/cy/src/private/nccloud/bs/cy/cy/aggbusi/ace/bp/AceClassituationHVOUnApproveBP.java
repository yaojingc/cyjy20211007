
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.classituation.AggClassituationHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceClassituationHVOUnApproveBP {

        public AggClassituationHVO[] unApprove(AggClassituationHVO[] clientBills,
                        AggClassituationHVO[] originBills) {
                for (AggClassituationHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggClassituationHVO> update = new BillUpdate<AggClassituationHVO>();
                AggClassituationHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
