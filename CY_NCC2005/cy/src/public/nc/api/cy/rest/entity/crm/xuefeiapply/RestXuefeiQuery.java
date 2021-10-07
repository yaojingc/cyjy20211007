package nc.api.cy.rest.entity.crm.xuefeiapply;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;
/**
 * @ClassName RestXuefeiQuery
 * @Description TODO 学费减免查询实体
 * @Author huangcong
 * @Date 2021/8/16 16:54
 * @Version 1.0
 **/
public class RestXuefeiQuery   extends QueryPage{
	/**
	 * 学校
	 */
	private String school;
	
	/**
	 * 学生
	 */
	private String student;
	
	/**
	 * 班级
	 */
	private String clas;

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public String getClas() {
		return clas;
	}

	public void setClas(String clas) {
		this.clas = clas;
	}
	
	/**
	 * 制单人
	 */
	private String billmaker;
	
	public String getBillmaker() {
		return billmaker;
	}

	public void setBillmaker(String billmaker) {
		this.billmaker = billmaker;
	}
}
