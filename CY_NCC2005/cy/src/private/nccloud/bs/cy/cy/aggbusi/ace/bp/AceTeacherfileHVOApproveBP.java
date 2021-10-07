
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.cy.teacherfile.AggTeacherfileHVO;

/**
 * 标准单据审核的BP
 */
public class AceTeacherfileHVOApproveBP {

        /**
         * 审核动作
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggTeacherfileHVO[] approve(AggTeacherfileHVO[] clientBills,
                        AggTeacherfileHVO[] originBills) {
                for (AggTeacherfileHVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggTeacherfileHVO> update = new BillUpdate<AggTeacherfileHVO>();
                AggTeacherfileHVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
