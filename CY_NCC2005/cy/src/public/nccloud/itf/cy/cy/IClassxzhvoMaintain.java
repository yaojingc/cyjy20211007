
package nccloud.itf.cy.cy;

import java.util.Map;
import java.util.Set;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.classfilexz.AggClassxzHVO;
import nc.vo.pub.BusinessException;

public interface IClassxzhvoMaintain {

	public void delete(AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills) throws BusinessException;

	public AggClassxzHVO[] insert(AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills) throws BusinessException;

	public AggClassxzHVO[] update(AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills) throws BusinessException;

	public AggClassxzHVO[] query(IQueryScheme queryScheme) throws BusinessException;

	public AggClassxzHVO[] save(AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills) throws BusinessException;

	public AggClassxzHVO[] unsave(AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills) throws BusinessException;

	public AggClassxzHVO[] approve(AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills) throws BusinessException;

	public AggClassxzHVO[] unapprove(AggClassxzHVO[] clientFullVOs, AggClassxzHVO[] originBills)
			throws BusinessException;

	public Map<String, String> classxzDataQuery(Set<String> setclassxz);
}
