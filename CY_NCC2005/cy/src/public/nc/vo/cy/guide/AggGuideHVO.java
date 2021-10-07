
package nc.vo.cy.guide;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.cy.guide.GuideHVO;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.guide.GuideHVO")
public class AggGuideHVO extends AbstractBill {

        @Override
        public IBillMeta getMetaData() {
                IBillMeta billMeta = BillMetaFactory.getInstance().getBillMeta(
                                AggGuideHVOMeta.class);
                return billMeta;
        }

        @Override
        public GuideHVO getParentVO() {
                return (GuideHVO) this.getParent();
        }

        @Override
        public String getPrimaryKey() {
                return super.getPrimaryKey();
        }
}