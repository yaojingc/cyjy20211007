
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.bankcollflow.AggBankcollflowHVO;

/**
 * 标准单据审核的BP
 */
public class AceBankcollflowHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggBankcollflowHVO[] approve(AggBankcollflowHVO[] clientBills,
                        AggBankcollflowHVO[] originBills) {
                for (AggBankcollflowHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggBankcollflowHVO> update = new BillUpdate<AggBankcollflowHVO>();
                AggBankcollflowHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
