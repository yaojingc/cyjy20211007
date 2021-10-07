package nc.api.cy.rest.entity.crm.refpojo;

/**
 * @ClassName Student
 * @Description TODO 学生档案参照拓展实体数据
 * @Author NCC
 * @Date 2021/8/16 14:04
 * @Version 1.0
 **/
public class Student extends RefPOJO  {
	
	/**
	 * 身份证号
	 */
	private String idcard;

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
}
