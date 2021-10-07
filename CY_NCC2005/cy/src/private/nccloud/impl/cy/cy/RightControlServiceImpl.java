package nccloud.impl.cy.cy;

import java.util.ArrayList;
import java.util.List;

import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.itf.cy.cy.IRightControlService;

public class RightControlServiceImpl implements IRightControlService {

	private IUAPQueryBS querybs = CyCommonUtils.getInstance(IUAPQueryBS.class);

	private SqlBuilder builder = CyCommonUtils.getInstance(SqlBuilder.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryBottomUserId(List<String> parentlist) throws BusinessException {
		List<String> pklist = new ArrayList<String>();
		/*
		 * 根据父用户组编码查询下级用户组编码
		 */
		try {
			builder.reset();
			builder.append("select pk_usergroup  from sm_usergroup where dr = '0' and ");
			builder.append("parentid", parentlist.toArray(new String[0]));
			pklist = (List<String>) querybs.executeQuery(builder.toString(), new ColumnListProcessor());
			if (pklist.size() > 0) {
				this.queryBottomUserId(pklist);
			} else {
				return new ArrayList<String>();
			}
			List<String> psnidlist = queryPsnIdByUserGroup(parentlist);
			if (CyCommonUtils.isNotEmpty(psnidlist)) {
				return psnidlist;
			}
			return new ArrayList<String>();
		} catch (BusinessException e) {
			return new ArrayList<String>();
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> queryPsnIdByUserGroup(List<String> parentlist) {
		/*
		 * 根据用户组编码查询相对应人员的编码
		 */
		try {
			builder.reset();
			builder.append("select pk_psndoc from sm_user where dr = '0' and ");
			builder.append("pk_usergroupforcreate", parentlist.toArray(new String[0]));
			List<String> psnlist;
			psnlist = (List<String>) querybs.executeQuery(builder.toString(), new ColumnListProcessor());
			if (CyCommonUtils.isNotEmpty(psnlist)) {
				return psnlist;
			}
			return null;
		} catch (BusinessException e) {
			return null;
		}
	}

	@Override
	public String queryUserGroupByUserId(String userid) throws BusinessException {
		// TODO Auto-generated method stub
		builder.reset();
		builder.append("select pk_usergroupforcreate  from sm_user where cuserid = '" + userid + "' and dr = '0'");
		Object result = querybs.executeQuery(builder.toString(), new ColumnProcessor());
		if (CyCommonUtils.isNotEmpty(result)) {
			return (String) result;
		}
		return null;
	}

	@Override
	public String queryCodeByGroupId(String groupid) throws BusinessException {
		// TODO Auto-generated method stub
		builder.reset();
		builder.append("select groupcode from sm_usergroup where pk_usergroup = '" + groupid + "' and dr = '0'");
		Object result = querybs.executeQuery(builder.toString(), new ColumnProcessor());
		if (CyCommonUtils.isNotEmpty(result)) {
			return (String) result;
		}
		return null;
	}

	@Override
	public List<String> queryClasscyByPsnid(List<String> psnids) throws BusinessException {
		try {
			builder.reset();
			builder.append("select pk_classcy from cy_classcy where dr = '0' and ");
			builder.append("teacher", psnids.toArray(new String[0]));
			builder.append("or salesman", psnids.toArray(new String[0]));
			List<String> psnlist;
			psnlist = (List<String>) querybs.executeQuery(builder.toString(), new ColumnListProcessor());
			if (CyCommonUtils.isNotEmpty(psnlist)) {
				return psnlist;
			}
			return null;
		} catch (BusinessException e) {
			return null;
		}
	}
}