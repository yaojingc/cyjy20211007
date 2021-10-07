
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.xuefeiapply.AggXuefeijmHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceXuefeijmHVOUnApproveBP {

        public AggXuefeijmHVO[] unApprove(AggXuefeijmHVO[] clientBills,
                        AggXuefeijmHVO[] originBills) {
                for (AggXuefeijmHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggXuefeijmHVO> update = new BillUpdate<AggXuefeijmHVO>();
                AggXuefeijmHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
