package nc.api.cy.rest.itf.miniprogram.wx;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import nc.api.cy.rest.itf.miniprogram.common.MiniProgramServlet;
import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.NoteDataUtils;
import nc.utils.commonplugin.returnparam.TwoTuple;
import nc.utils.swiftpass.SignUtil;
import nc.utils.swiftpass.SignUtils;
import nc.utils.swiftpass.XmlUtils;
import nc.vo.cy.pojo.ResponsePOJO;

/**
 * 支付请求
 * 
 * @param req
 * @param resp
 * @throws ServletException
 * @throws IOException
 * @see [类、类#方法、类#成员] HttpServletRequest req, HttpServletResponse resp
 */
public class CyWXPayment extends MiniProgramServlet implements IHttpServletAdaptor {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		super.virtualLogin();
		TwoTuple<String, SortedMap> mapBefore = XmlUtils.getParameterMap(XmlUtils.parseRequst(request));

		String resp = mapBefore.first;
		SortedMap<String, String> map = mapBefore.sencond;
		
		Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();
		String sign_type = "RSA_1_256";
		
		CloseableHttpResponse response2 = null;
		CloseableHttpClient client = null;
		String res = null;
		Map<String, String> resultMap = null;
		try {
			map.put("sign", SignUtil.getSign("RSA_1_256", preStr));
			String reqUrl = AppletsParams.req_url;

			HttpPost httpPost = new HttpPost(reqUrl);
			StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map), "utf-8");
			NoteDataUtils.addLog(entityParams.toString(), "pay3", false, null);

			httpPost.setEntity(entityParams);
			httpPost.setHeader("Content-Type", "text/xml;utf-8");
			client = HttpClients.createDefault();
			response2 = client.execute(httpPost);
			if (response2 != null && response2.getEntity() != null) {
				resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response2.getEntity()), "utf-8");
				String reSign = resultMap.get("sign");
				sign_type = resultMap.get("sign_type");
				res = XmlUtils.toXml(resultMap);
				NoteDataUtils.addLog(res, "pay4", false, null);

//					if (resultMap.containsKey("sign") ) {//&& !SignUtil.verifySign(reSign, sign_type, resultMap)
//						res = "验证签名不通过";
//					} else {
				if ("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))) {
					String pay_info = resultMap.get("pay_info");
					res = "ok";
				} else {
					res = "fail";
				}
//					}
			} else {
				res = "操作失败";
			}
		} catch (Exception e) {
			resp=ResponsePOJO.toRespObj(null, "400", "系统异常：" + e.getMessage());
		} finally {
			if (response2 != null) {
				response2.close();
			}
			if (client != null) {
				client.close();
			}
		}
		Map<String, String> result = new HashMap<String, String>();
		if ("ok".equals(res)) {
			resp = ResponsePOJO.toRespObj(resultMap, "200", "成功");
		} else {
			resp = ResponsePOJO.toRespObj(result, "400", "操作异常："+resp);
		}
		NoteDataUtils.addLog(resp, "pay5", false, null);
		// 响应返回
		response.getOutputStream().write(resp.getBytes());
	}
}
