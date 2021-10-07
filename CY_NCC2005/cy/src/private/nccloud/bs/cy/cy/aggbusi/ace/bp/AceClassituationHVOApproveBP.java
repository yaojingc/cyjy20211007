
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.classituation.AggClassituationHVO;

/**
 * 标准单据审核的BP
 */
public class AceClassituationHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggClassituationHVO[] approve(AggClassituationHVO[] clientBills,
                        AggClassituationHVO[] originBills) {
                for (AggClassituationHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggClassituationHVO> update = new BillUpdate<AggClassituationHVO>();
                AggClassituationHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
