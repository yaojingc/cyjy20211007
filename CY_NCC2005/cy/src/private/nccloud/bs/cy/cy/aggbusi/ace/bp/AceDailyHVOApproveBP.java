
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.daily.AggDailyHVO;

/**
 * 标准单据审核的BP
 */
public class AceDailyHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggDailyHVO[] approve(AggDailyHVO[] clientBills,
                        AggDailyHVO[] originBills) {
                for (AggDailyHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggDailyHVO> update = new BillUpdate<AggDailyHVO>();
                AggDailyHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
