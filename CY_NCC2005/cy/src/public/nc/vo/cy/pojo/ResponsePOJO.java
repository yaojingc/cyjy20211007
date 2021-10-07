package nc.vo.cy.pojo;

import org.json.JSONString;

import nc.utils.commonplugin.JSONTool;
import nccloud.api.rest.utils.NCCRestUtils;

public class ResponsePOJO {

	// 接口返回状态（200成功、 400失败）
	private String code;
	// 状态信息
	private String msg;
	// 接口响应信息
	private Object data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static JSONString toJSON(Object data, String code, String msg) {
		ResponsePOJO res = new ResponsePOJO();
		res.setData(data);
		res.setCode(code);
		res.setMsg(msg);
		return NCCRestUtils.toJSONString(res);
	}
	
	
	public static String toRespObj(Object data, String code, String msg) {
		ResponsePOJO res = new ResponsePOJO();
		res.setData(data);
		res.setCode(code);
		res.setMsg(msg);
		String result = JSONTool.getJsonByObj(res);
		return result;
	}
}
