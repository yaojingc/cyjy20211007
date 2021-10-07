
package nc.vo.cy.plantravel;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggPlantravelHVOMeta extends AbstractBillMeta{
        
        public AggPlantravelHVOMeta(){
                this.init();
        }
        
        private void init() {
                this.setParent(nc.vo.cy.plantravel.PlantravelHVO.class);
                this.addChildren(nc.vo.cy.plantravel.PlantravelkcVO.class);
                this.addChildren(nc.vo.cy.plantravel.PlantravelBVO.class);
        }
}