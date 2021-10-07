package nccloud.cy.cy.util;

import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nccloud.itf.cy.cy.IClasscyhvoMaintain;
import nccloud.itf.cy.cy.IClassxzhvoMaintain;
import nccloud.itf.cy.cy.ISchoolbasicshvoMaintain;
import nccloud.itf.cy.cy.IStudenthvoMaintain;
import nccloud.itf.cy.cy.ITestpaperfilehvoMaintain;

/**
 * 自定义参照翻译
 * 
 * @author tanronggao
 *
 */
public class BaseDataQueryUtil {

	/**
	 * 学生主键名称对照查询
	 * 
	 * @param setstudent
	 * @return
	 */
	public static Map<String, String> studentQuery(Set<String> setstudent) {
		IStudenthvoMaintain studentMaintain = NCLocator.getInstance().lookup(IStudenthvoMaintain.class);
		Map<String, String> studentMap = studentMaintain.studentDataQuery(setstudent);
		return studentMap;
	}

	/**
	 * 班级主键名称对照查询
	 * 
	 * @param setclasscy
	 * @return
	 */
	public static Map<String, String> classcyQuery(Set<String> setclasscy) {
		IClasscyhvoMaintain classcyMaintain = NCLocator.getInstance().lookup(IClasscyhvoMaintain.class);
		Map<String, String> classcyMap = classcyMaintain.classcyDataQuery(setclasscy);
		return classcyMap;
	}

	/**
	 * 班级主键名称对照查询
	 * 
	 * @param setclasscy
	 * @return
	 */
	public static Map<String, String> classxzQuery(Set<String> setclassxz) {
		IClassxzhvoMaintain classxzMaintain = NCLocator.getInstance().lookup(IClassxzhvoMaintain.class);
		Map<String, String> classxzMap = classxzMaintain.classxzDataQuery(setclassxz);
		return classxzMap;
	}

	/**
	 * 学校主键名称对照查询
	 * 
	 * @param setschool
	 * @return
	 */
	public static Map<String, String> schoolQuery(Set<String> setschool) {
		ISchoolbasicshvoMaintain schoolMaintain = NCLocator.getInstance().lookup(ISchoolbasicshvoMaintain.class);
		Map<String, String> schoolMap = schoolMaintain.schoolDataQuery(setschool);
		return schoolMap;
	}

	/**
	 * 试卷主键名称对照查询
	 * 
	 * @param setpks
	 * @return
	 */
	public static Map<String, String> testPaperQuery(Set<String> setpks) {
		ITestpaperfilehvoMaintain testpapeMaintain = NCLocator.getInstance().lookup(ITestpaperfilehvoMaintain.class);
		Map<String, String> schoolMap = testpapeMaintain.testPaperDataQuery(setpks);
		return schoolMap;
	}
}
