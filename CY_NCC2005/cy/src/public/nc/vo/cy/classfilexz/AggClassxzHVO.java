
package nc.vo.cy.classfilexz;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.cy.classfilexz.ClassxzHVO;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.classfilexz.ClassxzHVO")
public class AggClassxzHVO extends AbstractBill {

        @Override
        public IBillMeta getMetaData() {
                IBillMeta billMeta = BillMetaFactory.getInstance().getBillMeta(
                                AggClassxzHVOMeta.class);
                return billMeta;
        }

        @Override
        public ClassxzHVO getParentVO() {
                return (ClassxzHVO) this.getParent();
        }

        @Override
        public String getPrimaryKey() {
                return super.getPrimaryKey();
        }
}