
package nc.vo.cy.teacherfile;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggTeacherfileHVOMeta extends AbstractBillMeta{
        
        public AggTeacherfileHVOMeta(){
                this.init();
        }
        
        private void init() {
                this.setParent(nc.vo.cy.teacherfile.TeacherfileHVO.class);
                this.addChildren(nc.vo.cy.teacherfile.TeacherfileDetailVO.class);
        }
}