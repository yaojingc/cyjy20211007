
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.schoolform.AggSchoolformHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceSchoolformHVOUnSendApproveBP {

        public AggSchoolformHVO[] unSend(AggSchoolformHVO[] clientBills,
                        AggSchoolformHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggSchoolformHVO> update = new BillUpdate<AggSchoolformHVO>();
                AggSchoolformHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggSchoolformHVO[] clientBills) {
                for (AggSchoolformHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
