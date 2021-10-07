package nc.api.cy.rest.itf;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONString;
import org.springframework.web.bind.annotation.RequestBody;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.api.cy.rest.entity.crm.daily.DailyAggPOJO;
import nc.api.cy.rest.entity.crm.daily.RestDailyQuery;
import nc.api.cy.rest.entity.crm.daily.RestDailySave;
import nc.api.cy.rest.entity.crm.workplan.RestQueryDetail;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.daily.DailyTaskVO;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.pub.BusinessException;
import nccloud.itf.cy.cy.IDailyhvoMaintain;
import nccloud.itf.cy.cy.IWorkplanhvoMaintain;
import uap.ws.rest.resource.AbstractUAPRestResource;

/*Add By huangcong 
 * 工作日报接口
 */
@Path("cydaily")
public class CyDailyResource extends AbstractUAPRestResource {

	@Override
	public String getModule() {
		CyCommonUtils.login();
		return "cy";
	}

	/*
	 * 工作日报列表查询
	 */
	@POST
	@Path("queryList")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryList(@RequestBody RestDailyQuery restQuery) {
		try {
			ResponseList queryListData = CyCommonUtils.getInstance(IDailyhvoMaintain.class).queryListPage(restQuery);
			return ResponsePOJO.toJSON(queryListData, "200", "成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 工作日报详情查询
	 */
	@POST
	@Path("queryDeatil/{pk_daily}")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryDeatil(@PathParam("pk_daily") String pk) {
		try {
			DailyAggPOJO result = CyCommonUtils.getInstance(IDailyhvoMaintain.class).queryDeatil(pk);
			// 3、返回响应结果
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
	 * 工作日报更新
	 */
	@POST
	@Path("billSave")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString billSave(@RequestBody RestDailySave restSave) {
		try {
			CyCommonUtils.getInstance(IDailyhvoMaintain.class).billSave(restSave);
			return ResponsePOJO.toJSON(null, "200", "保存成功");

		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	@POST
	@Path("selectDes")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString selectDes(@RequestBody RestQueryDetail restDailyQuery) {
		try {
			UserInfoByDiworkPOJO userinfo = restDailyQuery.getUserinfo();
			// 查询改日期当天和第二天工作计划的工作内容
			DailyTaskVO hvo = CyCommonUtils.getInstance(IWorkplanhvoMaintain.class).queryDesByPk(restDailyQuery);
			if (hvo != null) {
				return ResponsePOJO.toJSON(hvo, "200", "查询成功");

			} else {
				return ResponsePOJO.toJSON(null, "400", "查询失败");
			}
		} catch (Exception e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}
}
