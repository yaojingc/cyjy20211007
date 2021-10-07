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
import nc.vo.cy.classfilecy.pojo.ClassCyQueryPOJO;
import nc.vo.cy.pojo.PsnQueryPOJO;
import nc.vo.cy.pojo.PsnReturnPOJO;
import nc.vo.cy.pojo.RefPOJO;
import nc.vo.cy.pojo.SchoolPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolListDetailPOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * 
 * @author RONGLEIA
 * 
 */
public class CyMkAppBaseDao {

	/**
	 * Add By rongleia 查询当前表数据共有多少条
	 * 
	 * @param rolecode
	 * @return
	 */
	public static String queryTotal(PsnQueryPOJO queryvo) {
		try {
			String name = queryvo.getName();// r人员姓名
			String code = queryvo.getCode();// 人员编码
			String type = queryvo.getType();// 人员分类
			IUAPQueryBS queryBS = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			StringBuffer sql = new StringBuffer("");
			if (CyCommonUtils.isNotEmpty(name) || CyCommonUtils.isNotEmpty(code) || CyCommonUtils.isNotEmpty(type)) {
				sql.append("select count(1)  from bd_psndoc where dr = '0' ");
				if (CyCommonUtils.isNotEmpty(name)) {
					sql.append(" and name like '%" + name + "%' ");
				}
				if (CyCommonUtils.isNotEmpty(code)) {
					sql.append(" and code like '%" + name + "%' ");
				}
//				if (CyCommonUtils.isNotEmpty(type)) {
//					sql.append(" and clasname like '%" + clasname + "%' ");
//				}
			} else {
				sql.append("select count(1)  from bd_psndoc where dr = '0'");
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
	 * Add By rongleia 根据查询条件查询学校档案列表态数据
	 * 
	 * @param rolecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<PsnReturnPOJO> queryPsndocList(PsnQueryPOJO queryvo) {
		// TODO 自动生成的方法存根
		StringBuffer sql = new StringBuffer("");
		String name = queryvo.getName();// r人员姓名
		String code = queryvo.getCode();// 人员编码
		String type = queryvo.getType();// 人员分类
		int index = queryvo.getIndex().intValue();// 第几页
		int size = queryvo.getSize().intValue();// 每页数据量
		UFDouble start = (new UFDouble(index).sub(new UFDouble(1))).multiply(new UFDouble(size)).add(new UFDouble(1));
		UFDouble end = new UFDouble(index).multiply(new UFDouble(size));
		if (CyCommonUtils.isNotEmpty(name) || CyCommonUtils.isNotEmpty(code) || CyCommonUtils.isNotEmpty(type)) {
			sql.append(
					"select name,code,pk_psndoc,ufdept from ( select rownum as rn, name,code,pk_psndoc,(select pk_dept from bd_psnjob b where b.pk_psndoc = c.pk_psndoc and ismainjob ='Y') ufdept from bd_psndoc c where dr = 0 ");
			if (CyCommonUtils.isNotEmpty(name)) {
				sql.append(" and name like '%" + name + "%' ");
			}
			if (CyCommonUtils.isNotEmpty(code)) {
				sql.append(" and code like '%" + code + "%' ");
			}
//			if (CyCommonUtils.isNotEmpty(sname)) {
//				sql.append(" and def1 in (select pk_school from cy_schoolBasics where sname like '%" + sname + "%')");
//			}
			sql.append(" and rownum <= " + end + " order by ts desc) where 1 =1 and rn >= " + start);
		} else {
			sql.append(
					"select pk_classcy,bill_no,clasname,openclas,dbilldate,def1 from ( select rownum as rn, pk_classcy,bill_no,clasname,openclas,substr(dbilldate,1,10) dbilldate,def1 from cy_classcy where dr = 0 and rownum <= "
							+ end + " order by ts desc) where 1 =1 and rn >= " + start);
		}
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		List<PsnReturnPOJO> listvos = new ArrayList<PsnReturnPOJO>();
		try {
			listvos = (List<PsnReturnPOJO>) dao.executeQuery(sql.toString(),
					new BeanListProcessor(PsnReturnPOJO.class));
			if (CyCommonUtils.isNotEmpty(listvos)) {
				return listvos;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static RefPOJO queryDeptByPsn(String ufdept) {
		StringBuffer sql = new StringBuffer("");
		sql.append("select name label,code,pk_dept value from org_dept where pk_dept = '" + ufdept + "' and dr = '0'");
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		RefPOJO rpojo = new RefPOJO();
		try {
			rpojo = (RefPOJO) dao.executeQuery(sql.toString(), new BeanProcessor(RefPOJO.class));
			if (CyCommonUtils.isNotEmpty(rpojo)) {
				return rpojo;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
