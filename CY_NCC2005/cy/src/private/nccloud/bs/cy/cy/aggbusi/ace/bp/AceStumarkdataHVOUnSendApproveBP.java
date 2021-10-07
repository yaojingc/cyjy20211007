
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.stumarkdata.AggStumarkdataHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceStumarkdataHVOUnSendApproveBP {

        public AggStumarkdataHVO[] unSend(AggStumarkdataHVO[] clientBills,
                        AggStumarkdataHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggStumarkdataHVO> update = new BillUpdate<AggStumarkdataHVO>();
                AggStumarkdataHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggStumarkdataHVO[] clientBills) {
                for (AggStumarkdataHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
