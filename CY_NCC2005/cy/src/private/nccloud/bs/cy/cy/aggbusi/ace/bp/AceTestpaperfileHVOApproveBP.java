
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.testpaperfile.AggTestpaperfileHVO;

/**
 * 标准单据审核的BP
 */
public class AceTestpaperfileHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggTestpaperfileHVO[] approve(AggTestpaperfileHVO[] clientBills,
                        AggTestpaperfileHVO[] originBills) {
                for (AggTestpaperfileHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggTestpaperfileHVO> update = new BillUpdate<AggTestpaperfileHVO>();
                AggTestpaperfileHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
