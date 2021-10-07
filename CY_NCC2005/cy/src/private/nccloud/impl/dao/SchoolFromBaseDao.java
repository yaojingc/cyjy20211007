package nccloud.impl.dao;

import java.util.ArrayList;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.pojo.RefPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolBasicsPOJO;
import nc.vo.cy.schoolform.pojo.SchoolFormCardReturnPOJO;
import nc.vo.cy.schoolform.pojo.SchoolFormDetailReturnPOJO;
import nc.vo.cy.schoolform.pojo.SchoolFormQueryPOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * 
 * @author RONGLEIA
 * 
 */
public class SchoolFromBaseDao {

	/**
	 * Add By rongleia 查询当前表数据共有多少条
	 * 
	 * @param rolecode
	 * @return
	 */
	public static String queryTotal(String sname) {
		try {
			IUAPQueryBS queryBS = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			StringBuffer sql = new StringBuffer("");
			if (CyCommonUtils.isNotEmpty(sname)) {
				sql.append("select count(1)  from cy_schoolform where dr = '0' and school like '%" + sname + "%'");
			} else {
				sql.append("select count(1)  from cy_schoolform where dr = '0'");
			}
			Object result;
			result = queryBS.executeQuery(sql.toString(), new ColumnProcessor());
			if (result == null) {
				return "0";
			}
			return result.toString();
		} catch (BusinessException e) {
			e.printStackTrace();
			return "0";
		}
	}

	/**
	 * Add By rongleia 根据申请人的主键查询详细信息
	 * 
	 * @param rolecode
	 * @return
	 */
	public static RefPOJO queryPetitionerByPk(String pk_proposer) {
		// TODO 自动生成的方法存根
		StringBuffer sql = new StringBuffer("");
		sql.append("select name label,pk_psndoc value,code from bd_psndoc where pk_psndoc = '" + pk_proposer
				+ "' and dr = '0'");
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		try {
			RefPOJO rvo = (RefPOJO) dao.executeQuery(sql.toString(), new BeanProcessor(RefPOJO.class));
			if (CyCommonUtils.isNotEmpty(rvo)) {
				return rvo;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Add By rongleia 根据查询条件查询学校档案列表态数据
	 * 
	 * @param rolecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SchoolFormDetailReturnPOJO> querySchoolList(SchoolFormQueryPOJO querypojo) {
		// TODO 自动生成的方法存根
		StringBuffer sql = new StringBuffer("");
		int index = querypojo.getIndex().intValue();// 第几页
		int size = querypojo.getSize().intValue();// 每页数据量
		UFDouble start = (new UFDouble(index).sub(new UFDouble(1))).multiply(new UFDouble(size)).add(new UFDouble(1));
		UFDouble end = new UFDouble(index).multiply(new UFDouble(size));
		String sname = querypojo.getSchool();
		if (CyCommonUtils.isNotEmpty(sname)) {
			sql.append(
					"select pk_schoolf,school,address,ufregion,proposer from ( select rownum as rn, pk_schoolf,school,address,region ufregion,proposer from cy_schoolform where dr = 0 and school like '%"
							+ sname + "%' and rownum <= " + end + " order by ts desc) where 1 =1 and rn >= " + start);
		} else {
			sql.append(
					"select pk_schoolf,school,address,ufregion,proposer from ( select rownum as rn, pk_schoolf,school,address,region ufregion,proposer from cy_schoolform where dr = 0 and rownum <= "
							+ end + " order by ts desc) where 1 =1 and rn >= " + start);
		}
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		List<SchoolFormDetailReturnPOJO> listvos = new ArrayList<SchoolFormDetailReturnPOJO>();
		try {
			listvos = (List<SchoolFormDetailReturnPOJO>) dao.executeQuery(sql.toString(),
					new BeanListProcessor(SchoolFormDetailReturnPOJO.class));
			if (CyCommonUtils.isNotEmpty(listvos)) {
				return listvos;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static SchoolFormCardReturnPOJO querySchoolCard(String pk_schoolf) {
		// TODO 自动生成的方法存根
		StringBuffer sql = new StringBuffer("");
		sql.append(
				"select pk_schoolf,school,address,region ufregion,proposer,vnote from cy_schoolform where pk_schoolf = '"
						+ pk_schoolf + "' and dr = '0'");
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		SchoolFormCardReturnPOJO svo = new SchoolFormCardReturnPOJO();
		try {
			svo = (SchoolFormCardReturnPOJO) dao.executeQuery(sql.toString(),
					new BeanProcessor(SchoolFormCardReturnPOJO.class));
			if (CyCommonUtils.isNotEmpty(svo)) {
				return svo;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
