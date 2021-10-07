package nc.api.cy.rest.entity.crm.teacherform;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;

/**
 * @ClassName RestTeacherQuery
 * @Description TODO 老师、讲师查询实体
 * @Author NCC
 * @Date 2021/7/14 17:20
 * @Version 1.0
 **/
public class RestTeacherQuery extends QueryPage {

    /**
         * 类型单据(0表示老师申请单、1表示讲师申请单)
     */
    private String billType;

    /**
         * 学校名称
     */
    private String sname;
    
	 /**
	     *是否分配
	 */
	private String def6;

	
    public String getDef6() {
		return def6;
	}

	public void setDef6(String def6) {
		this.def6 = def6;
	}

	/**
         * 申请日期
     */
    private String dateTime;

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
    
}
