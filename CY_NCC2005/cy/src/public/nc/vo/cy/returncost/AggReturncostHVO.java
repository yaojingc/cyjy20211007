
package nc.vo.cy.returncost;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.cy.returncost.ReturncostHVO;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.returncost.ReturncostHVO")
public class AggReturncostHVO extends AbstractBill {

        @Override
        public IBillMeta getMetaData() {
                IBillMeta billMeta = BillMetaFactory.getInstance().getBillMeta(
                                AggReturncostHVOMeta.class);
                return billMeta;
        }

        @Override
        public ReturncostHVO getParentVO() {
                return (ReturncostHVO) this.getParent();
        }

        @Override
        public String getPrimaryKey() {
                return super.getPrimaryKey();
        }
}