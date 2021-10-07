package nc.api.cy.rest.entity.crm.refpojo;

/**
 * @ClassName School
 * @Description TODO 学校参照拓展实体数据
 * @Author NCC
 * @Date 2021/7/14 16:04
 * @Version 1.0
 **/
public class School extends RefPOJO {

    /**
         * 学校所属区域
     */
    private String schoolregion;

    /**
          * 学校具体地址
     */
    private String schooladdress;

	public String getSchoolregion() {
		return schoolregion;
	}

	public void setSchoolregion(String schoolregion) {
		this.schoolregion = schoolregion;
	}

	public String getSchooladdress() {
		return schooladdress;
	}

	public void setSchooladdress(String schooladdress) {
		this.schooladdress = schooladdress;
	}
    
    

}
