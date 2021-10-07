
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.salesmanfile.AggSalesmanfileHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceSalesmanfileHVOUnApproveBP {

        public AggSalesmanfileHVO[] unApprove(AggSalesmanfileHVO[] clientBills,
                        AggSalesmanfileHVO[] originBills) {
                for (AggSalesmanfileHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggSalesmanfileHVO> update = new BillUpdate<AggSalesmanfileHVO>();
                AggSalesmanfileHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
