package nc.api.cy.rest.itf;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONString;
import org.springframework.web.bind.annotation.RequestBody;
import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.xuefeiapply.RestXuefeiSave;
import nc.api.cy.rest.entity.crm.yejiapply.RestYejiQuery;
import nc.api.cy.rest.entity.crm.yejiapply.RestYejiSave;
import nc.api.cy.rest.entity.crm.yejiapply.YejiAggPOJO;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.pub.BusinessException;
import nccloud.itf.cy.cy.IXuefeijmhvoMaintain;
import nccloud.itf.cy.cy.IYejiapplyhvoMaintain;
import uap.ws.rest.resource.AbstractUAPRestResource;

/*@author huangcong
 * 星级评定(业绩申请)接口
 */
@Path("cyyj")
public class CyYejiapplyResource extends AbstractUAPRestResource {

	@Override
	public String getModule() {
		CyCommonUtils.login();
		return "cy";
	}

	/*
	 * 星级评定(业绩申请)列表查询
	 */
	@POST
	@Path("queryList")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryList(@RequestBody RestYejiQuery restSave) {
		try {
			ResponseList queryListData = CyCommonUtils.getInstance(IYejiapplyhvoMaintain.class)
					.queryListPage(restSave);
			// 3、返回响应结果
			return ResponsePOJO.toJSON(queryListData, "200", "成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 星级评定(业绩申请)详情查询
	 */
	@POST
	@Path("queryDeatil/{pk_yejiapply}")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryDeatil(@PathParam("pk_yejiapply") String pk) {
		try {
			YejiAggPOJO result = CyCommonUtils.getInstance(IYejiapplyhvoMaintain.class).queryDeatil(pk);
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
	 * 星级评定(业绩申请)更新
	 */
	@POST
	@Path("billSave")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString billSave(@RequestBody RestYejiSave restSave) {
		try {
			CyCommonUtils.getInstance(IYejiapplyhvoMaintain.class).billSave(restSave);
			// 3、返回响应结果
			return ResponsePOJO.toJSON(null, "200", "保存成功");

		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}
	
	/*
	 * 星级评定(业绩申请)提交
	 */
	@POST
	@Path("commit")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString commit(@RequestBody RestYejiSave restSave) {
		try {
			CyCommonUtils.getInstance(IYejiapplyhvoMaintain.class).commit(restSave);
			return ResponsePOJO.toJSON(null, "200", "提交成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

}
