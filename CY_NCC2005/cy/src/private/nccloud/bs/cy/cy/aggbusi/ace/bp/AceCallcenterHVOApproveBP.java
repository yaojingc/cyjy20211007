
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.callcenter.AggCallcenterHVO;

/**
 * 标准单据审核的BP
 */
public class AceCallcenterHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggCallcenterHVO[] approve(AggCallcenterHVO[] clientBills,
                        AggCallcenterHVO[] originBills) {
                for (AggCallcenterHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggCallcenterHVO> update = new BillUpdate<AggCallcenterHVO>();
                AggCallcenterHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
