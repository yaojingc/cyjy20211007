
package nc.vo.cy.returncost;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggReturncostHVOMeta extends AbstractBillMeta{
        
        public AggReturncostHVOMeta(){
                this.init();
        }
        
        private void init() {
                this.setParent(nc.vo.cy.returncost.ReturncostHVO.class);
                this.addChildren(nc.vo.cy.returncost.ReturncostBVO.class);
        }
}