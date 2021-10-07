
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.stumarkdata.AggStumarkdataHVO;

/**
 * 标准单据审核的BP
 */
public class AceStumarkdataApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggStumarkdataHVO[] approve(AggStumarkdataHVO[] clientBills,
                        AggStumarkdataHVO[] originBills) {
                for (AggStumarkdataHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggStumarkdataHVO> update = new BillUpdate<AggStumarkdataHVO>();
                AggStumarkdataHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
