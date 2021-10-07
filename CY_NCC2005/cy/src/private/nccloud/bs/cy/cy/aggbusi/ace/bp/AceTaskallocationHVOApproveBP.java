
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;

/**
 * 标准单据审核的BP
 */
public class AceTaskallocationHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggTaskallocationHVO[] approve(AggTaskallocationHVO[] clientBills,
                        AggTaskallocationHVO[] originBills) {
                for (AggTaskallocationHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggTaskallocationHVO> update = new BillUpdate<AggTaskallocationHVO>();
                AggTaskallocationHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
