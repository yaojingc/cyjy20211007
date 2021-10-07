package nc.api.cy.rest.itf.miniprogram.invoice;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.json.JSONString;
import com.alibaba.fastjson.JSONObject;

import nc.api.cy.rest.itf.miniprogram.common.MiniProgramServlet;
import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonConstant.ConstantUtil;
import nc.utils.invoice.CallUtil;
import nc.utils.swiftpass.XmlUtils;
import nc.vo.cy.pojo.ResponsePOJO;
import nccloud.api.rest.utils.NCCRestUtils;

/**
 * 蓝票查询
 * 
 * @param jsonFlowid
 * @return
 */
public class CyQueryBlueInvoice extends MiniProgramServlet implements IHttpServletAdaptor {
	
	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		super.virtualLogin();
		InputStream is = request.getInputStream();
		DataInputStream input = new DataInputStream(is);
		String jsonFlowid = XmlUtils.parseRequst(request);
		JSONObject serObj = JSONObject.parseObject(jsonFlowid);
		String resp = "";
		if (serObj == null || serObj.toString() == "") {
			resp = ResponsePOJO.toRespObj(null, "400", "查询参数异常");
		}

		String flowid = serObj.getString("flowid");
		if (MMValueCheck.isEmpty(flowid)) {
			resp = ResponsePOJO.toRespObj(null, "400", "flowid为空");
		}
		String result = "";
		try {
			CallUtil callUtil = new CallUtil();
			// 接收到的开票数据，放到map里面
			Map<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("fpqqlsh", flowid); // 开票数据

			result = callUtil.callTaxCloudApi(ConstantUtil.QUERY_URL, paramsMap);
			JSONObject retJson = JSONObject.parseObject(result);
			if (!"0000".equals(retJson.getString("code")) && (!"查询成功".equals(retJson.getString("msg")))) {
				resp = ResponsePOJO.toRespObj(null, "400", retJson.getString("msg"));
			} else {
				resp = ResponsePOJO.toRespObj(result, "200", "查询成功");
			}
		} catch (Exception ex) {
			result = ex.getMessage();
			resp = ResponsePOJO.toRespObj(null, "400", result);
		}
		response.getOutputStream().write(resp.getBytes());
	}

}
