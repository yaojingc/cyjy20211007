
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.salesmanfile.AggSalesmanfileHVO;

/**
 * 标准单据审核的BP
 */
public class AceSalesmanfileHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggSalesmanfileHVO[] approve(AggSalesmanfileHVO[] clientBills,
                        AggSalesmanfileHVO[] originBills) {
                for (AggSalesmanfileHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggSalesmanfileHVO> update = new BillUpdate<AggSalesmanfileHVO>();
                AggSalesmanfileHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
