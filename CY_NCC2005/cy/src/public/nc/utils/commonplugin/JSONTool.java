package nc.utils.commonplugin;

import org.json.JSONString;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.cy.pojo.ResponsePOJO;

public class JSONTool {

	/**
	 * 将JSON字符串转换成数组
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getArrByJson(String jsonStr, Class Class) {
		return JSON.parseArray(jsonStr, Class);
	}

	/**
	 * 将数组转换成JSON字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJsonByArr(Object obj) {
		return JSON.toJSONString(obj);
	}

	/**
	 * 将对象转换成JSON字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJsonByObj(Object obj) {
		return JSON.toJSONString(obj);
	}

	/**
	 * 将JSON字符串转换成对象
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getObjByJson(String jsonStr, Class Class) {
		return JSON.toJavaObject(JSON.parseObject(jsonStr), Class);
	}

	/**
	 * 将json字符转换为对象
	 * 
	 * @param jsonStr
	 * @param Class
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getObjByJson2(String jsonStr, Class Class) {
		return JSON.parseObject(jsonStr, Class);
	}

	public static String getJsonByObject(Object object) {
		return JSONObject.toJSONString(object, SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullStringAsEmpty);
	}
	
	
	 /** 
     * @Title getJsonValueByKey 
     * @Description 获取Json格式字符串中key对应的值
     * @param jsonStr json格式的字符串
     * @param key 要获取值的键
     * @return Object
     * @version V1.0
     */
    public static Object getJsonValueByKey(String jsonStr, String key) {
	    // 此处引入的是 com.alibaba.fastjson.JSONObject; 对象
	    JSONObject jsonObject = JSONObject.parseObject(jsonStr);
	    // 获取到 key 对应的值
	    return jsonObject.get(key);
    }
	

	public static JSONString returnVoIsNull() {
		PagingPOJO rvo = new PagingPOJO();
		rvo.setCurrentPage(0);
		rvo.setPageSize(0);
		rvo.setTotal(0);
		rvo.setTotalPages(0);
		rvo.setContent(null);
		return ResponsePOJO.toJSON(rvo, "200", "成功");
	}

	public static JSONString returnVoIsNull(String msg) {
		PagingPOJO rvo = new PagingPOJO();
		rvo.setCurrentPage(0);
		rvo.setPageSize(0);
		rvo.setTotal(0);
		rvo.setTotalPages(0);
		rvo.setContent(null);
		return ResponsePOJO.toJSON(rvo, "200", msg);
	}

}
