
package nc.vo.cy.bankcollflow;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.cy.bankcollflow.BankcollflowHVO;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.bankcollflow.BankcollflowHVO")
public class AggBankcollflowHVO extends AbstractBill {

        @Override
        public IBillMeta getMetaData() {
                IBillMeta billMeta = BillMetaFactory.getInstance().getBillMeta(
                                AggBankcollflowHVOMeta.class);
                return billMeta;
        }

        @Override
        public BankcollflowHVO getParentVO() {
                return (BankcollflowHVO) this.getParent();
        }

        @Override
        public String getPrimaryKey() {
                return super.getPrimaryKey();
        }
}