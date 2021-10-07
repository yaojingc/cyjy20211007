package nc.api.cy.rest.itf;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONString;
import org.springframework.web.bind.annotation.RequestBody;
import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.plantravel.PlantravelAggPOJO;
import nc.api.cy.rest.entity.crm.plantravel.RestPlantravelQuery;
import nc.api.cy.rest.entity.crm.plantravel.RestPlantravelSave;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.pub.BusinessException;
import nccloud.itf.cy.cy.IPlantravelhvoMaintain;
import uap.ws.rest.resource.AbstractUAPRestResource;

/*@author huangcong
 * 行程安排接口
 */
@Path("cyplantravel")
public class CyPlantravelResource extends AbstractUAPRestResource {

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
	public JSONString queryList(@RequestBody RestPlantravelQuery restQuery) {
		try {
			ResponseList queryListData = CyCommonUtils.getInstance(IPlantravelhvoMaintain.class)
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
	@Path("queryDeatil/{pk_plantravel}")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryDeatil(@PathParam("pk_plantravel") String pk) {
		try {
			PlantravelAggPOJO result = CyCommonUtils.getInstance(IPlantravelhvoMaintain.class).queryDeatil(pk);
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
	public JSONString billSave(@RequestBody RestPlantravelSave restSave) {
		try {
			CyCommonUtils.getInstance(IPlantravelhvoMaintain.class).billSave(restSave);
			return ResponsePOJO.toJSON(null, "200", "保存成功");

		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}


}
