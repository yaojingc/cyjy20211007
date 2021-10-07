package nc.api.cy.rest.itf;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.json.JSONString;
import org.springframework.web.bind.annotation.RequestBody;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.generator.IdGenerator;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.classfilecy.pojo.AggClassCyPOJO;
import nc.vo.cy.classfilecy.pojo.ClassCyQueryPOJO;
import nc.vo.cy.pojo.PsnQueryPOJO;
import nc.vo.cy.pojo.ResponsePOJO;
import nccloud.itf.cy.cy.IClasscyhvoMaintain;
import uap.ws.rest.resource.AbstractUAPRestResource;

/*
 * 营销APP基础数据参照
 */
@Path("cymkapp")
public class CyMkappResource extends AbstractUAPRestResource {

	@Override
	public String getModule() {
		CyCommonUtils.login();
		return "cy";
	}

	/*
	 * 人员数据查询
	 */
	@POST
	@Path("querypsndoc")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString QueryPsndoc(@RequestBody PsnQueryPOJO queryvo) {
		try {

		} catch (Exception e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
		return null;
	}

	/*
	 * 基础档案查询
	 */
	@POST
	@Path("querybasicfile")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString QueryBasicFile(@RequestBody AggClassCyPOJO aggpojo) {
		try {

		} catch (Exception e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
		return null;
	}

	/*
	 * 菜单权限查询
	 */
	@POST
	@Path("menupermissions")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString MenuPermissions(@RequestBody ClassCyQueryPOJO queryvo) {
		try {

		} catch (Exception e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
		return null;
	}

	private IClasscyhvoMaintain service;

	public IClasscyhvoMaintain getService() {
		if (service == null) {
			service = NCLocator.getInstance().lookup(IClasscyhvoMaintain.class);
		}
		return service;
	}

	private IUAPQueryBS iQueryBS;

	public IUAPQueryBS getIQueryBS() {
		if (iQueryBS == null) {
			iQueryBS = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		}
		return iQueryBS;
	}



}
