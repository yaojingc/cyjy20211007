
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.salesmanfile.AggSalesmanfileHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceSalesmanfileHVOUnSendApproveBP {

        public AggSalesmanfileHVO[] unSend(AggSalesmanfileHVO[] clientBills,
                        AggSalesmanfileHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggSalesmanfileHVO> update = new BillUpdate<AggSalesmanfileHVO>();
                AggSalesmanfileHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggSalesmanfileHVO[] clientBills) {
                for (AggSalesmanfileHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
