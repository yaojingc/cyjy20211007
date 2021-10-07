package nc.api.cy.rest.entity.crm.basedoc.defdoc;

/**
 * @ClassName QueryPage
 * @Description TODO 
 * @Author NCC
 * @Date 2021/7/14 15:42
 * @Version 1.0
 **/
public class DefdocQuery{
	
	private String name;

	/**
	 * 自定义档案编码，格式为"defcode01,defcode02,defcode03,defcode04,defcode05"
	 */
	private String defdoclistcode;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefdoclistcode() {
		return defdoclistcode;
	}

	public void setDefdoclistcode(String defdoclistcode) {
		this.defdoclistcode = defdoclistcode;
	}
}
