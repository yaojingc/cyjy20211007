
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.taskfeedback.AggTaskfeedbackHVO;

/**
 * 标准单据审核的BP
 */
public class AceTaskfeedbackHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggTaskfeedbackHVO[] approve(AggTaskfeedbackHVO[] clientBills,
                        AggTaskfeedbackHVO[] originBills) {
                for (AggTaskfeedbackHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggTaskfeedbackHVO> update = new BillUpdate<AggTaskfeedbackHVO>();
                AggTaskfeedbackHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
