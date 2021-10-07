
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.testpaperfile.AggTestpaperfileHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceTestpaperfileHVOUnSendApproveBP {

        public AggTestpaperfileHVO[] unSend(AggTestpaperfileHVO[] clientBills,
                        AggTestpaperfileHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggTestpaperfileHVO> update = new BillUpdate<AggTestpaperfileHVO>();
                AggTestpaperfileHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggTestpaperfileHVO[] clientBills) {
                for (AggTestpaperfileHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
