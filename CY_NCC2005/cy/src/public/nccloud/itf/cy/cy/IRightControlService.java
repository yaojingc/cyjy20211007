package nccloud.itf.cy.cy;

import java.util.List;

import nc.vo.pub.BusinessException;

public interface IRightControlService {

	/**
	 * 通过用户主键查询所属用户组pk
	 * 
	 * @param pk_parent
	 * @return
	 * @throws BusinessException
	 */
	public String queryUserGroupByUserId(String userid) throws BusinessException;

	/**
	 * 通过用户组主键查询最下级的所有用户主键
	 * 
	 * @param pk_parent
	 * @return
	 * @throws BusinessException
	 */
	public List<String> queryBottomUserId(List<String> parentlist) throws BusinessException;

	/**
	 * 通过人员主键查询关联的班级主键
	 * 
	 * @param pk_parent
	 * @return
	 * @throws BusinessException
	 */
	public List<String> queryClasscyByPsnid(List<String> psnids) throws BusinessException;

	/**
	 * 通过用户组主键查询编码
	 * 
	 * @param pk_parent
	 * @return
	 * @throws BusinessException
	 */
	public String queryCodeByGroupId(String groupid) throws BusinessException;

}