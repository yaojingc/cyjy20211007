package nc.vo.cy.teacherform.pojo;

import org.json.JSONString;

import com.alibaba.fastjson.JSONObject;

import nc.vo.cy.pojo.ResponsePOJO;
import nccloud.api.rest.utils.NCCRestUtils;

public class ListQueryReturnPOJO {

	private String code = "200";
	private String msg = "成功";
	private DataContent<LContentRet> data;

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

	public DataContent<LContentRet> getData() {
		return data;
	}

	public void setData(DataContent<LContentRet> data) {
		this.data = data;
	}

//	public static JSONString toJSON(ListQueryReturnPOJO data, String code, String msg) {
//		data.setCode(code);
//		data.setMsg(msg);
//		return NCCRestUtils.toJSONString(data);
//	}
//	
//	public static JSONString toJSON(DataContent<LContent> data) {
//		ListQueryReturnPOJO res = new ListQueryReturnPOJO();
//		res.setData(data);
//		JSONObject jsb = JSONObject.parseObject(NCCRestUtils.toJSONString(res).toString()).getJSONObject("data");
//		return NCCRestUtils.toJSONString(res);
//		
//	}

}
