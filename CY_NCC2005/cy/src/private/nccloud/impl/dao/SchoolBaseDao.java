package nccloud.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.security.token.ISecurityTokenCache;
import nc.bs.framework.server.ISecurityTokenCallback;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.generator.IdGenerator;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.SqlContext;
import nc.vo.cy.pojo.SchoolPOJO;
import nc.vo.cy.schoolarchives.SchoolBasicsBusinVO;
import nc.vo.cy.schoolarchives.pojo.SchoolBasicsClassPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolBasicsLinkPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolBasicsPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolBasicsTopPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolBasicsVisitPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolListDetailPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolQueryPOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

/**
 * 
 * @author RONGLEIA
 * 
 */
public class SchoolBaseDao {

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
				sql.append("select count(1)  from cy_schoolbasics where dr = '0' and sname like '%" + sname + "%'");
			} else {
				sql.append("select count(1)  from cy_schoolbasics where dr = '0'");
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

	/**
	 * Add By rongleia 根据查询条件查询学校档案列表态数据
	 * 
	 * @param rolecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SchoolListDetailPOJO> querySchoolList(SchoolQueryPOJO querypojo) {
		// TODO 自动生成的方法存根
		StringBuffer sql = new StringBuffer("");
		int index = querypojo.getIndex().intValue();// 第几页
		int size = querypojo.getSize().intValue();// 每页数据量
		UFDouble start = (new UFDouble(index).sub(new UFDouble(1))).multiply(new UFDouble(size)).add(new UFDouble(1));
		UFDouble end = new UFDouble(index).multiply(new UFDouble(size));
		String sname = querypojo.getSname();
		if (CyCommonUtils.isNotEmpty(sname)) {
			sql.append(
					"select pk_school,sname,saddress,sphone,isuse from ( select rownum as rn, pk_school,sname,saddress,sphone,isuseing isuse from cy_schoolbasics where dr = 0 and sname like '%"
							+ sname + "%'" + " and rownum <= " + end + " order by ts desc) where 1 =1 and rn >= "
							+ start);
		} else {
			sql.append(
					"select pk_school,sname,saddress,sphone,isuse from ( select rownum as rn, pk_school,sname,saddress,sphone,isuseing isuse from cy_schoolbasics where dr = 0 and rownum <= "
							+ end + " order by ts desc) where 1 =1 and rn >= " + start);
		}
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		List<SchoolListDetailPOJO> listvos = new ArrayList<SchoolListDetailPOJO>();
		try {
			listvos = (List<SchoolListDetailPOJO>) dao.executeQuery(sql.toString(),
					new BeanListProcessor(SchoolListDetailPOJO.class));
			if (CyCommonUtils.isNotEmpty(listvos)) {
				return listvos;
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
	public static SchoolBasicsPOJO querySchoolBasicByPk(String pk_school) {
		// TODO 自动生成的方法存根
		StringBuffer sql = new StringBuffer("");
		sql.append(
				"select pk_school,substr(dbilldate,1,10) dbilldate, approvestatus,bill_no,sname,saddress,sphone,region,province,vnote from cy_schoolBasics where pk_school = '"
						+ pk_school + "' and dr = '0'");
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		SchoolBasicsPOJO svo = new SchoolBasicsPOJO();
		try {
			svo = (SchoolBasicsPOJO) dao.executeQuery(sql.toString(), new BeanProcessor(SchoolBasicsPOJO.class));
			if (CyCommonUtils.isNotEmpty(svo)) {
				return svo;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Add By rongleia 根据学校主键查询学校拜访记录
	 * 
	 * @param rolecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SchoolBasicsVisitPOJO> querySchoolBasicVisitByPk(String pk_school) {
		// TODO 自动生成的方法存根
		StringBuffer sql = new StringBuffer("");
		sql.append(
				"select pk_visit,busilevel ufbusilevel,develsituation ufdevelsituation,gradenum,competitor,rivaldevelsituation,gkgrade,intedegree ufintedegree,specialnote,rowno from Cy_Schoolbasics_Visit where pk_school = '"
						+ pk_school + "' and dr = '0'");
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		List<SchoolBasicsVisitPOJO> listvos = new ArrayList<SchoolBasicsVisitPOJO>();
		try {
			listvos = (List<SchoolBasicsVisitPOJO>) dao.executeQuery(sql.toString(),
					new BeanListProcessor(SchoolBasicsVisitPOJO.class));
			if (CyCommonUtils.isNotEmpty(listvos)) {
				return listvos;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Add By rongleia 根据学校主键查询学校年纪情况
	 * 
	 * @param rolecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SchoolBasicsClassPOJO> querySchoolBasicClassByPk(String pk_school) {
		// TODO 自动生成的方法存根
		StringBuffer sql = new StringBuffer("");
		sql.append(
				"select pk_class,grade,psnnum,whetherclas ufwhetherclas,cause,rowno from Cy_Schoolbasics_Class where pk_school = '"
						+ pk_school + "' and dr = '0'");
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		List<SchoolBasicsClassPOJO> listvos = new ArrayList<SchoolBasicsClassPOJO>();
		try {
			listvos = (List<SchoolBasicsClassPOJO>) dao.executeQuery(sql.toString(),
					new BeanListProcessor(SchoolBasicsClassPOJO.class));
			if (CyCommonUtils.isNotEmpty(listvos)) {
				return listvos;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Add By rongleia 根据学校主键查询学校联系人信息
	 * 
	 * @param rolecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SchoolBasicsLinkPOJO> querySchoolBasicLinkByPk(String pk_school) {
		// TODO 自动生成的方法存根
		StringBuffer sql = new StringBuffer("");
		sql.append(
				"select pk_link,name,position,duty,substr(birthday,1,10) birthday,linkman1,linkman2,linkman3,rowno from Cy_Schoolbasics_Link where pk_school = '"
						+ pk_school + "' and dr = '0'");
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		List<SchoolBasicsLinkPOJO> listvos = new ArrayList<SchoolBasicsLinkPOJO>();
		try {
			listvos = (List<SchoolBasicsLinkPOJO>) dao.executeQuery(sql.toString(),
					new BeanListProcessor(SchoolBasicsLinkPOJO.class));
			if (CyCommonUtils.isNotEmpty(listvos)) {
				return listvos;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Add By rongleia 根据学校主键查询高管学校维护记录
	 * 
	 * @param rolecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SchoolBasicsTopPOJO> querySchoolBasicTopByPk(String pk_school) {
		// TODO 自动生成的方法存根
		StringBuffer sql = new StringBuffer("");
		sql.append(
				"select pk_top,visitobj,solveproblem,visitpatrol,stablelevel ufstablelevel,redornot ufredornot,gradepsnnum,isexistside ufisexistside,specialnote,substr(visitdate,1,10) visitdate,rowno from Cy_Schoolbasics_Top where pk_school = '"
						+ pk_school + "' and dr = '0'");
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		List<SchoolBasicsTopPOJO> listvos = new ArrayList<SchoolBasicsTopPOJO>();
		try {
			listvos = (List<SchoolBasicsTopPOJO>) dao.executeQuery(sql.toString(),
					new BeanListProcessor(SchoolBasicsTopPOJO.class));
			if (CyCommonUtils.isNotEmpty(listvos)) {
				return listvos;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Add By rongleia 修改联系人信息
	 * 
	 * @param rolecode
	 * @return
	 * @throws ParseException
	 */
	public static String updateLink(SchoolBasicsLinkPOJO linkpojo, String pk_school) throws ParseException {
		JdbcSession session = CyCommonUtils.getNcDBmanager().getJdbcSession();
		Connection conn = session.getConnection();
		// 组装查询SQL语句并执行
		String vostatus = linkpojo.getVostatus();
		String birthday = linkpojo.getBirthday();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = sdf.parse(birthday);
		UFDateTime ufDateTime = new UFDateTime(parse);
		try {
			conn.setAutoCommit(false);
			if ("1".equals(vostatus)) {
				// 更新
				PreparedStatement update = conn.prepareStatement(SqlContext.UPDATE_LINK);
				update.setString(1, linkpojo.getName());
				update.setString(2, linkpojo.getPosition());
				update.setString(3, linkpojo.getDuty());
				update.setString(4, ufDateTime.toString());
				update.setString(5, linkpojo.getLinkman1());
				update.setString(6, linkpojo.getLinkman2());
				update.setString(7, linkpojo.getLinkman3());
				update.setString(8, new UFDate().toString());
				update.setString(9, linkpojo.getPk_link());
				update.executeUpdate();
			} else if ("2".equals(vostatus)) {
				// 新增
				PreparedStatement insert = conn.prepareStatement(SqlContext.INSERT_LINK);
				insert.setString(1, getNewPK());
				insert.setString(2, linkpojo.getName());
				insert.setString(3, linkpojo.getPosition());
				insert.setString(4, linkpojo.getDuty());
				insert.setString(5, ufDateTime.toString());
				insert.setString(6, linkpojo.getLinkman1());
				insert.setString(7, linkpojo.getLinkman2());
				insert.setString(8, linkpojo.getLinkman3());
				insert.setString(9, pk_school);
				insert.executeUpdate();
			} else if ("3".equals(vostatus)) {
				// 删除
				PreparedStatement delete = conn.prepareStatement(SqlContext.UPDATE_LINK_D);
				delete.setString(1, linkpojo.getPk_link());
				delete.executeUpdate();
			}
			conn.commit();// 提交
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			return e.getMessage();
		}
		return "ok";
	}

	/**
	 * Add By rongleia 修改班级信息
	 * 
	 * @param rolecode
	 * @return
	 */
	public static String updateClass(SchoolBasicsClassPOJO clapojo, String pk_school) {
		JdbcSession session = CyCommonUtils.getNcDBmanager().getJdbcSession();
		Connection conn = session.getConnection();
		// 组装查询SQL语句并执行
		String vostatus = clapojo.getVostatus();
		try {
			conn.setAutoCommit(false);
			if ("1".equals(vostatus)) {
				// 更新
				PreparedStatement update = conn.prepareStatement(SqlContext.UPDATE_CLASS);
				update.setString(1, clapojo.getGrade());
				update.setString(2, clapojo.getPsnnum());
				update.setString(3, clapojo.getWhetherclas().getValue());
				update.setString(4, clapojo.getCause());
				update.setString(5, new UFDate().toString());
				update.setString(6, clapojo.getPk_class());
				update.executeUpdate();
			} else if ("2".equals(vostatus)) {
				// 新增
				PreparedStatement insert = conn.prepareStatement(SqlContext.INSERT_CLASS);
				insert.setString(1, getNewPK());
				insert.setString(2, clapojo.getGrade());
				insert.setString(3, clapojo.getPsnnum());
				insert.setString(4, clapojo.getWhetherclas().getValue());
				insert.setString(5, clapojo.getCause());
				insert.setString(9, pk_school);
				insert.executeUpdate();
			} else if ("3".equals(vostatus)) {
				// 删除
				PreparedStatement delete = conn.prepareStatement(SqlContext.UPDATE_CLASS_D);
				delete.setString(1, clapojo.getPk_class());
				delete.executeUpdate();
			}
			conn.commit();// 提交
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			return e.getMessage();
		}
		return "ok";
	}

	/**
	 * Add By rongleia 修改拜访信息
	 * 
	 * @param rolecode
	 * @return
	 */
	public static String updateVisit(SchoolBasicsVisitPOJO vispojo, String pk_school) {
		JdbcSession session = CyCommonUtils.getNcDBmanager().getJdbcSession();
		Connection conn = session.getConnection();
		// 组装查询SQL语句并执行
		String vostatus = vispojo.getVostatus();
		try {
			conn.setAutoCommit(false);
			if ("1".equals(vostatus)) {
				// 更新
				PreparedStatement update = conn.prepareStatement(SqlContext.UPDATE_VISIT);
				update.setString(1, vispojo.getBusilevel().getValue());
				update.setString(2, vispojo.getDevelsituation().getValue());
				update.setString(3, vispojo.getGradenum());
				update.setString(4, vispojo.getCompetitor());
				update.setString(5, vispojo.getRivaldevelsituation());
				update.setString(6, vispojo.getGkgrade());
				update.setString(7, vispojo.getIntedegree().getValue());
				update.setString(8, vispojo.getSpecialnote());
				update.setString(9, new UFDate().toString());
				update.setString(10, vispojo.getPk_visit());
				update.executeUpdate();
			} else if ("2".equals(vostatus)) {
				// 新增
				PreparedStatement insert = conn.prepareStatement(SqlContext.INSERT_VISIT);
				insert.setString(1, getNewPK());
				insert.setString(2, vispojo.getBusilevel().getValue());
				insert.setString(3, vispojo.getDevelsituation().getValue());
				insert.setString(4, vispojo.getGradenum());
				insert.setString(5, vispojo.getCompetitor());
				insert.setString(6, vispojo.getRivaldevelsituation());
				insert.setString(7, vispojo.getGkgrade());
				insert.setString(8, vispojo.getIntedegree().getValue());
				insert.setString(9, vispojo.getSpecialnote());
				insert.setString(10, pk_school);
				insert.executeUpdate();
			} else if ("3".equals(vostatus)) {
				// 删除
				PreparedStatement delete = conn.prepareStatement(SqlContext.UPDATE_VISIT_D);
				delete.setString(1, vispojo.getPk_visit());
				delete.executeUpdate();
			}
			conn.commit();// 提交
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			return e.getMessage();
		}
		return "ok";
	}

	/**
	 * Add By rongleia 修改高管维护信息
	 * 
	 * @param rolecode
	 * @return
	 * @throws ParseException
	 */
	public static String updateTop(SchoolBasicsTopPOJO toppojo, String pk_school) throws ParseException {
		JdbcSession session = CyCommonUtils.getNcDBmanager().getJdbcSession();
		Connection conn = session.getConnection();
		// 组装查询SQL语句并执行
		String vostatus = toppojo.getVostatus();
		String visitdate = toppojo.getVisitdate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = sdf.parse(visitdate);
		UFDateTime ufDateTime = new UFDateTime(parse);
		try {
			conn.setAutoCommit(false);
			if ("1".equals(vostatus)) {
				// 更新
				PreparedStatement update = conn.prepareStatement(SqlContext.UPDATE_TOP);
				update.setString(1, toppojo.getVisitobj());
				update.setString(2, toppojo.getSolveproblem());
				update.setString(3, toppojo.getVisitpatrol());
				update.setString(4, toppojo.getStablelevel().getValue());
				update.setString(5, toppojo.getRedornot().getValue());
				update.setString(6, toppojo.getGradepsnnum());
				update.setString(7, toppojo.getIsexistside().getValue());
				update.setString(8, toppojo.getSpecialnote());
				update.setString(9, ufDateTime.toString());
				update.setString(10, new UFDate().toString());
				update.setString(11, toppojo.getPk_top());
				update.executeUpdate();
			} else if ("2".equals(vostatus)) {
				// 新增
				PreparedStatement insert = conn.prepareStatement(SqlContext.INSERT_TOP);
				insert.setString(1, getNewPK());
				insert.setString(2, toppojo.getVisitobj());
				insert.setString(3, toppojo.getSolveproblem());
				insert.setString(4, toppojo.getVisitpatrol());
				insert.setString(5, toppojo.getStablelevel().getValue());
				insert.setString(6, toppojo.getRedornot().getValue());
				insert.setString(7, toppojo.getGradepsnnum());
				insert.setString(8, toppojo.getIsexistside().getValue());
				insert.setString(9, toppojo.getSpecialnote());
				insert.setString(10, ufDateTime.toString());
				insert.setString(11, pk_school);
				insert.executeUpdate();
			} else if ("3".equals(vostatus)) {
				// 删除
				PreparedStatement delete = conn.prepareStatement(SqlContext.UPDATE_TOP_D);
				delete.setString(1, toppojo.getPk_top());
				delete.executeUpdate();
			}
			conn.commit();// 提交
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			return e.getMessage();
		}
		return "ok";
	}

	/**
	 * Add By rongleia 修改高管维护信息
	 * 
	 * @param rolecode
	 * @return
	 * @throws ParseException
	 */
	public static String insertBusin(SchoolBasicsBusinVO schoolBasicsBusinVO) throws ParseException {
		JdbcSession session = CyCommonUtils.getNcDBmanager().getJdbcSession();
		Connection conn = session.getConnection();
		// 组装查询SQL语句并执行
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			conn.setAutoCommit(false);
			// 新增
			PreparedStatement insert = conn.prepareStatement(SqlContext.INSERT_BUSIN);
			insert.setString(1, getNewPK());
			insert.setString(2, schoolBasicsBusinVO.getDef1());
			insert.setString(3, schoolBasicsBusinVO.getDef2());
			insert.setString(4, schoolBasicsBusinVO.getDef3());
			insert.setString(5, schoolBasicsBusinVO.getDef4());
			insert.setString(6, schoolBasicsBusinVO.getDef5());
			insert.setString(7, schoolBasicsBusinVO.getPk_school());
			insert.executeUpdate();
			conn.commit();// 提交
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			return e.getMessage();
		}
		return "ok";
	}

	public static void updateTs(String pk_school) {
		UFDateTime ufDateTime = new UFDateTime();
		String ts = ufDateTime.toString();
		try {
			// TODO 自动生成的方法存根
			StringBuffer sql = new StringBuffer("");
			sql.append("update cy_schoolbasics set ts = '" + ts + "' where pk_school = '" + pk_school + "'");
			// 组装查询SQL语句并执行
			BaseDAO dao = new BaseDAO();

			dao.executeUpdate(sql.toString());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add By rongleia 设置token
	 * 
	 * @param rolecode
	 * @return
	 */
	public static void login() {
		ISecurityTokenCallback sc = (ISecurityTokenCallback) NCLocator.getInstance()
				.lookup(ISecurityTokenCallback.class);
		// 得到token
		byte[] token = sc.token("NCSystem".getBytes(), "pfxx".getBytes());
		// token存入NC系统的缓存
		ISecurityTokenCache tokencache = (ISecurityTokenCache) NCLocator.getInstance()
				.lookup(ISecurityTokenCache.class);
		boolean contain = tokencache.isContain(token);
		if (!contain) {
			tokencache.setToken(token);
		}
	}

	// pk生成器
	private static String getNewPK() {
		IdGenerator idGenerator = new SequenceGenerator();
		return idGenerator.generate();
	}
}
