
package nccloud.itf.cy.cy;

import java.util.List;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.stumarkdata.AggStumarkdataHVO;
import nc.vo.cy.stumarkdata.CurriculumInfo;
import nc.vo.cy.stumarkdata.CurriculumMsg;
import nc.vo.cy.stumarkdata.MarkDayVO;
import nc.vo.cy.stumarkdata.StudentInfoVO;
import nc.vo.cy.stumarkdata.StumarkdataQueryPOJO;
import nc.vo.cy.stumarkdata.StumarkdataYearPOJO;
import nc.vo.pub.BusinessException;

public interface IStumarkdatahvoMaintain {

	public void delete(AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills) throws BusinessException;

	public AggStumarkdataHVO[] insert(AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills)
			throws BusinessException;

	public AggStumarkdataHVO[] update(AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills)
			throws BusinessException;

	public AggStumarkdataHVO[] query(IQueryScheme queryScheme) throws BusinessException;

	public AggStumarkdataHVO[] save(AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills)
			throws BusinessException;

	public AggStumarkdataHVO[] unsave(AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills)
			throws BusinessException;

	public AggStumarkdataHVO[] approve(AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills)
			throws BusinessException;

	public AggStumarkdataHVO[] unapprove(AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills)
			throws BusinessException;

	public List<StumarkdataYearPOJO> queryStuYearScoreByIdcard(StumarkdataQueryPOJO queryvo) throws BusinessException;
	
	public List<CurriculumMsg> queryCurriculumByIdcard(CurriculumInfo CurriculumInfo) throws BusinessException;
	
	public List<MarkDayVO> queryMonthTimeTable(CurriculumInfo CurriculumInfo) throws BusinessException;

	public StudentInfoVO queryStudentInfos(String id) throws BusinessException;

}
