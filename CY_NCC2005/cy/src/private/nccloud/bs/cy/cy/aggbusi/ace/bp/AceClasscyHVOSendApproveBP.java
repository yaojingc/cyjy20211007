
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceClasscyHVOSendApproveBP {
        /**
         * 送审动作
         *
         * @param vos
         *            单据VO数组
         * @param script
         *            单据动作脚本对象
         * @return 送审后的单据VO数组
         */

        public AggClasscyHVO[] sendApprove(AggClasscyHVO[] clientBills,
                        AggClasscyHVO[] originBills) {
                for (AggClasscyHVO clientFullVO : clientBills) {
                        clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.COMMIT.value());
                        clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 数据持久化
                AggClasscyHVO[] returnVos = new BillUpdate<AggClasscyHVO>().update(
                                clientBills, originBills);
                return returnVos;
        }
}
