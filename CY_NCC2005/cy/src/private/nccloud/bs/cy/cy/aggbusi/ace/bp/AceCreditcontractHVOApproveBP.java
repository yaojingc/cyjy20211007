
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;

/**
 * 标准单据审核的BP
 */
public class AceCreditcontractHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggCreditcontractHVO[] approve(AggCreditcontractHVO[] clientBills,
                        AggCreditcontractHVO[] originBills) {
                for (AggCreditcontractHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggCreditcontractHVO> update = new BillUpdate<AggCreditcontractHVO>();
                AggCreditcontractHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
