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
import nc.vo.cy.classfilecy.ClasscyHVO;
import nc.vo.cy.classfilecy.pojo.ClassCyCardReturnPOJO;
import nc.vo.cy.classfilecy.pojo.ClassCyQueryPOJO;
import nc.vo.cy.pojo.RefPOJO;
import nc.vo.cy.pojo.SchoolPOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * 
 * @author RONGLEIA
 * 
 */
public class ClassCyBaseDao {

	/**
	 * Add By rongleia 查询当前表数据共有多少条
	 * 
	 * @param rolecode
	 * @return
	 */
	public static String queryTotal(ClassCyQueryPOJO querypojo) {
		try {
			String sname = querypojo.getSname();
			String clasname = querypojo.getClasname();
			String bill_no = querypojo.getBill_no();
			IUAPQueryBS queryBS = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			StringBuffer sql = new StringBuffer("");
			if (CyCommonUtils.isNotEmpty(sname) || CyCommonUtils.isNotEmpty(clasname)
					|| CyCommonUtils.isNotEmpty(bill_no)) {
				sql.append("select count(1)  from cy_classcy where dr = '0' ");
				if (CyCommonUtils.isNotEmpty(sname)) {
					sql.append(
							" and def1 in (select pk_school from cy_schoolBasics where sname like '%" + sname + "%')");
				}
				if (CyCommonUtils.isNotEmpty(bill_no)) {
					sql.append(" and bill_no like '%" + bill_no + "%' ");
				}
				if (CyCommonUtils.isNotEmpty(clasname)) {
					sql.append(" and clasname like '%" + clasname + "%' ");
				}
			} else {
				sql.append("select count(1)  from cy_classcy where dr = '0'");
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
	public static List<ClasscyHVO> queryClassCyList(ClassCyQueryPOJO querypojo) {
		// TODO 自动生成的方法存根
		StringBuffer sql = new StringBuffer("");
		String clasname = querypojo.getClasname();// 班级名称
		String bill_no = querypojo.getBill_no();
		int index = querypojo.getIndex().intValue();// 第几页
		int size = querypojo.getSize().intValue();// 每页数据量
		UFDouble start = (new UFDouble(index).sub(new UFDouble(1))).multiply(new UFDouble(size)).add(new UFDouble(1));
		UFDouble end = new UFDouble(index).multiply(new UFDouble(size));
		String sname = querypojo.getSname();
		if (CyCommonUtils.isNotEmpty(sname) || CyCommonUtils.isNotEmpty(clasname)
				|| CyCommonUtils.isNotEmpty(bill_no)) {
			sql.append(
					"select pk_classcy,bill_no,clasname,openclas,dbilldate,def1 from ( select rownum as rn, pk_classcy,bill_no,clasname,openclas,dbilldate,def1 from cy_classcy where dr = 0 ");
			if (CyCommonUtils.isNotEmpty(clasname)) {
				sql.append(" and clasname like '%" + clasname + "%' ");
			}
			if (CyCommonUtils.isNotEmpty(bill_no)) {
				sql.append(" and bill_no like '%" + bill_no + "%' ");
			}
			if (CyCommonUtils.isNotEmpty(sname)) {
				sql.append(" and def1 in (select pk_school from cy_schoolBasics where sname like '%" + sname + "%')");
			}
			sql.append(" and rownum <= " + end + " order by ts desc) where 1 =1 and rn >= " + start);
		} else {
			sql.append(
					"select pk_classcy,bill_no,clasname,openclas,dbilldate,def1 from ( select rownum as rn, pk_classcy,bill_no,clasname,openclas,dbilldate,def1 from cy_classcy where dr = 0 and rownum <= "
							+ end + " order by ts desc) where 1 =1 and rn >= " + start);
		}
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		List<ClasscyHVO> listvos = new ArrayList<ClasscyHVO>();
		try {
			listvos = (List<ClasscyHVO>) dao.executeQuery(sql.toString(),
					new BeanListProcessor(ClasscyHVO.class));
			if (CyCommonUtils.isNotEmpty(listvos)) {
				return listvos;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ClassCyCardReturnPOJO queryClassCyCard(String pk_classcy) {
		// TODO 自动生成的方法存根
		StringBuffer sql = new StringBuffer("");
		sql.append("select pk_classcy,\r\n" + " substr(dbilldate, 1, 10) dbilldate,def1,\r\n" + " bill_no,\r\n"
				+ "       teacher ufteacher,\r\n"
				+ " substr(startdate, 1, 10) startdate,substr(enddate, 1, 10) enddate,gradenum,\r\n" + " gradenum,\r\n"
				+ " gangboss ufgangboss,\r\n" + " supervisor ufsupervisor,\r\n" + " marketass ufmarketass,\r\n"
				+ " salesman ufsalesman,\r\n" + " dutypsn ufdutypsn,\r\n" + " clasname,\r\n" + " openclas,\r\n"
				+ " paystand,\r\n" + " paperagre ufpaperagre,\r\n" + " isaverage ufisaverage,\r\n" + " artaverage,\r\n"
				+ " culaverage,\r\n" + " engaverage,\r\n" + " budget,\r\n" + " vnote\r\n" + " from cy_classcy\r\n"
				+ " where dr = '0' and pk_classcy = '" + pk_classcy + "'");
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		ClassCyCardReturnPOJO svo = new ClassCyCardReturnPOJO();
		try {
			svo = (ClassCyCardReturnPOJO) dao.executeQuery(sql.toString(),
					new BeanProcessor(ClassCyCardReturnPOJO.class));
			if (CyCommonUtils.isNotEmpty(svo)) {
				return svo;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
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
	 * Add By rongleia 根据学校主键查询学校基本信息
	 * 
	 * @param rolecode
	 * @return
	 */
	public static SchoolPOJO querySchoolByPk(String pk_school) {
		// TODO 自动生成的方法存根
		StringBuffer sql = new StringBuffer("");
		sql.append(
				"select sname label,pk_school value,region schoolregion,saddress schooladdress from cy_schoolBasics where pk_school = '"
						+ pk_school + "' and dr = '0'");
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		SchoolPOJO svo = new SchoolPOJO();
		try {
			svo = (SchoolPOJO) dao.executeQuery(sql.toString(), new BeanProcessor(SchoolPOJO.class));
			if (CyCommonUtils.isNotEmpty(svo)) {
				return svo;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
