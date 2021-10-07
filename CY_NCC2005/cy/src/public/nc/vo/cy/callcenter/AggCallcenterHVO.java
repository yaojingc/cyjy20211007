
package nc.vo.cy.callcenter;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.cy.callcenter.CallcenterHVO;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.callcenter.CallcenterHVO")
public class AggCallcenterHVO extends AbstractBill {

        @Override
        public IBillMeta getMetaData() {
                IBillMeta billMeta = BillMetaFactory.getInstance().getBillMeta(
                                AggCallcenterHVOMeta.class);
                return billMeta;
        }

        @Override
        public CallcenterHVO getParentVO() {
                return (CallcenterHVO) this.getParent();
        }

        @Override
        public String getPrimaryKey() {
                return super.getPrimaryKey();
        }
}