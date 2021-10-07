package nc.api.cy.rest.entity.teach.course;

import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
import nc.vo.cy.classituation.ClassituationDetailVO;

/**
 * @ClassName StudentClassSituation
 * @Description TODO 学生上课情况实体
 * @Author NCC
 * @Date 2021/8/24 10:30
 * @Version 1.0
 **/
public class StudentClassSituation {
	
	private String pk_detail;
	
	/**
	 * 当前课程学生的详细信息
	 */
	private ClassituationDetailVO classituationDetailVO;
	
	/**
	 * 学生信息
	 */
	private RefPOJO def1;
	
	/**
	 * 学生身份证号
	 */
	private String idCard;
	
	/**
	 * 学生编号
	 */
	private String bill_no;
	
	/**
	 * 词源班级
	 */
	private RefPOJO def2;
	
	/**
	 * 行政班级
	 */
	private RefPOJO def3;
	
	/**
	 * 出勤情况
	 */
	private RefPOJO def4;
	
	/**
	 * 作业完成情况
	 */
	private RefPOJO def5;
	
	/**
	 * 课堂表现情况
	 */
	private RefPOJO def6;

	public String getPk_detail() {
		return pk_detail;
	}

	public void setPk_detail(String pk_detail) {
		this.pk_detail = pk_detail;
	}

	public ClassituationDetailVO getClassituationDetailVO() {
		return classituationDetailVO;
	}

	public void setClassituationDetailVO(ClassituationDetailVO classituationDetailVO) {
		this.classituationDetailVO = classituationDetailVO;
	}

	public RefPOJO getDef1() {
		return def1;
	}

	public void setDef1(RefPOJO def1) {
		this.def1 = def1;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getBill_no() {
		return bill_no;
	}

	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}

	public RefPOJO getDef2() {
		return def2;
	}

	public void setDef2(RefPOJO def2) {
		this.def2 = def2;
	}

	public RefPOJO getDef3() {
		return def3;
	}

	public void setDef3(RefPOJO def3) {
		this.def3 = def3;
	}

	public RefPOJO getDef4() {
		return def4;
	}

	public void setDef4(RefPOJO def4) {
		this.def4 = def4;
	}

	public RefPOJO getDef5() {
		return def5;
	}

	public void setDef5(RefPOJO def5) {
		this.def5 = def5;
	}

	public RefPOJO getDef6() {
		return def6;
	}

	public void setDef6(RefPOJO def6) {
		this.def6 = def6;
	}
}
