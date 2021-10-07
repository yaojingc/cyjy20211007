
package nc.vo.cy.salesmanfile;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.cy.salesmanfile.SalesmanfileHVO;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.salesmanfile.SalesmanfileHVO")
public class AggSalesmanfileHVO extends AbstractBill {

        @Override
        public IBillMeta getMetaData() {
                IBillMeta billMeta = BillMetaFactory.getInstance().getBillMeta(
                                AggSalesmanfileHVOMeta.class);
                return billMeta;
        }

        @Override
        public SalesmanfileHVO getParentVO() {
                return (SalesmanfileHVO) this.getParent();
        }

        @Override
        public String getPrimaryKey() {
                return super.getPrimaryKey();
        }
}