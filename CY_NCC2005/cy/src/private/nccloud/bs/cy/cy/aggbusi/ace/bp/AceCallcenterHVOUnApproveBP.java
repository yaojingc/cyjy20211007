
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.callcenter.AggCallcenterHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceCallcenterHVOUnApproveBP {

        public AggCallcenterHVO[] unApprove(AggCallcenterHVO[] clientBills,
                        AggCallcenterHVO[] originBills) {
                for (AggCallcenterHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggCallcenterHVO> update = new BillUpdate<AggCallcenterHVO>();
                AggCallcenterHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
