
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.stumarkdata.AggStumarkdataHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceStumarkdataHVOUnApproveBP {

        public AggStumarkdataHVO[] unApprove(AggStumarkdataHVO[] clientBills,
                        AggStumarkdataHVO[] originBills) {
                for (AggStumarkdataHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggStumarkdataHVO> update = new BillUpdate<AggStumarkdataHVO>();
                AggStumarkdataHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
