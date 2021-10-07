
package nc.vo.cy.salesmanfile;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggSalesmanfileHVOMeta extends AbstractBillMeta{
        
        public AggSalesmanfileHVOMeta(){
                this.init();
        }
        
        private void init() {
                this.setParent(nc.vo.cy.salesmanfile.SalesmanfileHVO.class);
                this.addChildren(nc.vo.cy.salesmanfile.SalesmanfileSignVO.class);
        }
}