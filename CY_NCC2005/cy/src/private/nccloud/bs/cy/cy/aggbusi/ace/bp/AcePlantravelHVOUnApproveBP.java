
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.plantravel.AggPlantravelHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AcePlantravelHVOUnApproveBP {

        public AggPlantravelHVO[] unApprove(AggPlantravelHVO[] clientBills,
                        AggPlantravelHVO[] originBills) {
                for (AggPlantravelHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggPlantravelHVO> update = new BillUpdate<AggPlantravelHVO>();
                AggPlantravelHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
