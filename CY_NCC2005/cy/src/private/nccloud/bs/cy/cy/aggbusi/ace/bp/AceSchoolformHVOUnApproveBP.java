
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.schoolform.AggSchoolformHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceSchoolformHVOUnApproveBP {

        public AggSchoolformHVO[] unApprove(AggSchoolformHVO[] clientBills,
                        AggSchoolformHVO[] originBills) {
                for (AggSchoolformHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggSchoolformHVO> update = new BillUpdate<AggSchoolformHVO>();
                AggSchoolformHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
