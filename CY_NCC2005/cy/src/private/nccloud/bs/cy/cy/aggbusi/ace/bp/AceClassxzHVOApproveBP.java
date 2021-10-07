
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.classfilexz.AggClassxzHVO;

/**
 * 标准单据审核的BP
 */
public class AceClassxzHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggClassxzHVO[] approve(AggClassxzHVO[] clientBills,
                        AggClassxzHVO[] originBills) {
                for (AggClassxzHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggClassxzHVO> update = new BillUpdate<AggClassxzHVO>();
                AggClassxzHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
