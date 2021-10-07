
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.msgstation.AggMsgstationHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceMsgstationHVOUnApproveBP {

        public AggMsgstationHVO[] unApprove(AggMsgstationHVO[] clientBills,
                        AggMsgstationHVO[] originBills) {
                for (AggMsgstationHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggMsgstationHVO> update = new BillUpdate<AggMsgstationHVO>();
                AggMsgstationHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
