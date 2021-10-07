
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.returncost.AggReturncostHVO;

/**
 * 标准单据审核的BP
 */
public class AceReturncostHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggReturncostHVO[] approve(AggReturncostHVO[] clientBills,
                        AggReturncostHVO[] originBills) {
                for (AggReturncostHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggReturncostHVO> update = new BillUpdate<AggReturncostHVO>();
                AggReturncostHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
