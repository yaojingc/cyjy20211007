package nc.api.cy.rest.itf;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONString;
import org.springframework.web.bind.annotation.RequestBody;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.taskallocation.RestTaskallocationQuery;
import nc.api.cy.rest.entity.crm.taskallocation.RestTaskallocationSave;
import nc.api.cy.rest.entity.crm.taskallocation.TaskallocationAggPOJO;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.pub.BusinessException;
import nccloud.itf.cy.cy.ITaskallocationhvoMaintain;
import uap.ws.rest.resource.AbstractUAPRestResource;
/*@author huangcong
 * 任务分配接口
 */
@Path("cytask")
public class CyTaskallocationResource extends AbstractUAPRestResource {

	@Override
	public String getModule() {
		CyCommonUtils.login();
		return "cy";
	}
	/*
	 * 任务分配列表查询
	 */
	@POST
	@Path("queryList")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryList(@RequestBody RestTaskallocationQuery restQuery) {
		try {			
			ResponseList queryListData=CyCommonUtils.getInstance(ITaskallocationhvoMaintain.class).queryListPage(restQuery);
			return ResponsePOJO.toJSON(queryListData, "200", "成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}
	
	/*
	 * 任务分配详情查询
	 */
	@POST
	@Path("queryDeatil/{pk_taskallocation}")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryDeatil(@PathParam("pk_taskallocation") String pk) {
		try {
			TaskallocationAggPOJO result=CyCommonUtils.getInstance(ITaskallocationhvoMaintain.class).queryDeatil(pk);
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
	 * 任务分配更新
	 */
	@POST
	@Path("billSave")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString billSave(@RequestBody RestTaskallocationSave restTaskQuery) {
		try {
			CyCommonUtils.getInstance(ITaskallocationhvoMaintain.class).billSave(restTaskQuery);
			return ResponsePOJO.toJSON(null, "200", "保存成功");		
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}
	
}
