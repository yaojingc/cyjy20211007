package nc.api.cy.rest.itf;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONString;
import org.springframework.web.bind.annotation.RequestBody;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.schoolform.RestSchoolFindSave;
import nc.api.cy.rest.entity.crm.workplan.RestWorkPlanQuery;
import nc.api.cy.rest.entity.crm.workplan.RestWorkPlanSave;
import nc.api.cy.rest.entity.crm.workplan.WorkPlanAggPOJO;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.pub.BusinessException;
import nccloud.itf.cy.cy.ISchoolformhvoMaintain;
import nccloud.itf.cy.cy.IWorkplanhvoMaintain;
import uap.ws.rest.resource.AbstractUAPRestResource;

/*@author huangcong
 * 工作计划接口
 */
@Path("cyworkplan")
public class CyWorkplanResource extends AbstractUAPRestResource {

	@Override
	public String getModule() {
		CyCommonUtils.login();
		return "cy";
	}

	/*
	 * 工作计划列表查询
	 */
	@POST
	@Path("queryList")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryList(@RequestBody RestWorkPlanQuery restWorkPlan) {
		try {
			ResponseList queryListData = CyCommonUtils.getInstance(IWorkplanhvoMaintain.class)
					.queryListPage(restWorkPlan);
			// 3、返回响应结果
			return ResponsePOJO.toJSON(queryListData, "200", "成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 工作计划详情查询
	 */
	@POST
	@Path("queryDeatil/{pk_workplan}")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryDeatil(@PathParam("pk_workplan") String pk) {
		try {
			WorkPlanAggPOJO result = CyCommonUtils.getInstance(IWorkplanhvoMaintain.class).queryDeatil(pk);
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
	 * 工作计划更新
	 */
	@POST
	@Path("billSave")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString billSave(@RequestBody RestWorkPlanSave restWorkPlan) {
		try {
			CyCommonUtils.getInstance(IWorkplanhvoMaintain.class).billSave(restWorkPlan);
			return ResponsePOJO.toJSON(null, "200", "保存成功");

		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}
	/*
	 * 工作计划提交
	 */
	@POST
	@Path("commit")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString commit(@RequestBody RestWorkPlanSave restSave) {
		try {
			CyCommonUtils.getInstance(IWorkplanhvoMaintain.class).commit(restSave);
			return ResponsePOJO.toJSON(null, "200", "提交成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}
}
