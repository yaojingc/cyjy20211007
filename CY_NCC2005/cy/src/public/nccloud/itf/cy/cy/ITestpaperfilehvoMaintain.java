
package nccloud.itf.cy.cy;

import java.util.Map;
import java.util.Set;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.testpaperfile.AggTestpaperfileHVO;
import nc.vo.pub.BusinessException;

public interface ITestpaperfilehvoMaintain {

	public void delete(AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills) throws BusinessException;

	public AggTestpaperfileHVO[] insert(AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills)
			throws BusinessException;

	public AggTestpaperfileHVO[] update(AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills)
			throws BusinessException;

	public AggTestpaperfileHVO[] query(IQueryScheme queryScheme) throws BusinessException;

	public AggTestpaperfileHVO[] save(AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills)
			throws BusinessException;

	public AggTestpaperfileHVO[] unsave(AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills)
			throws BusinessException;

	public AggTestpaperfileHVO[] approve(AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills)
			throws BusinessException;

	public AggTestpaperfileHVO[] unapprove(AggTestpaperfileHVO[] clientFullVOs, AggTestpaperfileHVO[] originBills)
			throws BusinessException;

	public Map<String, String> testPaperDataQuery(Set<String> setpks);
}
