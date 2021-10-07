
package nccloud.bs.cy.cy.aggbusi.ace.bp;
import nc.vo.cy.teacherform.AggTeacherformHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceTeacherformHVOUnSendApproveBP {

        public AggTeacherformHVO[] unSend(AggTeacherformHVO[] clientBills,
                        AggTeacherformHVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggTeacherformHVO> update = new BillUpdate<AggTeacherformHVO>();
                AggTeacherformHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggTeacherformHVO[] clientBills) {
                for (AggTeacherformHVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
