
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.clsschedule.AggClsscheduleHVO;

/**
 * 标准单据审核的BP
 */
public class AceClsscheduleHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggClsscheduleHVO[] approve(AggClsscheduleHVO[] clientBills,
                        AggClsscheduleHVO[] originBills) {
                for (AggClsscheduleHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggClsscheduleHVO> update = new BillUpdate<AggClsscheduleHVO>();
                AggClsscheduleHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
