package nc.api.cy.rest.entity.teach.course;

import java.util.List;

/**
 * @ClassName CourseStudentSave
 * @Description TODO 课堂学生考勤维护
 * @Author NCC
 * @Date 2021/9/2 10:30
 * @Version 1.0
 **/
public class CourseStudentSave {
	
	//==给当前这节课添加上课学生用的参数
	/**
	 * 上课情况主键
	 */
	public String pk_situation;
	
	/**
	 * 学生pk
	 */
	public List<String> listStudentPk;
	
	
	//====给维护学生考勤情况用的参数
	/**
	 * 学生情况明细主键
	 */
	public String pk_detail;
	
	/**
	 * 出勤情况pk
	 */
	public String def4;
	
	/**
	 * 作业完成pk
	 */
	public String def5;
	
	/**
	 * 课堂表现情况pk
	 */
	public String def6;

	public String getPk_situation() {
		return pk_situation;
	}

	public void setPk_situation(String pk_situation) {
		this.pk_situation = pk_situation;
	}

	public List<String> getListStudentPk() {
		return listStudentPk;
	}

	public void setListStudentPk(List<String> listStudentPk) {
		this.listStudentPk = listStudentPk;
	}

	public String getPk_detail() {
		return pk_detail;
	}

	public void setPk_detail(String pk_detail) {
		this.pk_detail = pk_detail;
	}

	public String getDef4() {
		return def4;
	}

	public void setDef4(String def4) {
		this.def4 = def4;
	}

	public String getDef5() {
		return def5;
	}

	public void setDef5(String def5) {
		this.def5 = def5;
	}

	public String getDef6() {
		return def6;
	}

	public void setDef6(String def6) {
		this.def6 = def6;
	}
}
