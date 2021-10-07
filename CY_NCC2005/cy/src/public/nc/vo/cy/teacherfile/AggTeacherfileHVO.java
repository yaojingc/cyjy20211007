
package nc.vo.cy.teacherfile;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.cy.teacherfile.TeacherfileHVO;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.cy.teacherfile.TeacherfileHVO")
public class AggTeacherfileHVO extends AbstractBill {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Override
	public IBillMeta getMetaData() {
		IBillMeta billMeta = BillMetaFactory.getInstance().getBillMeta(AggTeacherfileHVOMeta.class);
		return billMeta;
	}

	@Override
	public TeacherfileHVO getParentVO() {
		return (TeacherfileHVO) this.getParent();
	}

	@Override
	public String getPrimaryKey() {
		return super.getPrimaryKey();
	}
}