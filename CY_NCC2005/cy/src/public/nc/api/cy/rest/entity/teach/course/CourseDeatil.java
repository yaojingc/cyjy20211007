package nc.api.cy.rest.entity.teach.course;

import java.util.List;

import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
import nc.vo.cy.classituation.ClassituationHVO;

/**
 * @ClassName CourseDeatil
 * @Description TODO 课表基本信息
 * @Author NCC
 * @Date 2021/8/24 10:30
 * @Version 1.0
 **/
public class CourseDeatil {
	
	/**
	 * 课程日期
	 */
	private String courseDate;
	
	/**
	 * 学校
	 */
	private RefPOJO school;
	
	/**
	 * 上课词源班级
	 */
	private List<RefPOJO> classescy;
	
	/**
	 * 上课词源班级名称
	 */
	private String classescyname;
	
	/**
	 * 上课词源班级班级号
	 */
	private String classescynum;
	
	/**
	 * 上课行政班级
	 */
	private List<RefPOJO> classesxz;
	
	/**
	 * 上课行政班级名称
	 */
	private String classesxzname;
	
	/**
	 * 上课行政班级班级号
	 */
	private String classesxznum;
	
	/**
	 * 上课开始时间
	 */
	private String startTime;
	
	/**
	 * 上课结束时间
	 */
	private String endTime;
	
	/**
	 * 上课地点
	 */
	private String address;
	
	/**
	 * 当前该节课的数据主键
	 */
	private String pk_situation;
	
	/**
	 * 当前课程基本详情信息
	 */
	private ClassituationHVO classituationHVO;
	
	/**
	 * 是否能上课
	 */
	private boolean isCanGoClass;
	
	/**
	 * 是否能换课
	 */
	private boolean isCanChangeClass;
	
	/**
	 * 课堂学生上课情况
	 */
	private List<StudentClassSituation> courseStudents;
	
	/**
	 * 课堂上课学生总人数
	 */
	private int nums;

	public String getCourseDate() {
		return courseDate;
	}

	public void setCourseDate(String courseDate) {
		this.courseDate = courseDate;
	}

	public RefPOJO getSchool() {
		return school;
	}

	public void setSchool(RefPOJO school) {
		this.school = school;
	}

	public List<RefPOJO> getClassescy() {
		return classescy;
	}

	public void setClassescy(List<RefPOJO> classescy) {
		this.classescy = classescy;
	}

	public String getClassescyname() {
		return classescyname;
	}

	public void setClassescyname(String classescyname) {
		this.classescyname = classescyname;
	}

	public String getClassescynum() {
		return classescynum;
	}

	public void setClassescynum(String classescynum) {
		this.classescynum = classescynum;
	}

	public List<RefPOJO> getClassesxz() {
		return classesxz;
	}

	public void setClassesxz(List<RefPOJO> classesxz) {
		this.classesxz = classesxz;
	}

	public String getClassesxzname() {
		return classesxzname;
	}

	public void setClassesxzname(String classesxzname) {
		this.classesxzname = classesxzname;
	}

	public String getClassesxznum() {
		return classesxznum;
	}

	public void setClassesxznum(String classesxznum) {
		this.classesxznum = classesxznum;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPk_situation() {
		return pk_situation;
	}

	public void setPk_situation(String pk_situation) {
		this.pk_situation = pk_situation;
	}

	public ClassituationHVO getClassituationHVO() {
		return classituationHVO;
	}

	public void setClassituationHVO(ClassituationHVO classituationHVO) {
		this.classituationHVO = classituationHVO;
	}

	public boolean isCanGoClass() {
		return isCanGoClass;
	}

	public void setCanGoClass(boolean isCanGoClass) {
		this.isCanGoClass = isCanGoClass;
	}

	public boolean isCanChangeClass() {
		return isCanChangeClass;
	}

	public void setCanChangeClass(boolean isCanChangeClass) {
		this.isCanChangeClass = isCanChangeClass;
	}

	public List<StudentClassSituation> getCourseStudents() {
		return courseStudents;
	}

	public void setCourseStudents(List<StudentClassSituation> courseStudents) {
		this.courseStudents = courseStudents;
	}

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}
}
