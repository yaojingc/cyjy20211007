
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.xuefeiapply.AggXuefeijmHVO;

/**
 * 标准单据审核的BP
 */
public class AceXuefeijmHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggXuefeijmHVO[] approve(AggXuefeijmHVO[] clientBills,
                        AggXuefeijmHVO[] originBills) {
                for (AggXuefeijmHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggXuefeijmHVO> update = new BillUpdate<AggXuefeijmHVO>();
                AggXuefeijmHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
