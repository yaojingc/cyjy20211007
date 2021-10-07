
package nc.vo.cy.callcenter;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggCallcenterHVOMeta extends AbstractBillMeta{
        
        public AggCallcenterHVOMeta(){
                this.init();
        }
        
        private void init() {
                this.setParent(nc.vo.cy.callcenter.CallcenterHVO.class);
                this.addChildren(nc.vo.cy.callcenter.CallcenterDetailVO.class);
        }
}