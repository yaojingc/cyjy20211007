package nccloud.itf.cy.cy;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.utils.commonplugin.returnparam.TwoTuple;
import nc.vo.cy.classfilexz.ClassxzHVO;
import nc.vo.cy.clsschedule.AggClsscheduleHVO;
import nc.vo.cy.clsschedule.ClsscheduleBVO;
import nc.vo.cy.studentfile.StudentHVO;
import nc.vo.pub.BusinessException;

public interface IClsschedulehvoMaintain {

	public void delete(AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills) throws BusinessException;

	public AggClsscheduleHVO[] insert(AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills)
			throws BusinessException;

	public AggClsscheduleHVO[] update(AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills)
			throws BusinessException;

	public AggClsscheduleHVO[] query(IQueryScheme queryScheme) throws BusinessException;

	public AggClsscheduleHVO[] save(AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills)
			throws BusinessException;

	public AggClsscheduleHVO[] unsave(AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills)
			throws BusinessException;

	public AggClsscheduleHVO[] approve(AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills)
			throws BusinessException;

	public AggClsscheduleHVO[] unapprove(AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills)
			throws BusinessException;

	public TwoTuple<ClsscheduleBVO, Map<ClassxzHVO, List<StudentHVO>>> queryStuByXzcls(String pk_clsschedule,
			String def2) throws BusinessException;

	public Map<ClassxzHVO, List<StudentHVO>> constractStuTreeByPKStu(String[] pk_stu) throws BusinessException;

	public void updateBodyLineByPK(String pk_body, String stustr) throws BusinessException;

	public Boolean creatSituation(AggClsscheduleHVO aggvo) throws BusinessException;

	public List<AggClsscheduleHVO> buildVOForInsert(Sheet sheet) throws BusinessException;
}
