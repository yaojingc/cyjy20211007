
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.testpaperfile.AggTestpaperfileHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceTestpaperfileHVOUnApproveBP {

        public AggTestpaperfileHVO[] unApprove(AggTestpaperfileHVO[] clientBills,
                        AggTestpaperfileHVO[] originBills) {
                for (AggTestpaperfileHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggTestpaperfileHVO> update = new BillUpdate<AggTestpaperfileHVO>();
                AggTestpaperfileHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
