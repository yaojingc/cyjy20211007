
package nc.vo.cy.guide;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggGuideHVOMeta extends AbstractBillMeta{
        
        public AggGuideHVOMeta(){
                this.init();
        }
        
        private void init() {
                this.setParent(nc.vo.cy.guide.GuideHVO.class);
                this.addChildren(nc.vo.cy.guide.GuideBVO.class);
        }
}