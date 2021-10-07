package nc.api.cy.rest.itf;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONString;
import org.springframework.web.bind.annotation.RequestBody;
import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.jiedaiapply.JiedaiAggPOJO;
import nc.api.cy.rest.entity.crm.jiedaiapply.RestJiedaiQuery;
import nc.api.cy.rest.entity.crm.jiedaiapply.RestJiedaiSave;
import nc.api.cy.rest.entity.crm.yejiapply.RestYejiSave;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.pub.BusinessException;
import nccloud.itf.cy.cy.IJiedaiapplyhvoMaintain;
import nccloud.itf.cy.cy.IYejiapplyhvoMaintain;
import uap.ws.rest.resource.AbstractUAPRestResource;

/*@author huangcong
 * 客情接待申请单接口
 */
@Path("cyjd")
public class CyJiedaiResource extends AbstractUAPRestResource {

	@Override
	public String getModule() {
		CyCommonUtils.login();
		return "cy";
	}

	/*
	 * 客情接待申请单列表查询
	 */
	@POST
	@Path("queryList")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryList(@RequestBody RestJiedaiQuery restQuery) {
		try {

			ResponseList queryListData = CyCommonUtils.getInstance(IJiedaiapplyhvoMaintain.class)
					.queryListPage(restQuery);
			return ResponsePOJO.toJSON(queryListData, "200", "成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 客情接待申请单详情查询
	 */
	@POST
	@Path("queryDeatil/{pk_jiedaiapply}")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryDeatil(@PathParam("pk_jiedaiapply") String pk) {
		try {
			JiedaiAggPOJO result = CyCommonUtils.getInstance(IJiedaiapplyhvoMaintain.class).queryDeatil(pk);
			if (CyCommonUtils.isEmpty(result)) {
				return ResponsePOJO.toJSON(result, "400", "查询失败");
			}
			return ResponsePOJO.toJSON(result, "200", "成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 客情接待申请单更新
	 */
	@POST
	@Path("billSave")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString billSave(@RequestBody RestJiedaiSave restSave) {
		try {
			CyCommonUtils.getInstance(IJiedaiapplyhvoMaintain.class).billSave(restSave);
			return ResponsePOJO.toJSON(null, "200", "保存成功");

		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 客情接待申请单提交
	 */
	@POST
	@Path("commit")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString commit(@RequestBody RestJiedaiSave restSave) {
		try {
			CyCommonUtils.getInstance(IJiedaiapplyhvoMaintain.class).commit(restSave);
			return ResponsePOJO.toJSON(null, "200", "提交成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}
}
