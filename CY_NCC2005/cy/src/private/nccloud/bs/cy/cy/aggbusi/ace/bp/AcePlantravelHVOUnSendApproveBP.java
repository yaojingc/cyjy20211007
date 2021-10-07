
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.plantravel.AggPlantravelHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AcePlantravelHVOUnSendApproveBP {

        public AggPlantravelHVO[] unSend(AggPlantravelHVO[] clientBills,
                        AggPlantravelHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggPlantravelHVO> update = new BillUpdate<AggPlantravelHVO>();
                AggPlantravelHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggPlantravelHVO[] clientBills) {
                for (AggPlantravelHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
