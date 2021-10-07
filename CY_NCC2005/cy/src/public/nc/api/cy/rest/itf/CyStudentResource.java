package nc.api.cy.rest.itf;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.poi.ss.formula.functions.T;
import org.json.JSONString;
import org.springframework.web.bind.annotation.RequestBody;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.cy.studentfile.pojo.StudentQueryPOJO;
import nccloud.itf.cy.cy.IStudenthvoMaintain;
import uap.ws.rest.resource.AbstractUAPRestResource;

@Path("cystudent")
public class CyStudentResource extends AbstractUAPRestResource {

	@Override
	public String getModule() {
		CyCommonUtils.login();
		return "cy";
	}


	/**
	 * Add By huangcong 根据查询条件查询学校档案列表态数据
	 * 
	 * @param stuquery 查询条件vo
	 * @param voClass  voClass 查询类型
	 * @param sql      查询语句
	 * @return
	 */

	@SuppressWarnings({ "unchecked" })
	@POST
	@Path("queryList")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryStuList(@RequestBody StudentQueryPOJO stuquery) {
		try {
			@SuppressWarnings("unchecked")
			ResponseList<T> resp=CyCommonUtils.getInstance(IStudenthvoMaintain.class).queryForApp(stuquery);
			return ResponsePOJO.toJSON(resp, "200", "成功");
		} catch (Exception e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

}
