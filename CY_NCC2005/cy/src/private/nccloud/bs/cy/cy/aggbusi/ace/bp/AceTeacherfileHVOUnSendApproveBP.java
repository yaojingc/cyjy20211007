
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.teacherfile.AggTeacherfileHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceTeacherfileHVOUnSendApproveBP {

        public AggTeacherfileHVO[] unSend(AggTeacherfileHVO[] clientBills,
                        AggTeacherfileHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggTeacherfileHVO> update = new BillUpdate<AggTeacherfileHVO>();
                AggTeacherfileHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggTeacherfileHVO[] clientBills) {
                for (AggTeacherfileHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
