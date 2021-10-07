package nccloud.impl.cy.crm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;
import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
import nc.api.cy.rest.entity.teach.course.CourseDeatil;
import nc.api.cy.rest.entity.teach.course.CourseStudentSave;
import nc.api.cy.rest.entity.teach.course.StudentClassSituation;
import nc.api.cy.rest.entity.teach.course.StudentInfo;
import nc.api.cy.rest.entity.teach.course.TeacherCourse;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.itf.uap.pf.IPFBusiAction;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.bd.defdoc.DefdocVO;
import nc.vo.cy.classfilecy.ClasscyHVO;
import nc.vo.cy.classfilexz.ClassxzHVO;
import nc.vo.cy.classituation.AggClassituationHVO;
import nc.vo.cy.classituation.ClassituationDetailVO;
import nc.vo.cy.classituation.ClassituationHVO;
import nc.vo.cy.schoolarchives.SchoolBasicsHVO;
import nc.vo.cy.studentfile.StudentHVO;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.sm.UserVO;

public class CrmServiceImpl implements ICrmService {

	private static IPFBusiAction pfBusiAction = null;

	private static BaseDAO baseDao;

	private static IMDPersistenceQueryService aggvoQueryService;

	@Override
	public UserVO getUserInfoByUserMobile(String userMobile) throws BusinessException {
		UserVO user = executeQueryVO("select * from sm_user where pk_psndoc  = (select pk_psndoc from bd_psndoc where bd_psndoc.mobile='"+userMobile+"')", UserVO.class);
		return user;
	}

	@Override
	public OrgVO getOrgByPrimaryKey(String pk_org) throws BusinessException {
		OrgVO org = executeQueryVO("select * from org_orgs where pk_org = '" + pk_org + "'", OrgVO.class);
		return org;
	}

	@Override
	public BilltypeVO getBillTypeVOByCode(String billTypeCode) throws BusinessException {
		BilltypeVO billType = executeQueryVO("select * from bd_billtype where pk_billtypecode = '" + billTypeCode + "'",
				BilltypeVO.class);
		return billType;
	}

	@Override
	public ResponseList queryListPage(String condition, QueryPage queryPage, Class<? extends AbstractBill> NccAggClazz,
			Class RestHeadClazz) throws BusinessException {
		int pageindex = queryPage.getIndex();// 当前页
		int pagesize = queryPage.getSize();// 每页大小
		String sort = queryPage.getSort();// 排序条件

		Object bill = Constructor.construct(NccAggClazz);
		AbstractBill nccAggVO = (AbstractBill) bill;

		IVOMeta metaH = nccAggVO.getMetaData().getParent();
		String tableName = metaH.getStatisticInfo().getTables()[0].toString();
		String tablePrimaryKeyName = metaH.getPrimaryAttribute().getName();
		
		// 排序条件
		String sortwhereSql = "";
		if (!StringUtils.isBlank(sort)) {
			sortwhereSql = " order by " + sort;
		}else {
			sortwhereSql = " order by ts desc";
		}

		String querySql = "select row_number() over ("+sortwhereSql+") rowno," + tableName + ".* from " + tableName + " where dr = 0 ";
		if (!StringUtils.isBlank(condition)) {
			// 添加条件
			querySql = querySql + " and " + condition;
		}

//		// 添加排序
		querySql = querySql + sortwhereSql;

		// 拼装分页查询sql
		int bpage = (pageindex - 1) * (pagesize) + 1;// 开始
		int epage = pageindex * pagesize;// 结束

		StringBuffer str = new StringBuffer();
		str.append("select b.* from (select rowno," + tablePrimaryKeyName + " from (");
		str.append(querySql);
		str.append(") temp ");
		str.append("where rowno >=" + bpage);
		str.append(" and rowno <= " + epage);
		str.append(") a," + tableName + " b where a." + tablePrimaryKeyName + " = b." + tablePrimaryKeyName + "");
		
		// 添加排序
		str.append(sortwhereSql);

		// 进行分页查询
		ChangeEntitySoPowerful ChangeEntitySoPowerful = new ChangeEntitySoPowerful();
		ISuperVO[] nccParentVOs = executeQueryVOs(str.toString(), nccAggVO.getMetaData().getVOClass(metaH));
		if (nccParentVOs == null || nccParentVOs.length == 0) {
			ChangeEntitySoPowerful.pageListPage(RestHeadClazz, null, 0, 0, pageindex, pagesize);
		} else {
			int total = queryCount(condition, tableName);// 总数量
			int totalPages = getTotalPage(pagesize, total);// 总页数

			List<AbstractBill> listAggVo = new ArrayList<AbstractBill>();
			Object[] nccAggBills = Constructor.construct(NccAggClazz, nccParentVOs.length);
			for (int i = 0; i < nccAggBills.length; i++) {
				AbstractBill nccAggBill = (AbstractBill) nccAggBills[i];
				nccAggBill.setParent(nccParentVOs[i]);
				listAggVo.add(nccAggBill);
			}

			ChangeEntitySoPowerful.pageListPage(RestHeadClazz, listAggVo, total, totalPages, pageindex, pagesize);
		}

		return ChangeEntitySoPowerful.getPageList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public AbstractBill queryDeatil(String primaryKey, Class<? extends AbstractBill> NccAggClazz)
			throws BusinessException {
		Object bill = Constructor.construct(NccAggClazz);
		AbstractBill nccAggVO = (AbstractBill) bill;
		IVOMeta metaH = nccAggVO.getMetaData().getParent();
		String tablePrimaryKeyName = metaH.getPrimaryAttribute().getName();

		String whereSql = " " + tablePrimaryKeyName + "=" + "'" + primaryKey + "'";// 条件

		// 根据主键查询得到aggvo
		@SuppressWarnings("rawtypes")
		Collection aggVOs = getMDPersistenceQueryService().queryBillOfVOByCond(NccAggClazz, whereSql, true, false);

		if (aggVOs.isEmpty() || aggVOs.size() == 0) {
			return null;
		}

		nccAggVO = (AbstractBill) aggVOs.toArray(new AbstractBill[0])[0];
		return nccAggVO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object pfSaveBusiAction(String billType, AbstractBill aggvo, boolean isSaveCommit) throws BusinessException {
		try {
			if(StringUtils.isBlank(billType)) {
				throw new BusinessException("单据类型不能为空");
			}
			// 标准按钮编码示例：保存SAVEBASE、删除DELETE、提交SAVE、收回UNSAVEBILL
			Object[] obj = null;
			if (!StringUtils.isBlank(aggvo.getPrimaryKey())) {
				// 对于修改的单据，需要校验当前单据状态，只要为单据审批了就不允许修改，如果为提交态则先收回
				String billstatus = aggvo.getParentVO().getAttributeValue("approvestatus").toString();
				
				if(StringUtils.isBlank(billstatus)) {
					throw new BusinessException("审批状态为空，数据不能进行操作");
				}

				// -1自由态和3提交态的单据可以修改，其他单据状态均不能修改
				if (!billstatus.equals("-1") && !billstatus.equals("3")) {
					throw new BusinessException("单据已被审批，不能修改");
				}

				// 收回
				if (billstatus.equals("3")) {
					obj = getPFBusiAction().processBatch("UNSAVEBILL", billType, new AbstractBill[] { aggvo }, null, null, new HashMap());
					if (obj == null || obj.length == 0) {
						throw new BusinessException("执收回行动作脚本出错：billType：" + billType + "，actionCode：UNSAVEBILL");
					}
					aggvo = (AbstractBill) obj[0];
				}
			}

			obj = getPFBusiAction().processBatch("SAVEBASE", billType, new AbstractBill[] { aggvo }, null, null, new HashMap());
			if (obj == null || obj.length == 0) {
				throw new BusinessException("执保存行动作脚本出错：billType：" + billType + "，actionCode：SAVEBASE");
			}

			if (isSaveCommit) {
				aggvo = (AbstractBill) obj[0];
				obj = getPFBusiAction().processBatch("SAVE", billType, new AbstractBill[] { aggvo }, null, null, new HashMap());
				if (obj == null || obj.length == 0) {
					throw new BusinessException("执提交行动作脚本出错：billType：" + billType + "，actionCode：SAVEBASE");
				}
			}
			return obj[0];
		} catch (Exception e) {
			throw new  BusinessException(e);
		}
	}
	
	@Override
	public Object pfBusiAction(String billType, String actionCode, AbstractBill aggvo) throws BusinessException {
		if(aggvo==null) {
			throw new BusinessException("当前操作的数据不能为空");
		}
		if(StringUtils.isBlank(billType)) {
			throw new BusinessException("单据类型/交易类型 编码不能为空");
		}
		if(StringUtils.isBlank(actionCode)) {
			throw new BusinessException("动作脚本编码不能为空");
		}
		
		// 标准按钮编码示例：保存SAVEBASE、删除DELETE、提交SAVE、收回UNSAVEBILL、审批APPROVE 、取消审批UNAPPROVE
		if(!actionCode.equals("SAVEBASE") && !actionCode.equals("DELETE") && !actionCode.equals("SAVE") 
				&& !actionCode.equals("UNSAVEBILL") && !actionCode.equals("APPROVE") && !actionCode.equals("UNAPPROVE")) {
			throw new BusinessException("动作脚本编码格式错误");
		}
		
		String billstatus = aggvo.getParentVO().getAttributeValue("approvestatus").toString();
		if(StringUtils.isBlank(billstatus)) {
			throw new BusinessException("审批状态为空，数据不能执行动作脚本");
		}
		
		switch (actionCode) {
			case "SAVEBASE":
				//任意状态单据都可以进行操作
				break;
			case "DELETE":
				if(StringUtils.isBlank(aggvo.getPrimaryKey())) {
					throw new BusinessException("当前需要删除数据有误，请重新查询");
				}
				//只有-1自由态单据可以删除
				if(!billstatus.equals("-1")) {
					throw new BusinessException("数据状态不匹配，当前单据不能被删除");
				}
				break;
			case "SAVE":
				if(StringUtils.isBlank(aggvo.getPrimaryKey())) {
					throw new BusinessException("当前需要提交的数据有误，请重新查询");
				}
				//只有-1自由态单据可以提交
				if(!billstatus.equals("-1")) {
					throw new BusinessException("数据状态不匹配，当前单据不能被提交");
				}
				break;
			case "UNSAVEBILL":
				if(StringUtils.isBlank(aggvo.getPrimaryKey())) {
					throw new BusinessException("当前需要收回的数据有误，请重新查询");
				}
				//只有3提交态态单据可以收回
				if(!billstatus.equals("3")) {
					throw new BusinessException("数据状态不匹配，当前单据不能被收回");
				}
				break;
			case "APPROVE":
				if(StringUtils.isBlank(aggvo.getPrimaryKey())) {
					throw new BusinessException("当前需要审批的数据有误，请重新查询");
				}
				break;
			case "UNAPPROVE":
				if(StringUtils.isBlank(aggvo.getPrimaryKey())) {
					throw new BusinessException("当前需要取消审批的数据有误，请重新查询");
				}
				break;
			default:
				break;
		}
		
		@SuppressWarnings("rawtypes")
		Object[] obj = getPFBusiAction().processBatch(actionCode, billType, new AbstractBill[] { aggvo }, null, null, new HashMap());
		
		if (obj == null || obj.length == 0) {
			throw new BusinessException("执行动作脚本出错：【billType】：" + billType + "，【actionCode】："+ actionCode);
		}
		return null;
	}
	
	@Override
	public Map<String, TeacherCourse> getTeacherCourse(UserVO user, String year, String month) throws BusinessException {
		if(user==null || StringUtils.isBlank(year) || StringUtils.isBlank(month)) {
			throw new BusinessException("参数信息全都必传");
		}
		Map<String, TeacherCourse> mapData = new HashMap<String, TeacherCourse>();
		
		Map<String, String> mapMonthDay = CrmUtil.getMonthDay(Integer.parseInt(year), Integer.parseInt(month));
		String firstDay = mapMonthDay.get("firstDay");
		String lastDay = mapMonthDay.get("lastDay");
		//1、得到当月的所有课表数据
		Map<String, TeacherCourse> mapMonthCourseData = handleDataForCourse(firstDay, lastDay, user);
		if(mapMonthCourseData!=null && !mapMonthCourseData.isEmpty()) {
			mapData.putAll(mapMonthCourseData);
		}
		
		//2、处理跨月度断层的日期课表数据
		Map<String, List<String>> mapAddShowTime = CrmUtil.getWeekOfDate(Integer.parseInt(year), Integer.parseInt(month));
		//2.1、得到月首断层的课表数据
		List<String> listFirstShowTime = mapAddShowTime.get("firstShowTime");
		if(listFirstShowTime!=null && listFirstShowTime.size()>0) {
			if(listFirstShowTime.size()==1) {
				firstDay = listFirstShowTime.get(0);
				lastDay = listFirstShowTime.get(0);
			}else {
				firstDay = listFirstShowTime.get(listFirstShowTime.size()-1);
				lastDay = listFirstShowTime.get(0);
			}
			Map<String, TeacherCourse> mapMonthFirstCourseData = handleDataForCourse(firstDay, lastDay, user);
			if(mapMonthFirstCourseData!=null && !mapMonthFirstCourseData.isEmpty()) {
				mapData.putAll(mapMonthFirstCourseData);
			}
		}
		//2.2、得到月尾断层的课表数据
		List<String> listLastShowTime = mapAddShowTime.get("lastShowTime");
		if(listLastShowTime!=null && listLastShowTime.size()>0) {
			if(listLastShowTime.size()==1) {
				firstDay = listLastShowTime.get(0);
				lastDay = listLastShowTime.get(0);
			}else {
				firstDay = listLastShowTime.get(0);
				lastDay = listLastShowTime.get(listLastShowTime.size()-1);
			}
			Map<String, TeacherCourse> mapMonthLastCourseData = handleDataForCourse(firstDay, lastDay, user);
			if(mapMonthLastCourseData!=null && !mapMonthLastCourseData.isEmpty()) {
				mapData.putAll(mapMonthLastCourseData);
			}
		}
		return mapData;
	}
	
	/**
	 * 处理得到指定区间内的月度课表数据
	 * @param firstDay 开始日期
	 * @param lastDay 结束日期
	 * @param user 用户信息
	 * @return
	 * @throws BusinessException 
	 * @throws DAOException 
	 */
	private Map<String, TeacherCourse> handleDataForCourse(String firstDay,String lastDay,UserVO user) throws BusinessException{
		try {
			Map<String, TeacherCourse> mapData = new HashMap<String, TeacherCourse>();
			//查询指定日期区间所有的课表
			String sql = "select *\n" +
						 "  from cy_classituation\n" + 
						 " where dr = 0\n" + 
						 "   and cyteacher = '"+user.getPk_psndoc()+"'\n" + 
						 "   and dbilldate >= '"+firstDay+"00:00:00'\n" + 
						 "   and dbilldate <= '"+lastDay+"23:59:59'";

			ClassituationHVO[] hvos = executeQueryVOs(sql, ClassituationHVO.class);
			if(hvos==null || hvos.length==0) {
				return null;
			}
			//查询指定日期期间上课的所有班级信息
		    sql = "select t.pk_situation,\n" +
				  "       t.def2 as pk_cy,\n" + 
				  "       cy_classcy.clasname as cyclasname,\n" + 
				  "       cy_classcy.bill_no as cyclassnum,\n" + 
				  "       t.def3 as pk_xz,\n" + 
				  "       cy_classxz.clasname as xzclasname,\n" + 
				  "       cy_classxz.bill_no as xzclassnum\n" + 
				  "  from (select pk_situation, def2, def3\n" + 
				  "          from cy_classituation_detail\n" + 
				  "         where pk_situation in\n" + 
				  "               (select pk_situation\n" + 
				  "                  from cy_classituation\n" + 
				  "                 where dr = 0\n" + 
				  "                   and cyteacher = '"+user.getPk_psndoc()+"'\n" + 
				  "                   and dbilldate >= '"+firstDay+"00:00:00'\n" + 
				  "                   and dbilldate <= '"+lastDay+"23:59:59')\n" + 
				  "         group by pk_situation, def2, def3) t\n" + 
				  "  join cy_classcy on t.def2 = cy_classcy.pk_classcy\n" + 
				  "  join cy_classxz on t.def3 = cy_classxz.pk_classxz";
		    
		    @SuppressWarnings({ "unchecked", "rawtypes" })
		    List<Map<String,Object>> mapList = (List) getBaseDao().executeQuery(sql, new MapListProcessor());
		    
			Map<String,RefPOJO> mapSchoolInfo = new HashMap<String,RefPOJO>();//学校信息<学校主键，学校信息>
			Map<String,String> mapSchoolAddress = new HashMap<String, String>();//学校信息<学校主键，学校地址>
			Map<String,List<RefPOJO>> mapCourseClassxzInfo = new HashMap<String,List<RefPOJO>>();//行政班级信息信息<课表主键，行政班级信息>
			Map<String,List<RefPOJO>> mapCourseClasscyInfo = new HashMap<String,List<RefPOJO>>();//词源班级信息<课表主键，词源班级信息>

			for(Map<String,Object> mapClass: mapList) {
				String pk_situation = (String) mapClass.get("pk_situation");
				
				List<RefPOJO> listClassxz =  mapCourseClassxzInfo.get(pk_situation);
				if(listClassxz==null) {
					listClassxz = new ArrayList<RefPOJO>();
				}
				if(mapClass.get("pk_xz")!=null) {
					String pk_xz = mapClass.get("pk_xz").toString();
					String xzclasname = mapClass.get("xzclasname").toString();
					String xzclassnum = mapClass.get("xzclassnum").toString();
					
					RefPOJO classxz = new RefPOJO();
					classxz.setValue(pk_xz);
					classxz.setLabel(xzclasname);
					classxz.setCode(xzclassnum);
					
					listClassxz.add(classxz);
				}
				mapCourseClassxzInfo.put(pk_situation, listClassxz);
				
				List<RefPOJO> listClasscy =  mapCourseClasscyInfo.get(pk_situation);
				if(listClasscy==null) {
					listClasscy = new ArrayList<RefPOJO>();
				}
				if(mapClass.get("pk_cy")!=null) {
					String pk_cy = mapClass.get("pk_cy").toString();
					String cyclasname = mapClass.get("cyclasname").toString();
					String cyclassnum = mapClass.get("cyclassnum").toString();
					
					RefPOJO classcy = new RefPOJO();
					classcy.setValue(pk_cy);
					classcy.setLabel(cyclasname);
					classcy.setCode(cyclassnum);
					
					listClasscy.add(classcy);
				}
				mapCourseClasscyInfo.put(pk_situation, listClasscy);
			}
			
			//1、分组统计每天课程详情
			Map<String,List<ClassituationHVO>> mapDayCourse = new HashMap<String, List<ClassituationHVO>>();//<日期,日期所有的课程安排>
			for(ClassituationHVO hvo : hvos) {
				String dbilldate = hvo.getDbilldate().toString().substring(0, 10);//日期
				List<ClassituationHVO> listData = mapDayCourse.get(dbilldate);
				if(listData == null) {
					listData = new ArrayList<ClassituationHVO>();
				}
				listData.add(hvo);
				mapDayCourse.put(dbilldate, listData);
				
				if(mapSchoolInfo.get(hvo.getDef1())==null) {
					String schoolQuerySql = "select * from cy_schoolBasics where pk_school = '"+hvo.getDef1()+"'";
					SchoolBasicsHVO schoolBasicsHVO = executeQueryVO(schoolQuerySql, SchoolBasicsHVO.class);
					
					RefPOJO school = new RefPOJO();
					school.setValue(schoolBasicsHVO.getPrimaryKey());
					school.setLabel(schoolBasicsHVO.getSname());
					school.setCode(schoolBasicsHVO.getBill_no());
					mapSchoolInfo.put(hvo.getDef1(), school);
					mapSchoolAddress.put(hvo.getDef1(), schoolBasicsHVO.getSaddress());
				}
			}
				
			//2、处理分组数据
			for(Map.Entry<String,List<ClassituationHVO>> map : mapDayCourse.entrySet()) {
				//TeacherCourse teacherCourse = new TeacherCourse();
				String day = map.getKey();
				
				List<ClassituationHVO> listHvo = map.getValue();
				if(listHvo!=null && listHvo.size()>0) {
					TeacherCourse teacherCourse = mapData.get(day);
					List<CourseDeatil> listCourseDeatil = null;
					if(teacherCourse == null) {
						teacherCourse = new TeacherCourse();
						listCourseDeatil = new ArrayList<CourseDeatil>();
					}else {
						listCourseDeatil = teacherCourse.getListCourseDeatil();
					}
					
					for(ClassituationHVO hvo:listHvo) {
						CourseDeatil courseDeatil = new CourseDeatil();
						String pk_situation = hvo.getPk_situation();
						
						List<RefPOJO> listrefxz = mapCourseClassxzInfo.get(pk_situation);
						if(listrefxz!=null) {
							courseDeatil.setClassesxz(listrefxz);//当前这节课上课行政班级
							StringBuilder strName = new StringBuilder();
							StringBuilder strNum = new StringBuilder();
							for(RefPOJO ref:listrefxz) {
								strName.append(ref.getLabel()).append("、");
								strNum.append(ref.getCode()).append("、");
							}
							courseDeatil.setClassescyname(strName.substring(0, strName.length()-1));
							courseDeatil.setClassescynum(strNum.substring(0, strNum.length()-1));
						}else {
							throw new BusinessException(day+" "+hvo.getClassbegintime()+"~"+hvo.getClassendtime()+"当前课程，没有维护上课行政班级");
						}
						
						List<RefPOJO> listrefcy = mapCourseClasscyInfo.get(pk_situation);
						if(listrefcy!=null) {
							courseDeatil.setClassescy(listrefcy);//当前这节课上课词源班级
							StringBuilder strName = new StringBuilder();
							StringBuilder strNum = new StringBuilder();
							for(RefPOJO ref:listrefcy) {
								strName.append(ref.getLabel()).append("、");
								strNum.append(ref.getCode()).append("、");
							}
							courseDeatil.setClassesxzname(strName.substring(0, strName.length()-1));
							courseDeatil.setClassesxznum(strNum.substring(0, strNum.length()-1));
						}else {
							throw new BusinessException(day+" "+hvo.getClassbegintime()+"~"+hvo.getClassendtime()+"当前该课程，没有维护上课词源班级");
						}
						
						RefPOJO school = mapSchoolInfo.get(hvo.getDef1());
						if(school!=null) {
							courseDeatil.setSchool(school);//当前这节课学校
						}
						courseDeatil.setStartTime(hvo.getClassbegintime());//当前这节课开始时间
						courseDeatil.setEndTime(hvo.getClassendtime());//当前这节课上课结束时间
						courseDeatil.setAddress(StringUtils.isBlank(hvo.getClassadress())?mapSchoolAddress.get(hvo.getDef1()):hvo.getClassadress());//当前这节课的上课地点（如果地点为空没有维护，则取学校的地点）
						courseDeatil.setCanGoClass(true);//当前这节课能否上课
						courseDeatil.setCanChangeClass(CrmUtil.theDataCompareToNow(day));//当前这节课能否换课
						courseDeatil.setPk_situation(pk_situation);

						//courseDeatil.setClassituationHVO(hvo);//当前这节课上课基本详细信息
						listCourseDeatil.add(courseDeatil);
					}
					teacherCourse.setCourseDate(day);
					//当天所有课时排序，后进行赋值
					Collections.sort(listCourseDeatil, new Comparator<CourseDeatil>() {
			            @Override
			            public int compare(CourseDeatil o1, CourseDeatil o2) {
			                //升序
			            	String o1value = o1.getStartTime();
			            	String o2value = o2.getStartTime();
			                return o1value.compareTo(o2value);
			            }
			        });
					teacherCourse.setListCourseDeatil(listCourseDeatil);
					mapData.put(day, teacherCourse);
				}
			}
			return mapData;
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
	
	@Override
	public CourseDeatil getCourseDeatilByPk(String pk) throws BusinessException {
		AbstractBill abstractBill = queryDeatil(pk, AggClassituationHVO.class);
		
		CourseDeatil courseDeatil = new CourseDeatil();
		if(abstractBill!=null) {
			AggClassituationHVO aggvo = (AggClassituationHVO) abstractBill;
			
			ClassituationHVO hvo = aggvo.getParentVO();
			ClassituationDetailVO[] bvos = (ClassituationDetailVO[]) aggvo.getChildren(ClassituationDetailVO.class);
			if(bvos==null || bvos.length==0 ) {
				throw new BusinessException("当前课程没有维护上课学生");
			}
			String day = hvo.getDbilldate().toString().substring(0, 10);//日期
			String schoolQuerySql = "select * from cy_schoolBasics where pk_school = '"+hvo.getDef1()+"'";
			SchoolBasicsHVO schoolBasicsHVO = executeQueryVO(schoolQuerySql, SchoolBasicsHVO.class);

			RefPOJO school = new RefPOJO();
			school.setValue(schoolBasicsHVO.getPrimaryKey());
			school.setLabel(schoolBasicsHVO.getSname());
			school.setCode(schoolBasicsHVO.getBill_no());
			courseDeatil.setCourseDate(day);//当前这节课上课日期
			courseDeatil.setSchool(school);//当前这节课学校
			courseDeatil.setStartTime(hvo.getClassbegintime());//当前这节课开始时间
			courseDeatil.setEndTime(hvo.getClassendtime());//当前这节课上课结束时间
			courseDeatil.setAddress(StringUtils.isBlank(hvo.getClassadress())?schoolBasicsHVO.getSaddress():hvo.getClassadress());//当前这节课的上课地点（如果地点为空没有维护，则取学校的地点）
			courseDeatil.setCanGoClass(true);//当前这节课能否上课
			courseDeatil.setCanChangeClass(CrmUtil.theDataCompareToNow(day));//当前这节课能否换课
			courseDeatil.setPk_situation(hvo.getPrimaryKey());
			courseDeatil.setNums(bvos.length);//当前这节课上课学生总人数
			
			//需要翻译的数据
			Set<String> setStudentPk = new HashSet<String>();
			Set<String> setClasscyPk = new HashSet<String>();
			Set<String> setClassxzPk = new HashSet<String>();
			Set<String> setDefdocPk = new HashSet<String>();
			for(ClassituationDetailVO bvo : bvos) {
				String def1 = bvo.getDef1();//上课学生
				if(!StringUtils.isBlank(def1)) {
					setStudentPk.add(def1);
				}
				String def2 = bvo.getDef2();//所属词源班级
				if(!StringUtils.isBlank(def2)) {
					setClasscyPk.add(def2);
				}
				String def3 = bvo.getDef3();//所属行政班级
				if(!StringUtils.isBlank(def3)) {
					setClassxzPk.add(def3);
				}
				String def4 = bvo.getDef4();//出勤情况
				if(!StringUtils.isBlank(def4)) {
					setDefdocPk.add(def4);
				}
				String def5 = bvo.getDef5();//作业完成情况
				if(!StringUtils.isBlank(def5)) {
					setDefdocPk.add(def5);
				}
				String def6 = bvo.getDef6();//课堂表现情况
				if(!StringUtils.isBlank(def6)) {
					setDefdocPk.add(def6);
				}
			}
			String querySql = null;
			Map<String,StudentHVO> mapStudent = new HashMap<String,StudentHVO>();//学生翻译数据
			if(setStudentPk.size()>0) {
				StringBuilder whereStr = new StringBuilder();
				for(String pk_id:setStudentPk) {
					whereStr.append("'").append(pk_id).append("',");
				}
				querySql = "select * from cy_student where pk_student in ("+whereStr.substring(0, whereStr.length()-1).toString()+")";
				StudentHVO[] studentHVOs = executeQueryVOs(querySql, StudentHVO.class);
				for(StudentHVO studentHVO : studentHVOs) {
					mapStudent.put(studentHVO.getPrimaryKey(), studentHVO);
				}
			}
			Map<String,ClasscyHVO> mapClasscy = new HashMap<String,ClasscyHVO>();//词源班级翻译数据
			if(setClasscyPk.size()>0) {
				StringBuilder whereStr = new StringBuilder();
				for(String pk_id:setClasscyPk) {
					whereStr.append("'").append(pk_id).append("',");
				}
				querySql = "select * from cy_classcy where pk_classcy in ("+whereStr.substring(0, whereStr.length()-1).toString()+")";
				ClasscyHVO[] classcyHVOs = executeQueryVOs(querySql, ClasscyHVO.class);
				
				List<RefPOJO> listRefCy = new ArrayList<RefPOJO>();
				for(ClasscyHVO classcyHVO : classcyHVOs) {
					mapClasscy.put(classcyHVO.getPrimaryKey(), classcyHVO);
					
					RefPOJO classCy = new RefPOJO();
					classCy.setLabel(classcyHVO.getClasname());
					classCy.setCode(classcyHVO.getBill_no());
					classCy.setValue(classcyHVO.getPrimaryKey());
					listRefCy.add(classCy);
				}
				
				if(listRefCy!=null) {
					courseDeatil.setClassescy(listRefCy);//当前这节课上课词源班级
					StringBuilder strName = new StringBuilder();
					StringBuilder strNum = new StringBuilder();
					for(RefPOJO ref:listRefCy) {
						strName.append(ref.getLabel()).append("、");
						strNum.append(ref.getCode()).append("、");
					}
					courseDeatil.setClassescyname(strName.substring(0, strName.length()-1));
					courseDeatil.setClassescynum(strNum.substring(0, strNum.length()-1));
				}
			}
			
			Map<String,ClassxzHVO> mapClassxz = new HashMap<String,ClassxzHVO>();//行政班级翻译数据
			if(setClassxzPk.size()>0) {
				StringBuilder whereStr = new StringBuilder();
				for(String pk_id:setClassxzPk) {
					whereStr.append("'").append(pk_id).append("',");
				}
				querySql = "select * from cy_classxz where pk_classxz in ("+whereStr.substring(0, whereStr.length()-1).toString()+")";
				ClassxzHVO[] classxzHVOs = executeQueryVOs(querySql, ClassxzHVO.class);
				
				List<RefPOJO> listRefXz = new ArrayList<RefPOJO>();
				for(ClassxzHVO classxzHVO : classxzHVOs) {
					mapClassxz.put(classxzHVO.getPrimaryKey(), classxzHVO);
					
					RefPOJO classXz = new RefPOJO();
					classXz.setLabel(classxzHVO.getClasname());
					classXz.setCode(classxzHVO.getBill_no());
					classXz.setValue(classxzHVO.getPrimaryKey());
					listRefXz.add(classXz);
				}
				
				if(listRefXz!=null) {
					courseDeatil.setClassesxz(listRefXz);//当前这节课上课行政班级
					StringBuilder strName = new StringBuilder();
					StringBuilder strNum = new StringBuilder();
					for(RefPOJO ref:listRefXz) {
						strName.append(ref.getLabel()).append("、");
						strNum.append(ref.getCode()).append("、");
					}
					courseDeatil.setClassesxzname(strName.substring(0, strName.length()-1));
					courseDeatil.setClassesxznum(strNum.substring(0, strNum.length()-1));
				}
			}
			
			Map<String,DefdocVO> mapDefdoc = new HashMap<String,DefdocVO>();//自定义档案翻译数据
			if(setDefdocPk.size()>0) {
				StringBuilder whereStr = new StringBuilder();
				for(String pk_id:setDefdocPk) {
					whereStr.append("'").append(pk_id).append("',");
				}
				querySql = "select * from bd_defdoc where pk_defdoc in ("+whereStr.substring(0, whereStr.length()-1).toString()+")";
				DefdocVO[] defdocVOs = executeQueryVOs(querySql, DefdocVO.class);
				for(DefdocVO defdocVO : defdocVOs) {
					mapDefdoc.put(defdocVO.getPrimaryKey(), defdocVO);
				}
			}
			
			//组装上课学生信息
			List<StudentClassSituation> listBvo = new ArrayList<StudentClassSituation>();
			for(ClassituationDetailVO classituationDetailVO : bvos) {
				StudentClassSituation studentClassSituation = new StudentClassSituation();
				//studentClassSituation.setClassituationDetailVO(classituationDetailVO);
				studentClassSituation.setPk_detail(classituationDetailVO.getPrimaryKey());
				
				String def1 = classituationDetailVO.getDef1();//上课学生
				if(!StringUtils.isBlank(def1)) {
					StudentHVO studentHVO = mapStudent.get(def1);
					RefPOJO student = new RefPOJO();
					student.setLabel(studentHVO.getSname());
					student.setCode(studentHVO.getBill_no());
					student.setValue(studentHVO.getPrimaryKey());
					studentClassSituation.setDef1(student);
					
					String idcard = studentHVO.getIdcard();
					String str = null;
					if(idcard.length()<4) {
						//有些学生身份证数据没有校验就录到数据库中了（辣鸡业务功能），故此处处理下
						str = idcard;
					}else {
						str = idcard.substring(0, 2)+"**"+idcard.substring(idcard.length()-4, idcard.length());
					}
					studentClassSituation.setIdCard(str);//身份证号简写
					studentClassSituation.setBill_no(studentHVO.getBill_no());
				}
				
				String def2 = classituationDetailVO.getDef2();//所属词源班级
				if(!StringUtils.isBlank(def2)) {
					ClasscyHVO classcyHVO = mapClasscy.get(def2);
					RefPOJO classCy = new RefPOJO();
					classCy.setLabel(classcyHVO.getClasname());
					classCy.setCode(classcyHVO.getBill_no());
					classCy.setValue(classcyHVO.getPrimaryKey());
					studentClassSituation.setDef2(classCy);
				}
				
				String def3 = classituationDetailVO.getDef3();//所属行政班级
				if(!StringUtils.isBlank(def3)) {
					ClassxzHVO classxzHVO = mapClassxz.get(def3);
					RefPOJO classXz = new RefPOJO();
					classXz.setLabel(classxzHVO.getClasname());
					classXz.setCode(classxzHVO.getBill_no());
					classXz.setValue(classxzHVO.getPrimaryKey());
					studentClassSituation.setDef3(classXz);
				}
				
				String def4 = classituationDetailVO.getDef4();//出勤情况
				if(!StringUtils.isBlank(def4)) {
					DefdocVO defdocVO = mapDefdoc.get(def4);
					RefPOJO defdoc = new RefPOJO();
					defdoc.setLabel(defdocVO.getName());
					defdoc.setCode(defdocVO.getCode());
					defdoc.setValue(defdocVO.getPrimaryKey());
					studentClassSituation.setDef4(defdoc);
				}
				
				String def5 = classituationDetailVO.getDef5();//作业完成情况
				if(!StringUtils.isBlank(def5)) {
					DefdocVO defdocVO = mapDefdoc.get(def5);
					RefPOJO defdoc = new RefPOJO();
					defdoc.setLabel(defdocVO.getName());
					defdoc.setCode(defdocVO.getCode());
					defdoc.setValue(defdocVO.getPrimaryKey());
					studentClassSituation.setDef5(defdoc);
				}
				
				String def6 = classituationDetailVO.getDef6();//课堂表现情况
				if(!StringUtils.isBlank(def6)) {
					DefdocVO defdocVO = mapDefdoc.get(def6);
					RefPOJO defdoc = new RefPOJO();
					defdoc.setLabel(defdocVO.getName());
					defdoc.setCode(defdocVO.getCode());
					defdoc.setValue(defdocVO.getPrimaryKey());
					studentClassSituation.setDef6(defdoc);
				}
				
				listBvo.add(studentClassSituation);
			}

			courseDeatil.setCourseStudents(listBvo);
			return courseDeatil;
		}
		return null;
	}
	
	@Override
	public Map<String,Object> updateStudentAttence(CourseStudentSave courseStudentSave) throws BusinessException {
		if (StringUtils.isBlank(courseStudentSave.getPk_detail()) ||
				(StringUtils.isBlank(courseStudentSave.getDef4()) && StringUtils.isBlank(courseStudentSave.getDef5()) && StringUtils.isBlank(courseStudentSave.getDef6()))) {
				throw new BusinessException("更改学生考勤信息,参数不能为空,请检查！");
		}
		StringBuilder sqlStr = new StringBuilder();
		StringBuilder zdypkStr = new StringBuilder();
		String def4 = courseStudentSave.getDef4();//出勤情况pk
		if(!StringUtils.isBlank(def4)) {
			sqlStr.append("def4").append("=").append("'").append(def4).append("',");
			zdypkStr.append("'").append(def4).append("',");
		}
		String def5 = courseStudentSave.getDef5();//作业完成情况pk
		if(!StringUtils.isBlank(def5)) {
			sqlStr.append("def5").append("=").append("'").append(def5).append("',");
			zdypkStr.append("'").append(def5).append("',");
		}
		String def6 = courseStudentSave.getDef6();//课堂表现情况pk
		if(!StringUtils.isBlank(def6)) {
			sqlStr.append("def6").append("=").append("'").append(def6).append("',");
			zdypkStr.append("'").append(def6).append("',");
		}
		
		//执行更新Sql语句
		String pk_detail = courseStudentSave.getPk_detail();//学生情况明细主键
		//原回写
//		String updateSql = "update cy_classituation_Detail set "+sqlStr.substring(0, sqlStr.length()-1)+" where pk_detail = '"+pk_detail+"'";
		//新回写  给def7添加考勤回写的标识 1表示老师已维护考勤信息 
		String updateSql = "update cy_classituation_Detail set "+sqlStr.substring(0, sqlStr.length()-1)+" ,def7 = '1' where pk_detail = '"+pk_detail+"'";
				
		
		int num = getBaseDao().executeUpdate(updateSql);
		
		//更新成功,组装返回的参数
		if(num > 0) {
			String querySql = "select * from bd_defdoc where pk_defdoc in ("+zdypkStr.substring(0, zdypkStr.length()-1)+")";
			DefdocVO[] defdocVOs = executeQueryVOs(querySql, DefdocVO.class);
			Map<String,DefdocVO> mapDefdoc = new HashMap<String, DefdocVO>();
			for(DefdocVO defdocVO : defdocVOs) {
				mapDefdoc.put(defdocVO.getPrimaryKey(), defdocVO);
			}
		
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("pk_detail", pk_detail);
			if(!StringUtils.isBlank(def4)) {
				DefdocVO defvo = mapDefdoc.get(def4);
				RefPOJO ref = new RefPOJO();
				ref.setLabel(defvo.getName());
				ref.setCode(defvo.getCode());
				ref.setValue(defvo.getPrimaryKey());
				
				map.put("def4", ref);
			}
			if(!StringUtils.isBlank(def5)) {
				DefdocVO defvo = mapDefdoc.get(def5);
				RefPOJO ref = new RefPOJO();
				ref.setLabel(defvo.getName());
				ref.setCode(defvo.getCode());
				ref.setValue(defvo.getPrimaryKey());
				
				map.put("def5", ref);
			}
			if(!StringUtils.isBlank(def6)) {
				DefdocVO defvo = mapDefdoc.get(def6);
				RefPOJO ref = new RefPOJO();
				ref.setLabel(defvo.getName());
				ref.setCode(defvo.getCode());
				ref.setValue(defvo.getPrimaryKey());
				
				map.put("def6", ref);
			}
			return map;
		}
		
		return null;
	}

	@Override
	public void courseAddStudent(CourseStudentSave courseStudentSave) throws BusinessException {
		if (StringUtils.isBlank(courseStudentSave.getPk_situation()) || 
				courseStudentSave.getListStudentPk()==null || 
				courseStudentSave.getListStudentPk().size()==0) {
				throw new BusinessException("添加上课学生，参数不能为空，请检查！");
		}
		
		String pk_situation = courseStudentSave.getPk_situation();
		AbstractBill abstractBill = queryDeatil(pk_situation, AggClassituationHVO.class);
		if(abstractBill==null) {
			throw new BusinessException("主键不存在，请检查！");
		}
		
		AggClassituationHVO aggClassituationHVO = (AggClassituationHVO) abstractBill;
		ClassituationDetailVO[] bvos = (ClassituationDetailVO[]) aggClassituationHVO.getChildren(ClassituationDetailVO.class);
		
		//过滤已经存在的学生，添加不存在的学生
		List<String> listpk = new ArrayList<String>();
		List<String> list = courseStudentSave.getListStudentPk();
		StringBuilder stuPkStr = new StringBuilder();
		for(String pk : list) {
			boolean isExis = false;
			for(ClassituationDetailVO classituationDetailVO : bvos) {
				if(pk.equals(classituationDetailVO.getDef1())) {
					isExis = true;
					break;
				}
			}
			if(!isExis) {
				listpk.add(pk);
				stuPkStr.append("'").append(pk).append("',");
			}
		}
		if(listpk.size()==0) {
			throw new BusinessException("当前课堂需要添加的学生已存在！请勿重复添加");
		}
		
		Map<String,StudentHVO> mapStudent = new HashMap<String,StudentHVO>();
		String querySql = "select * from cy_student where pk_student in ("+stuPkStr.substring(0, stuPkStr.length()-1)+")";
		StudentHVO[] studentHVOs = executeQueryVOs(querySql, StudentHVO.class);
		for(StudentHVO studentHVO : studentHVOs) {
			mapStudent.put(studentHVO.getPrimaryKey(), studentHVO);
		}
		
		//组装VO数据
		List<ClassituationDetailVO> listBvo = new ArrayList<ClassituationDetailVO>();
		for(String pk:listpk) {
			ClassituationDetailVO classituationDetailVO = new ClassituationDetailVO();
			classituationDetailVO.setPk_situation(pk_situation);//主表主键
			
			StudentHVO studentHVO = mapStudent.get(pk);
			classituationDetailVO.setDef1(studentHVO.getPrimaryKey());//学生pk
			classituationDetailVO.setDef2(studentHVO.getDef1());//词源班级pk
			classituationDetailVO.setDef3(studentHVO.getDef4());//行政班级pk
			classituationDetailVO.setDef4(AppletsParams.Cqqk);//出勤情况pk（默认正常）
			classituationDetailVO.setDef5(AppletsParams.Zywcqk);//作业完成情况pk（默认正常）
			classituationDetailVO.setDef6(AppletsParams.Ktbxqk);//课堂表现情况pk（默认正常）
			classituationDetailVO.setStatus(VOStatus.NEW);
			listBvo.add(classituationDetailVO);
		}
		//添加学生到库中
		VOInsert<ClassituationDetailVO> voInsert = new VOInsert<ClassituationDetailVO>();
		voInsert.insert(listBvo.toArray(new ClassituationDetailVO[listBvo.size()]));
	}
	
	@Override
	public List<StudentInfo> queryStudentByClasscy(List<String> listClasscyId) throws BusinessException {
		StringBuilder whereStr = new StringBuilder();
		for(String pk_classcy : listClasscyId) {
			whereStr.append("'").append(pk_classcy).append("',");
		}
		
		String querySql = "select * from cy_student where def1 in ("+whereStr.substring(0, whereStr.length()-1)+")";
		StudentHVO[] studentHVOs = executeQueryVOs(querySql, StudentHVO.class);
		if(studentHVOs!=null && studentHVOs.length>0) {
			List<StudentInfo> listStudentInfo = new ArrayList<StudentInfo>();
			
			Set<String> setClasscyPk = new HashSet<String>();
			Set<String> setClassxzPk = new HashSet<String>();
			Set<String> setSchoolPk = new HashSet<String>();
			for(StudentHVO studentHVO : studentHVOs) {
				if(!StringUtils.isBlank(studentHVO.getDef1())) {
					setClasscyPk.add(studentHVO.getDef1());
				}
				if(!StringUtils.isBlank(studentHVO.getDef4())) {
					setClassxzPk.add(studentHVO.getDef4());
				}
				if(!StringUtils.isBlank(studentHVO.getDef2())) {
					setSchoolPk.add(studentHVO.getDef2());
				}
			}
			Map<String,ClasscyHVO> mapClasscy = new HashMap<String,ClasscyHVO>();//词源班级
			if(setClasscyPk.size()>0) {
				whereStr = new StringBuilder();
				for(String pk_id:setClasscyPk) {
					whereStr.append("'").append(pk_id).append("',");
				}
				querySql = "select * from cy_classcy where pk_classcy in ("+whereStr.substring(0, whereStr.length()-1)+")";
				ClasscyHVO[] classcyHVOs = executeQueryVOs(querySql, ClasscyHVO.class);

				for(ClasscyHVO classcyHVO : classcyHVOs) {
					mapClasscy.put(classcyHVO.getPrimaryKey(), classcyHVO);
				}
			}
			Map<String,ClassxzHVO> mapClassxz = new HashMap<String,ClassxzHVO>();//行政班级
			if(setClassxzPk.size()>0) {
				whereStr = new StringBuilder();
				for(String pk_id:setClassxzPk) {
					whereStr.append("'").append(pk_id).append("',");
				}
				querySql = "select * from cy_classxz where pk_classxz in ("+whereStr.substring(0, whereStr.length()-1)+")";
				ClassxzHVO[] classxzHVOs = executeQueryVOs(querySql, ClassxzHVO.class);
				for(ClassxzHVO classxzHVO : classxzHVOs) {
					mapClassxz.put(classxzHVO.getPrimaryKey(), classxzHVO);
				}
			}
			Map<String,SchoolBasicsHVO> mapSchool = new HashMap<String,SchoolBasicsHVO>();//学校
			if(setSchoolPk.size()>0) {
				whereStr = new StringBuilder();
				for(String pk_id:setSchoolPk) {
					whereStr.append("'").append(pk_id).append("',");
				}
				querySql = "select * from cy_schoolBasics where pk_school in ("+whereStr.substring(0, whereStr.length()-1)+")";
				SchoolBasicsHVO[] schoolBasicsHVOs = executeQueryVOs(querySql, SchoolBasicsHVO.class);
				for(SchoolBasicsHVO schoolBasicsHVO : schoolBasicsHVOs) {
					mapSchool.put(schoolBasicsHVO.getPrimaryKey(), schoolBasicsHVO);
				}
			}
			
			for(StudentHVO studentHVO : studentHVOs) {
				StudentInfo studentInfo = new StudentInfo();
				studentInfo.setPk_student(studentHVO.getPrimaryKey());
				studentInfo.setBill_no(studentHVO.getBill_no());
				studentInfo.setName(studentHVO.getSname());
				studentInfo.setIdcard(studentHVO.getIdcard());
				
				if(!StringUtils.isBlank(studentHVO.getDef1())) {
					ClasscyHVO classcyHVO = mapClasscy.get(studentHVO.getDef1());
					//词源班级
					RefPOJO classCy = new RefPOJO();
					classCy.setLabel(classcyHVO.getClasname());
					classCy.setCode(classcyHVO.getBill_no());
					classCy.setValue(classcyHVO.getPrimaryKey());
					
					studentInfo.setClasscy(classCy);
				}
				if(!StringUtils.isBlank(studentHVO.getDef4())) {
					ClassxzHVO classxzHVO = mapClassxz.get(studentHVO.getDef4());
					//词源班级
					RefPOJO classXz = new RefPOJO();
					classXz.setLabel(classxzHVO.getClasname());
					classXz.setCode(classxzHVO.getBill_no());
					classXz.setValue(classxzHVO.getPrimaryKey());
					
					studentInfo.setClassxz(classXz);
				}
				if(!StringUtils.isBlank(studentHVO.getDef2())) {
					SchoolBasicsHVO schoolBasicsHVO = mapSchool.get(studentHVO.getDef2());
					//学校
					RefPOJO school = new RefPOJO();
					school.setLabel(schoolBasicsHVO.getSname());
					school.setCode(schoolBasicsHVO.getBill_no());
					school.setValue(schoolBasicsHVO.getPrimaryKey());
					
					studentInfo.setSchool(school);
				}
				listStudentInfo.add(studentInfo);
			}
			return listStudentInfo;
		}
		return null;
	}

	/**
	  * 校验单据能都修改
	 * 
	 * @param pk
	 * @param aggClazz
	 * @return
	 */
	@SuppressWarnings({ "all" })
	private static void checkBillIsEdit(AbstractBill aggvo) throws BusinessException {
		String billstatus = aggvo.getParentVO().getAttributeValue("billstatus").toString();

		// -1自由态和3提交态的单据可以修改，其他单据状态均不能修改
		if (!billstatus.equals("-1") && !billstatus.equals("3")) {
			throw new BusinessException("单据已被审批，不能修改");
		}

	}

	/**
	 * 总条数
	 * 
	 * @param condition 条件
	 * @param tablename 表名
	 * @return
	 */
	private int queryCount(String condition, String tablename) {
		String sql = "select count(0) from " + tablename + " where dr = 0 ";
		if (!StringUtils.isBlank(condition)) {
			// 添加条件
			sql = sql + " and " + condition;
		}

		try {
			return (int) getBaseDao().executeQuery(sql, new ColumnProcessor());
		} catch (DAOException e) {
			ExceptionUtils.wrappException(e);
		}
		return 0;
	}

	/**
	 * 总页数
	 * 
	 * @param pageSize 每页条数
	 * @param length   总条数
	 * @return
	 */
	private int getTotalPage(int pageSize, int length) {
		int size = pageSize;
		int total = 0;
		if ((length % size) == 0) {
			total = length / size;
		} else {
			total = (length / size) + 1;
		}
		return total;
	}

	/**
	 * 查询数据(单条)
	 * 
	 * @param <T>
	 * @param sql     SQL语句
	 * @param voClass 查询类型
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings({ "all" })
	private static <T> T executeQueryVO(String sql, Class<T> voClass) throws DAOException {
		return (T) getBaseDao().executeQuery(sql, new BeanProcessor(voClass));
	}

	/**
	 * 查询数据(多条)
	 * 
	 * @param <T>
	 * @param sql
	 * @param voClass
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings({ "all" })
	public <T> T[] executeQueryVOs(String sql, Class<T> voClass) throws DAOException {
		List<T> list = (List) getBaseDao().executeQuery(sql, new BeanListProcessor(voClass));
		if ((list == null) || (list.size() == 0)) {
			return (T[]) Array.newInstance(voClass, 0);
		}

		return (T[]) list.toArray((Object[]) Array.newInstance(voClass, list.size()));
	}

	/**
	 * 动作脚本接口
	 * 
	 * @return
	 */
	private static IPFBusiAction getPFBusiAction() {
		if (pfBusiAction == null)
			pfBusiAction = NCLocator.getInstance().lookup(IPFBusiAction.class);
		return pfBusiAction;
	}

	/**
	 * baseDAO
	 * 
	 * @return
	 */
	private static BaseDAO getBaseDao() {
		if (baseDao == null) {
			baseDao = new BaseDAO();
		}
		return baseDao;
	}

	/**
	 * aggvo查询支持
	 * 
	 * @return
	 */
	private static IMDPersistenceQueryService getMDPersistenceQueryService() {
		if (aggvoQueryService == null)
			aggvoQueryService = NCLocator.getInstance().lookup(IMDPersistenceQueryService.class);
		return aggvoQueryService;
	}

}
