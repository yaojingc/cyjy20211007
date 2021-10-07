package nc.api.cy.rest.entity.teach.course;

import java.util.List;

/**
 * @ClassName RestCourseQuery
 * @Description TODO 当天课表汇总信息
 * @Author NCC
 * @Date 2021/8/24 10:30
 * @Version 1.0
 **/
public class TeacherCourse {
	
	/**
	 * 课表日期
	 */
	private String courseDate;
	
	/**
	 * 当前课表日期的详情课表信息
	 */
	private List<CourseDeatil> listCourseDeatil;

	public String getCourseDate() {
		return courseDate;
	}

	public void setCourseDate(String courseDate) {
		this.courseDate = courseDate;
	}

	public List<CourseDeatil> getListCourseDeatil() {
		return listCourseDeatil;
	}

	public void setListCourseDeatil(List<CourseDeatil> listCourseDeatil) {
		this.listCourseDeatil = listCourseDeatil;
	}
}
