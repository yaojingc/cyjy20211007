
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.msgstation.AggMsgstationHVO;

/**
 * 标准单据审核的BP
 */
public class AceMsgstationHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggMsgstationHVO[] approve(AggMsgstationHVO[] clientBills,
                        AggMsgstationHVO[] originBills) {
                for (AggMsgstationHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggMsgstationHVO> update = new BillUpdate<AggMsgstationHVO>();
                AggMsgstationHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
