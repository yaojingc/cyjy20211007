
package nc.vo.cy.plantravel;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.cy.plantravel.PlantravelHVO;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.plantravel.PlantravelHVO")
public class AggPlantravelHVO extends AbstractBill {

        @Override
        public IBillMeta getMetaData() {
                IBillMeta billMeta = BillMetaFactory.getInstance().getBillMeta(
                                AggPlantravelHVOMeta.class);
                return billMeta;
        }

        @Override
        public PlantravelHVO getParentVO() {
                return (PlantravelHVO) this.getParent();
        }

        @Override
        public String getPrimaryKey() {
                return super.getPrimaryKey();
        }
}