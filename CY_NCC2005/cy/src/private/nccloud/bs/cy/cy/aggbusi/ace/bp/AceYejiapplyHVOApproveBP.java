
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.yejiapply.AggYejiapplyHVO;

/**
 * 标准单据审核的BP
 */
public class AceYejiapplyHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggYejiapplyHVO[] approve(AggYejiapplyHVO[] clientBills,
                        AggYejiapplyHVO[] originBills) {
                for (AggYejiapplyHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggYejiapplyHVO> update = new BillUpdate<AggYejiapplyHVO>();
                AggYejiapplyHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
