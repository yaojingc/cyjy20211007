
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.vo.cy.stumarkdata.AggStumarkdataHVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceStumarkdataSendApproveBP {
        /**
         * 送审动作
         *
         * @param vos
         *            单据VO数组
         * @param script
         *            单据动作脚本对象
         * @return 送审后的单据VO数组
         */

        public AggStumarkdataHVO[] sendApprove(AggStumarkdataHVO[] clientBills,
                        AggStumarkdataHVO[] originBills) {
                for (AggStumarkdataHVO clientFullVO : clientBills) {
                        clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.COMMIT.value());
                        clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
                }
                // 数据持久化
                AggStumarkdataHVO[] returnVos = new BillUpdate<AggStumarkdataHVO>().update(
                                clientBills, originBills);
                return returnVos;
        }
}
