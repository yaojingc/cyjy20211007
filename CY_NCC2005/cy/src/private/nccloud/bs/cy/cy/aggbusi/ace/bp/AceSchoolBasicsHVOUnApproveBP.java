
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceSchoolBasicsHVOUnApproveBP {

        public AggSchoolBasicsHVO[] unApprove(AggSchoolBasicsHVO[] clientBills,
                        AggSchoolBasicsHVO[] originBills) {
                for (AggSchoolBasicsHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggSchoolBasicsHVO> update = new BillUpdate<AggSchoolBasicsHVO>();
                AggSchoolBasicsHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
