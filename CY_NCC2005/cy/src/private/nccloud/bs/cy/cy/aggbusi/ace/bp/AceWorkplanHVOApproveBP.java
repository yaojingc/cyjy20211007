
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.workplan.AggWorkplanHVO;

/**
 * 标准单据审核的BP
 */
public class AceWorkplanHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggWorkplanHVO[] approve(AggWorkplanHVO[] clientBills,
                        AggWorkplanHVO[] originBills) {
                for (AggWorkplanHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggWorkplanHVO> update = new BillUpdate<AggWorkplanHVO>();
                AggWorkplanHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
