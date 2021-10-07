package nc.api.cy.rest.itf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONString;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;

import nc.api.cy.rest.entity.crm.classfilecy.ClassAggPOJO;
import nc.api.cy.rest.entity.teach.course.CourseDeatil;
import nc.api.cy.rest.entity.teach.course.CourseStudentSave;
import nc.api.cy.rest.entity.teach.course.RestCourseQuery;
import nc.api.cy.rest.entity.teach.course.StudentInfo;
import nc.api.cy.rest.entity.teach.course.TeacherCourse;
import nc.bs.framework.common.NCLocator;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.classituation.AggClassituationHVO;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.sm.UserVO;
import nccloud.api.rest.utils.ResultMessageUtil;
import uap.ws.rest.resource.AbstractUAPRestResource;

/*
 * 课表信息
 */
@Path("cycourse")
public class CyCourseResource extends AbstractUAPRestResource  {

	@Override
	public String getModule() {
		CyCommonUtils.login();
		return "cy";
	}
	
	private ICrmService crmService;

	public ICrmService getCrmService() {
		if (crmService == null) {
			crmService = NCLocator.getInstance().lookup(ICrmService.class);
		}
		return crmService;
	}
	
	/*
	 * 根据老师和月份获得当月的上课课表信息
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("teacherCourse")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString teacherCourse(@RequestBody RestCourseQuery restCourseQuery) {
		try {
			UserVO user = getCrmService().getUserInfoByUserMobile(restCourseQuery.getUserinfo().getUserMobile());

			Map<String, TeacherCourse> mapTeacherCourse = getCrmService().getTeacherCourse(user, restCourseQuery.getYear(), restCourseQuery.getMonth());
			
			if(mapTeacherCourse!=null && !mapTeacherCourse.isEmpty()){
				Map treeMapDesc = new TreeMap(new Comparator<String> (){
		            @Override
		            public int compare(String o1, String o2) {
		                return o1.compareTo(o2);
		            }
				 });
				
				for(Map.Entry<String, TeacherCourse> mapdata : mapTeacherCourse.entrySet()) {
					treeMapDesc.put(mapdata.getKey(), mapdata.getValue());
				}
				return ResponsePOJO.toJSON(treeMapDesc, "200", "成功");
			}else {
				return ResponsePOJO.toJSON(null, "200", "当前老师不存在课表数据");
			}
		} catch (Exception e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}
	
	/**
	  * 根据主键查询课程详细信息
	 * @param pk_situation
	 * @return
	 */
	@POST
	@Path("queryCourseDeatil/{pk_situation}")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryCourseDeatil(@PathParam("pk_situation") String pk_situation) {
		try {
			if (StringUtils.isBlank(pk_situation)) {
				throw new BusinessException("主键不能为空");
			}
			
            CourseDeatil courseDeatil = getCrmService().getCourseDeatilByPk(pk_situation);
			
			return ResponsePOJO.toJSON(courseDeatil, "200", "成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}
	
	/**
	 * 更新学生考勤信息
	 * @param courseStudentSave
	 * @return
	 */
	@POST
	@Path("updateStudentAttence")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString updateStudentAttence(@RequestBody CourseStudentSave courseStudentSave) {
		//参数：pk_detail 学生情况明细主键、def4出勤情况主键、def5作业完成主键、def6课堂表现情况主键
		try {
			Map<String, Object> map = getCrmService().updateStudentAttence(courseStudentSave);
			
			return ResponsePOJO.toJSON(map, "200", "修改成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}
	
	/**
	 * 课堂添加上课学生
	 * @param Data
	 * @return
	 */
	@POST
	@Path("courseAddStudent")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString courseAddStudent(@RequestBody CourseStudentSave courseStudentSave) {
		//参数：pk_situation 上课情况主键、listStudentPk 添加的学生pk
		try {
			getCrmService().courseAddStudent(courseStudentSave);
			
			return ResponsePOJO.toJSON(null, "200", "添加成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}
	
	/**
	  * 根据词源班级主键查询班级下所有学生信息
	 * @param classcystrids 词源班级主键：格式 pk;pk;pk
	 * @return
	 */
	@POST
	@Path("seachStudentByClasscy/{classcystrids}")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString seachStudentByClasscy(@PathParam("classcystrids") String classcystrids) {
		try {			
			if(StringUtils.isBlank(classcystrids)) {
				throw new BusinessException("参数不能为空");
			}
			
			String[] classcypks = classcystrids.split(";");
			List<StudentInfo> listAAtudent = getCrmService().queryStudentByClasscy(new ArrayList<String>(Arrays.asList(classcypks)));
			
			if(listAAtudent!=null && listAAtudent.size()!=0) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("listAAtudent", listAAtudent);
				return ResponsePOJO.toJSON(map, "200", "成功");
			}else {
				return ResponsePOJO.toJSON(null, "200", "当前班级下不存在学生");
			}
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}
	
}
