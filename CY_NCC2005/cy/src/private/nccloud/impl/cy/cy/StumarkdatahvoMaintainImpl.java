
package nccloud.impl.cy.cy;

import nccloud.impl.pub.ace.AceAggbusiStumarkdataHVOPubServiceImpl;
import nccloud.itf.cy.cy.IStumarkdatahvoMaintain;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.creditcontract.pojo.CContractRetPojo;
import nc.vo.cy.stumarkdata.AggStumarkdataHVO;
import nc.vo.cy.stumarkdata.CurriculumInfo;
import nc.vo.cy.stumarkdata.CurriculumMsg;
import nc.vo.cy.stumarkdata.MarkDayVO;
import nc.vo.cy.stumarkdata.StudentInfoVO;
import nc.vo.cy.stumarkdata.StumarkdataQueryPOJO;
import nc.vo.cy.stumarkdata.StumarkdataYearPOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class StumarkdatahvoMaintainImpl extends AceAggbusiStumarkdataHVOPubServiceImpl
		implements IStumarkdatahvoMaintain {

	private BaseDAO baseDAO;

	private BaseDAO getBaseDAO() {
		if (baseDAO == null) {
			baseDAO = new BaseDAO();
		}
		return baseDAO;
	}

	@Override
	public void delete(AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggStumarkdataHVO[] insert(AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills)
			throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggStumarkdataHVO[] update(AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills)
			throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggStumarkdataHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggStumarkdataHVO[] save(AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills)
			throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggStumarkdataHVO[] unsave(AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills)
			throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggStumarkdataHVO[] approve(AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills)
			throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggStumarkdataHVO[] unapprove(AggStumarkdataHVO[] clientFullVOs, AggStumarkdataHVO[] originBills)
			throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StumarkdataYearPOJO> queryStuYearScoreByIdcard(StumarkdataQueryPOJO queryvo)
			throws BusinessException {

		if (MMValueCheck.isEmpty(queryvo)) {
			throw new BusinessException("查询参数异常");
		}
		String stuidcard = queryvo.getStuidcard();
		String scoreyear = queryvo.getScoreyear();
		if (MMValueCheck.isEmpty(stuidcard)) {
			throw new BusinessException("查询条件学生身份证号为空");
		}
		SqlBuilder sql = new SqlBuilder();
		sql.append(" SELECT * FROM ( ");
		sql.append(" SELECT  cysch.sname szxx,cycl.clasname cybj,tpaper.testname kssjmc, ");
		sql.append(" tpaper.testcode kssjbm,substr(smdh.examdate,0,10) kssjian,stu.sname xsxm,");
		sql.append(" smdb.def2, smdb.totalscore xsscore,substr(smdh.examdate,0,4) scoreyear");
		sql.append(" FROM cy_stumarkdata_detail smdb ");
		sql.append(" left join cy_stumarkdata smdh  on smdb.pk_stumark=smdh.pk_stumark ");
		sql.append(" left join cy_student stu on smdb.def1=stu.pk_student  ");
		sql.append(" left join cy_schoolbasics cysch on smdh.def1 = cysch.pk_school ");
		sql.append(" left join cy_classcy cycl on smdh.def2 = cycl.pk_classcy  ");
		sql.append(" left join cy_testpaperfile tpaper on smdh.def3 = tpaper.pk_testpaperfile  ");
		sql.append(" WHERE nvl(smdh.dr,0)=0 and nvl(smdb.dr,0)=0 ");
		sql.append(" and nvl(stu.dr,0)=0  and nvl(cysch.dr,0)=0  and nvl(cycl.dr,0)=0  and nvl(tpaper.dr,0)=0 ");
		if (MMValueCheck.isNotEmpty(stuidcard)) {
			sql.append(" and stu.idcard ", stuidcard);
		}
		sql.append(" ) socremark ");
		if (MMValueCheck.isNotEmpty(scoreyear)) {
			sql.append(" where scoreyear ", scoreyear);
		}
		sql.append(" order by kssjian asc ");
		List<StumarkdataYearPOJO> listCC = (List<StumarkdataYearPOJO>) getBaseDAO().executeQuery(sql.toString(),
				new BeanListProcessor(StumarkdataYearPOJO.class));
		return listCC;
	}
	
	
	
	@Override
	public List<CurriculumMsg> queryCurriculumByIdcard(CurriculumInfo CurriculumInfo) throws BusinessException {
		// addby  yangli5  根据id和单据日期查询学生课程信息
		if (MMValueCheck.isEmpty(CurriculumInfo)) {
			throw new BusinessException("查询参数异常");
		}
		String idcard = CurriculumInfo.getIdcard();
		String dbilldate = CurriculumInfo.getDbilldate();
		String type = CurriculumInfo.getType();
		if (MMValueCheck.isEmpty(idcard)) {
			throw new BusinessException("查询条件学生身份证号为空");
		}
		if (MMValueCheck.isEmpty(dbilldate)) {
			throw new BusinessException("查询条件日期为空");
		}
		if (MMValueCheck.isEmpty(type)) {
			throw new BusinessException("查询条件课程考勤不明确");
		}
		dbilldate=dbilldate+'%';
		String sql ="";
		if("1".equals(type)) {
			//课程
			 sql = "select  student.sname studentname,classcy.CLASNAME CLASNAME,sch.sname schoolname,classitua.classadress classadress, " + 
					" classitua.classbegintime||'-'||classitua.classendtime bgendtime ,defdoc.name attendancetype  " + 
					" from    cy_classituation classitua  " + 
					" left join cy_classituation_detail detail on detail.pk_situation = classitua.pk_situation  " + 
					" left join cy_schoolBasics sch on classitua.def1 = sch.pk_school  " + 
					" left join cy_classcy classcy on classcy.pk_classcy = detail.def2 " + 
					" left join cy_student student on student.pk_student =  detail.def1  " + 
					" left join bd_defdoc defdoc on defdoc.pk_defdoc = detail.def4  " + 
					" where classitua.dbilldate like '"+dbilldate+"' and classitua.dr = 0 and detail.dr = 0 and student.IDCARD = '"+idcard+"' order by classitua.classendtime ";
		}else if("2".equals(type)) {
			//考勤
			sql ="select  student.sname studentname,classcy.CLASNAME CLASNAME,sch.sname schoolname ," + 
					"	classitua.classadress classadress,  classitua.classbegintime||'-'||classitua.classendtime bgendtime ," + 
					"	doccq.name kqzt,doczy.name zyzt,dockt.name ktzt from     " + 
					"	cy_classituation classitua   " + 
					"	left join cy_classituation_detail detail on detail.pk_situation = classitua.pk_situation 	" + 
					"	left join cy_schoolBasics sch on classitua.def1 = sch.pk_school 	" + 
					"	left join cy_student student on student.pk_student =  detail.def1  	" + 
					"	left join cy_classcy classcy on classcy.pk_classcy = detail.def2 	" + 
					"	left join bd_defdoc doccq on doccq.pk_defdoc = detail.def4 		" + 
					"	left join bd_defdoc doczy on doczy.pk_defdoc = detail.def5 		" + 
					"	left join bd_defdoc dockt on dockt.pk_defdoc = detail.def6 		" + 
					"	where classitua.dbilldate like '"+dbilldate+"' and classitua.dr = 0 and detail.dr = 0 and student.IDCARD = '"+idcard+"'  and detail.def7='1'		" + 
					"	order by classitua.classendtime ";
		}else {
			return null;
		}
		List<CurriculumMsg> listCC = (List<CurriculumMsg>) getBaseDAO().executeQuery(sql.toString(),
				new BeanListProcessor(CurriculumMsg.class));
		return listCC;
	}

	@Override
	public List<MarkDayVO> queryMonthTimeTable(CurriculumInfo CurriculumInfo) throws BusinessException {
		// addby  yangli5  根据id和单据日期查询学生课程信息 大体数据
				if (MMValueCheck.isEmpty(CurriculumInfo)) {
					throw new BusinessException("查询参数异常");
				}
				String idcard = CurriculumInfo.getIdcard();
				String dbilldate = CurriculumInfo.getDbilldate();
				String type = CurriculumInfo.getType();
				if (MMValueCheck.isEmpty(idcard)) {
					throw new BusinessException("查询条件学生身份证号为空");
				}
				if (MMValueCheck.isEmpty(dbilldate)) {
					throw new BusinessException("查询条件日期为空");
				}
				if (MMValueCheck.isEmpty(type)) {
					throw new BusinessException("查询条件课程考勤不明确");
				}
				dbilldate=dbilldate+'%';
				String sql ="";
				if("1".equals(type)) {
					sql =" select  SUBSTR(classitua.dbilldate,0,10)  markday" + 
							"	from    cy_classituation classitua   " + 
							"	left join cy_classituation_detail detail on detail.pk_situation = classitua.pk_situation     " + 
							"	left join cy_student student on student.pk_student =  detail.def1    " + 
							"	where classitua.dbilldate like '"+dbilldate+"' and classitua.dr = 0 and detail.dr = 0 and student.IDCARD = '"+idcard+"'  ";
				}else if("2".equals(type)){
					sql =" select  SUBSTR(classitua.dbilldate,0,10)  markday" + 
							"	from    cy_classituation classitua   " + 
							"	left join cy_classituation_detail detail on detail.pk_situation = classitua.pk_situation     " + 
							"	left join cy_student student on student.pk_student =  detail.def1    " + 
							"	where classitua.dbilldate like '"+dbilldate+"' and classitua.dr = 0 and detail.dr = 0 and detail.def7='1' and student.IDCARD = '"+idcard+"' ";
				}else {
					return null;
				}
				List<MarkDayVO> listCC = (List<MarkDayVO>) getBaseDAO().executeQuery(sql.toString(),
						new BeanListProcessor(MarkDayVO.class));
				
				
		return listCC;
	}

	@Override
	public StudentInfoVO queryStudentInfos(String id) throws BusinessException {
		BaseDAO dao = new BaseDAO();
		if (id==null||"".equals(id)) {
			throw new BusinessException("查询失败学生id为空");
		}
		
		String sql =" select stu.sname sname,sch.sname schoolname,cla.clasname classname,cla.bill_no classno,xz.clasname xzname,psn.name thachername from cy_student stu " + 
				"  left join cy_classcy cla on stu.def1 = cla.pk_classcy  " + 
				"  left join cy_schoolBasics sch on  sch.pk_school = stu.def2  " + 
				"  left join cy_classxz xz on xz.PK_CLASSXZ = stu.def4  " + 
				"  left join bd_psndoc psn on psn.pk_psndoc = cla.teacher  " + 
				"  where stu.idcard = '"+id+"' and stu.dr = 0 and cla.dr= 0 ";
		List<StudentInfoVO> list = (List<StudentInfoVO>) dao.executeQuery(sql,
				new BeanListProcessor(StudentInfoVO.class));
		if (CyCommonUtils.isNotEmpty(list)) {
			if (list.size() == 1) {
				StudentInfoVO result = list.get(0);
				return result;	
			}else {
				for (StudentInfoVO stuQueryPOJO : list) {
					return stuQueryPOJO;
				}
			}
		}
		
		return null;
	}

}
