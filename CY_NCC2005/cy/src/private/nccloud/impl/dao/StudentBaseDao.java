package nccloud.impl.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.pojo.SchoolPOJO;
import nc.vo.cy.pojo.StuRef;
import nc.vo.cy.studentfile.StudentParentVO;
import nc.vo.cy.teacherform.pojo.BaseRef;
import nc.vo.cy.workplan.WorkplanbVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.UserVO;

/**
 * 
 * @author RONGLEIA
 * 
 */
public class StudentBaseDao {


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
	
	// 查询匹配学生信息
	public static StuRef queryStudentByPk(String id,String sname) throws DAOException {
		
		if (StringUtils.isAllEmpty(id)) {
			return null;
		}
		SqlBuilder sql = new SqlBuilder();
		sql.append(" select pk_student,sname,idcard sidcard,studenttype stype from cy_student where   pk_student = '" + id + "' and dr = '0'");
	
		if (StringUtils.isNotEmpty(sname)) {
			sql.append(" and sname like '%" + sname + "%'");
		}
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		StuRef ref=new StuRef();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		try {
			ref = (StuRef) dao.executeQuery(sql.toString(), new BeanProcessor(StuRef.class));
			if (CyCommonUtils.isNotEmpty(ref)) {
				return ref;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	// 查询匹配部门信息
	public static BaseRef queryDeptByPk(String id) throws DAOException {
		
		if (StringUtils.isAllEmpty(id)) {
			return null;
		}
		SqlBuilder sql = new SqlBuilder();
		sql.append(" select pk_dept value,name label,code code from org_dept where  pk_dept = '" + id + "' and dr = '0'");
	
		
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		BaseRef ref=new BaseRef();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		try {
			ref = (BaseRef) dao.executeQuery(sql.toString(), new BeanProcessor(BaseRef.class));
			if (CyCommonUtils.isNotEmpty(ref)) {
				return ref;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	public static BaseRef queryClassByPk(String pk_school) {
		// TODO 自动生成的方法存根
		StringBuffer sql = new StringBuffer("");
		sql.append(
				"select clasname label,pk_classcy value from cy_classcy where pk_classcy = '"
						+ pk_school + "' and dr = '0'");
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		BaseRef svo = new BaseRef();
		try {
			svo = (BaseRef) dao.executeQuery(sql.toString(), new BeanProcessor(BaseRef.class));
			if (CyCommonUtils.isNotEmpty(svo)) {
				return svo;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BaseRef queryPetitionerByPk(String pk_proposer) {
		// TODO 自动生成的方法存根
		StringBuffer sql = new StringBuffer("");
		sql.append("select name label,pk_psndoc value,code from bd_psndoc where pk_psndoc = '" + pk_proposer
				+ "' and dr = '0'");
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		try {
			 BaseRef rvo = (BaseRef) dao.executeQuery(sql.toString(), new BeanProcessor(BaseRef.class));
			if (CyCommonUtils.isNotEmpty(rvo)) {
				return rvo;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static StudentParentVO queryParentStuByPk(String pk_proposer) {
		// TODO 自动生成的方法存根
		StringBuffer sql = new StringBuffer("");
		sql.append("select pk_parent parentphone,rowno from cy_student_parent where pk_parent = '" + pk_proposer
				+ "' and dr = '0'");
		// 组装查询SQL语句并执行
		BaseDAO dao = new BaseDAO();
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		try {
			StudentParentVO rvo = (StudentParentVO) dao.executeQuery(sql.toString(), new BeanProcessor(StudentParentVO.class));
			if (CyCommonUtils.isNotEmpty(rvo)) {
				return rvo;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	public static WorkplanbVO queryDesByPk(String creator,UFDate date) {
		// TODO 自动生成的方法存根
		try {
		StringBuffer sql = new StringBuffer("");
//		StringBuffer sql1 = new StringBuffer("");
//		String salesman="";
//		sql1.append("select * from sm_user where cuserid = '" + creator
//				+ "' and dr = '0'");
		BaseDAO dao = new BaseDAO();
//		UserVO uvo = (UserVO) dao.executeQuery(sql1.toString(), new BeanProcessor(UserVO.class));
//		if (CyCommonUtils.isNotEmpty(uvo)) {
//			salesman=uvo.getPk_psndoc();
//		}
//		String ds=date.toString().substring(0, 10)+" 00:00:00";
		if(StringUtils.isAllEmpty(creator)) {
		sql.append("select * from cy_workplanb where pk_workplan in ('");
		sql.append("select * from cy_workplan where  salesman  = '"+creator+"') and visitdate = '");
		sql.append(date+"' and dr ='0'");
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		}
		
		List<WorkplanbVO> rvos = new ArrayList<WorkplanbVO>();
		 rvos = (List<WorkplanbVO>) dao.executeQuery(sql.toString(), new BeanListProcessor(WorkplanbVO.class));
		 if (rvos!=null&&rvos.size()!=0) {
				return rvos.get(0);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}


