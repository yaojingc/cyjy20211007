
package nc.vo.cy.classfilexz;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggClassxzHVOMeta extends AbstractBillMeta{
        
        public AggClassxzHVOMeta(){
                this.init();
        }
        
        private void init() {
                this.setParent(nc.vo.cy.classfilexz.ClassxzHVO.class);
                this.addChildren(nc.vo.cy.classfilexz.ClassxzDetailVO.class);
        }
}