
package nc.vo.cy.bankcollflow;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggBankcollflowHVOMeta extends AbstractBillMeta{
        
        public AggBankcollflowHVOMeta(){
                this.init();
        }
        
        private void init() {
                this.setParent(nc.vo.cy.bankcollflow.BankcollflowHVO.class);
                this.addChildren(nc.vo.cy.bankcollflow.BankcollflowDetail.class);
        }
}