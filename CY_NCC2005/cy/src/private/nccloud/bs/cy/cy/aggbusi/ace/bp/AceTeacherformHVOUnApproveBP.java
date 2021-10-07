
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import java.util.HashSet;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.vo.cy.teacherform.AggTeacherformHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 标准单据弃审的BP
 */
public class AceTeacherformHVOUnApproveBP {

	public AggTeacherformHVO[] unApprove(AggTeacherformHVO[] clientBills, AggTeacherformHVO[] originBills) {

		Set<String> psndocSet = new HashSet<String>();
		Set<String> teacherformSet = new HashSet<String>();
		for (AggTeacherformHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
			teacherformSet.add(clientBill.getParentVO().getPk_teacherform());
			if (MMValueCheck.isNotEmpty(clientBill.getParentVO().getTeacher())) {
				psndocSet.add(clientBill.getParentVO().getTeacher());
			}
		}
		try {
			billCheck(psndocSet, teacherformSet);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		BillUpdate<AggTeacherformHVO> update = new BillUpdate<AggTeacherformHVO>();
		AggTeacherformHVO[] returnVos = update.update(clientBills, originBills);

		return returnVos;
	}

	/**
	 * 为了避免取消审批前未点击取消分配按钮 
	 * 根据老师讲师申请单匹配人员子表页签数据并删除
	 * 
	 * @param returnVos
	 */
	private void billCheck(Set<String> psndocSet, Set<String> teacherformSet) throws BusinessException {
		if (MMValueCheck.isNotEmpty(psndocSet) && MMValueCheck.isNotEmpty(teacherformSet)) {
			SqlBuilder sql = new SqlBuilder();
			sql.append(" delete from hi_psndoc_glbdef1 where pk_psndoc ", psndocSet.toArray(new String[0]));
			sql.append(" and glbdef8", teacherformSet.toArray(new String[0]));
			new BaseDAO().executeUpdate(sql.toString());
		}
	}
}
