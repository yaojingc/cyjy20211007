package nccloud.impl.cy.cy;

import java.util.Collection;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import nc.api.cy.rest.entity.TableCountQueryVO;
import nc.bs.dao.BaseDAO;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.bd.banktype.BankTypeVO;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.org.DeptVO;
import nc.vo.pf.pub.util.SQLUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;

public class CyCommonSqlUtilImpl implements ICyCommonSqlUtil {

	private IUAPQueryBS querybs = CyCommonUtils.getInstance(IUAPQueryBS.class);

	private SqlBuilder builder = CyCommonUtils.getInstance(SqlBuilder.class);

	/**
	 * 根据单据条件查询整张单据
	 */
	@SuppressWarnings("unchecked")
	public AbstractBill[] queryBillsByCondition(Class<? extends AbstractBill> voClass, String fieldname,
			String[] fieldvalues) throws BusinessException {
		// 构造where in的语句
		String wheresql = SQLUtil.buildSqlForIn(fieldname, fieldvalues);
		/**
		 * p1:AggVO p2:条件 p3：是否懒加载(默认为否)
		 */
		Collection<T> bills = CyCommonUtils.getInstance(IMDPersistenceQueryService.class).queryBillOfVOByCond(voClass,
				wheresql, false);

		return bills.toArray(new AbstractBill[bills.size()]);
	}

	/**
	 * 通过组织主键查询组织编码
	 * 
	 */
	public String queryNewCode(String typecode) throws BusinessException {
		builder.reset();
		builder.append(
				"select code from (select code from bd_customer where dr = '0' and pk_custclass = (select pk_custclass from bd_custclass where code = '"
						+ typecode + "') order by code desc) where rownum = '1'\r\n" + "");
		Object result = querybs.executeQuery(builder.toString(), new ColumnProcessor());
		if (result == null)
			return "";
		return result.toString();
	}

	/**
	 * 查询某个表中符合条件的记录的条目数
	 */
	@Override
	public PagingPOJO queryTablePageInfo(TableCountQueryVO tableCountQueryVO) throws BusinessException {
		builder.reset();
		builder.append(" SELECT ");
		builder.append(" 	count(1) ");
		builder.append(" FROM ");
		builder.append(tableCountQueryVO.getTablename());
		builder.append(" WHERE ");
		builder.append(" dr ", 0);
		if (CyCommonUtils.isNotEmpty(tableCountQueryVO.getWheresql())) {
			builder.append(" AND ");
			builder.append(tableCountQueryVO.getWheresql());
		}
		Object result = querybs.executeQuery(builder.toString(), new ColumnProcessor());
		if (CyCommonUtils.isNotEmpty(result)) {
			PagingPOJO returnPagePojo = new PagingPOJO();
			returnPagePojo.setTotal(Integer.parseInt(result.toString()));
			return returnPagePojo;
		}
		return null;
	}

	/**
	 * 通过自定义档案信息得到主键 defdoc-档案详细信息的编码或名称 defdoclist-所属档案列表的编码或名称
	 * 
	 * @param materialid
	 * @return
	 * @throws BusinessException
	 */
	public String queryDefdocPk(String defdoc, String defdoclist) throws BusinessException {
		builder.reset();
		builder.append(" SELECT pk_defdoc FROM bd_defdoc where ( name='" + defdoc + "' or code='" + defdoc + "' ) ");
		builder.append(" and pk_defdoclist in ");
		builder.append(" (SELECT pk_defdoclist FROM bd_defdoclist where name='" + defdoclist + "' or code='"
				+ defdoclist + "') ");
		Object result = querybs.executeQuery(builder.toString(), new ColumnProcessor());
		if (result == null)
			return "";
		return result.toString();
	}

	public String queryDefdocName(String defdoc, String defdoclist) throws BusinessException {
		builder.reset();
		builder.append(" SELECT name ");
		builder.append(" FROM bd_defdoc ");
		builder.append(" WHERE ");
		builder.append("pk_defdoc", defdoc);
		builder.append(" AND ");
		builder.append(" pk_defdoclist in ");
		builder.append(" (SELECT pk_defdoclist FROM bd_defdoclist where ");
		builder.append("name", defdoclist);
		builder.append(")");

		Object result = querybs.executeQuery(builder.toString(), new ColumnProcessor());
		if (result == null)
			return "";
		return result.toString();
	}

	/**
	 * 根据什么字段查询什么字段都作为参数
	 */
	public String queryDefdocByField(String field1, String field2, String value, String defdoclistname)
			throws BusinessException {
		builder.reset();
		builder.append(" SELECT ");
		builder.append(field1);
		builder.append(" FROM bd_defdoc ");
		builder.append(" WHERE ");
		builder.append(field2, value);
		builder.append(" AND ");
		builder.append(" pk_defdoclist in ");
		builder.append(" (SELECT pk_defdoclist FROM bd_defdoclist where ");
		builder.append("name", defdoclistname);
		builder.append(")");

		Object result = querybs.executeQuery(builder.toString(), new ColumnProcessor());
		if (result == null)
			return "";
		return result.toString();
	}

	/**
	 * 通过客户主键查询客户编码
	 * 
	 */
	public String queryCustCodeById(String id) throws BusinessException {
		builder.reset();
		builder.append(" SELECT code FROM bd_customer  WHERE pk_customer ='" + id + "' and dr = '0'");
		Object result = querybs.executeQuery(builder.toString(), new ColumnProcessor());
		if (result == null)
			return "";
		return result.toString();
	}

	/**
	 * 通过组织主键查询组织编码
	 * 
	 */
	public String queryOrgCodeById(String id) throws BusinessException {
		builder.reset();
		builder.append(" SELECT code FROM org_orgs  WHERE pk_org='" + id + "' and dr = '0'");
		Object result = querybs.executeQuery(builder.toString(), new ColumnProcessor());
		if (result == null)
			return "";
		return result.toString();
	}

	/**
	 * 通过人员主键查询所在的部门
	 */
	@SuppressWarnings("unchecked")
	public DeptVO queryDeptByPsnInfo(String pk_psndoc) throws BusinessException {
		builder.reset();
		builder.append(" SELECT  ");
		builder.append(" dept.* ");
		builder.append(" FROM ");
		builder.append(" bd_psnjob psnjob ");
		builder.append(" LEFT JOIN ");
		builder.append(" bd_psndoc psndoc ");
		builder.append(" ON ");
		builder.append(" psnjob.pk_psndoc = psndoc.pk_psndoc ");
		builder.append(" LEFT JOIN ");
		builder.append(" org_dept dept  ");
		builder.append(" ON ");
		builder.append(" psnjob.pk_dept = dept.pk_dept ");
		builder.append(" WHERE ");
		builder.append(" psndoc.pk_psndoc ", pk_psndoc);

		List<DeptVO> deptList = (List<DeptVO>) CyCommonUtils.getInstance(BaseDAO.class).executeQuery(builder.toString(),
				new BeanListProcessor(DeptVO.class));

		if (CyCommonUtils.isNotEmpty(deptList)) {
			return deptList.get(0);
		}

		return null;
	}

	/**
	 * 条件查询某个VO的详情
	 * 
	 * @param tab对应的表
	 * @param where拼接的查询条件
	 * @param 对应的vo类
	 */
	@Override
	public SuperVO getVO(String tab, String where, Class<? extends ISuperVO> clazz) throws BusinessException {
		SqlBuilder sb = new SqlBuilder();
		sb.append("select * from ");
		sb.append(tab);
		sb.append(" where dr= '0' ");
		sb.append(where);
		SuperVO result = (SuperVO) new BaseDAO().executeQuery(sb.toString(), new BeanProcessor(clazz));
		return result;
	}

	/**
	 * 根据老师人员编码查询主键
	 */
	@Override
	public String queryPsnIdByCode(String code) throws BusinessException {
		builder.reset();
		builder.append(" SELECT pk_psndoc FROM bd_psndoc  WHERE code='" + code + "' and dr = '0'");
		Object result = querybs.executeQuery(builder.toString(), new ColumnProcessor());
		if (result == null) {
			return "";
		}
		return result.toString();
	}

	/**
	 * 根据交易类型主键查询编码
	 */
	@Override
	public String queryCodeByTransId(String pk_transtype) throws BusinessException {
		builder.reset();
		builder.append(" SELECT pk_billtypecode FROM bd_billtype  WHERE pk_billtypeid='" + pk_transtype + "'");
		Object result = querybs.executeQuery(builder.toString(), new ColumnProcessor());
		if (result == null) {
			return "";
		}
		return result.toString();
	}

	@Override
	public Boolean updateContract(String pk_detail, String payNo) throws BusinessException {
		try {
			StringBuffer sql = new StringBuffer("");
			sql.append("update cy_creditcontract_detail set ");
			sql.append("def30 = '" + payNo + "' where pk_detail = '" + pk_detail + "'");
			CyCommonUtils.getInstance(BaseDAO.class).executeUpdate(sql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String queryPsnIdByUserId(String id) throws BusinessException {
		builder.reset();
		builder.append(" SELECT pk_psndoc FROM sm_user  WHERE cuserid='" + id + "' and dr = '0'");
		Object result = querybs.executeQuery(builder.toString(), new ColumnProcessor());
		if (result == null) {
			return "";
		}
		return result.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryRoleCodeByUserId(String userid) throws BusinessException {
		builder.reset();
		builder.append(
				"select role_code from sm_role where pk_role in (select pk_role from sm_user_role where cuserid = '"
						+ userid + "') and dr = '0'");
		List<String> rolelist = (List<String>) querybs.executeQuery(builder.toString(), new ColumnListProcessor());
		if (CyCommonUtils.isEmpty(rolelist)) {
			return null;
		}
		return rolelist;
	}

	public Boolean queryContractByStudent(String id) throws BusinessException {
		builder.reset();
		builder.append("select pk_contract from cy_contract where dr = '0' and def2 = '" + id + "'");
		String pk_contract = (String) querybs.executeQuery(builder.toString(), new ColumnProcessor());
		if (CyCommonUtils.isEmpty(pk_contract)) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean queryRoleGroupCodeByUserId(String id, String rolegroup) throws BusinessException {
		builder.reset();
		builder.append("select role_code from sm_role where role_group_id = '" + rolegroup
				+ "' and pk_role in (select pk_role from sm_user_role where cuserid = '" + id + "') and dr = '0'");
		List<String> rolelist = (List<String>) querybs.executeQuery(builder.toString(), new ColumnListProcessor());
		if (CyCommonUtils.isEmpty(rolelist)) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> querySchoolByPsndoc(String psnid, String wheresql) throws BusinessException {
		builder.reset();
		builder.append("select pk_school from cy_schoolbasics where dr = '0'");
		if (CyCommonUtils.isNotEmpty(wheresql)) {
			builder.append(wheresql);
		}
		List<String> rolelist = (List<String>) querybs.executeQuery(builder.toString(), new ColumnListProcessor());
		if (CyCommonUtils.isEmpty(rolelist)) {
			return null;
		}
		return rolelist;
	}

	@Override
	public String queryIdCardByStudent(String sid) throws BusinessException {
		builder.reset();
		builder.append(" SELECT idcard FROM cy_student  WHERE pk_student='" + sid + "' and dr = '0'");
		Object result = querybs.executeQuery(builder.toString(), new ColumnProcessor());
		if (result == null) {
			return "";
		}
		return result.toString();
	}

	@Override
	public String queryClasscyIdByCycode(String code) throws BusinessException {
		builder.reset();
		builder.append(" SELECT pk_classcy FROM cy_classcy  WHERE bill_no ='" + code + "' and dr = '0'");
		Object result = querybs.executeQuery(builder.toString(), new ColumnProcessor());
		if (result == null) {
			return "";
		}
		return result.toString();
	}

	@Override
	public Boolean updateXfjmStatus(String pk_xfjm) throws BusinessException {
		try {
			StringBuffer sql = new StringBuffer("");
			sql.append("update cy_xuefeijm set ");
			sql.append("def6 = '是' where pk_xfapply = '" + pk_xfjm + "'");
			CyCommonUtils.getInstance(BaseDAO.class).executeUpdate(sql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 查询所有的银行档案
	 */
	public BankTypeVO[] queryAllBankType(String field,String value) throws BusinessException {
		builder.reset();
		builder.append(" SELECT bank.code, bank.name ");
		builder.append(" FROM bd_banktype bank ");
		builder.append(" WHERE ");
		builder.append("dr",0);
		// 查询条件存在则拼接上
		if(CyCommonUtils.isNotEmpty(field) && CyCommonUtils.isNotEmpty(value)) {
			builder.append("AND");
			builder.append(field,value);
		}
		List<BankTypeVO> result = (List<BankTypeVO>) CyCommonUtils.getInstance(BaseDAO.class).executeQuery(builder.toString(),
				new BeanListProcessor(BankTypeVO.class));

		return result.toArray(new BankTypeVO[result.size()]);
	}

}