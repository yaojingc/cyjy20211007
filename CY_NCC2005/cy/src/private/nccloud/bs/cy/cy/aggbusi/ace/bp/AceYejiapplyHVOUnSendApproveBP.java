
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.yejiapply.AggYejiapplyHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceYejiapplyHVOUnSendApproveBP {

        public AggYejiapplyHVO[] unSend(AggYejiapplyHVO[] clientBills,
                        AggYejiapplyHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggYejiapplyHVO> update = new BillUpdate<AggYejiapplyHVO>();
                AggYejiapplyHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggYejiapplyHVO[] clientBills) {
                for (AggYejiapplyHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
