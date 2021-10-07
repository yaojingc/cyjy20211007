
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.classfilecy.AggClasscyHVO;

/**
 * 标准单据审核的BP
 */
public class AceClasscyHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggClasscyHVO[] approve(AggClasscyHVO[] clientBills,
                        AggClasscyHVO[] originBills) {
                for (AggClasscyHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggClasscyHVO> update = new BillUpdate<AggClasscyHVO>();
                AggClasscyHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
