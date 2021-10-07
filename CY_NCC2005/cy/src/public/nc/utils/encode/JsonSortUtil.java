package nc.utils.encode;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class JsonSortUtil {

	private final static String SIGN = "sign";
	private final static String CLASZ = "class";

	public static void main(String[] args) {
		String json = "{\"bizContent\":{\"mchId\":\"mch20190710002\",\"mchType\":1,\"mchName\":\"木屋\",\"shopId\":\"43509983388683264\",\"shopName\":\"木屋-科技园分店\"},\"charset\":\"UTF-8\",\"messageId\":\"3453453234543523\",\"certId\":\"appId001-INSIDE-20190523190036\",\"guestId\":\"appId001\",\"reqTime\":\"20190618112021472\",\"signType\":\"RSA2\",\"accountId\":\"dexoekkidskj3jkkxii2k\",\"listEmpty\":[],\"listValue\":[\"vad\",\"sds\"],\"listMapValue\":[{\"mchId\":\"mch20190710002\"},{\"mchId\":\"mch2019071000b\"}],\"mapEmpty\":[{\"f\":\"\",\"d\":\"\"},{\"f\":\"gg\",\"d\":\"dd\"}]}";
		String waitSign = parseSignJson(json);

		System.out.println(waitSign);

	}

	/**
	 * json转map 然后多层嵌套map排序生成待签名的字符串
	 */
	public static String parseSignJson(String json) {
		Map<String, Object> reqMap = (Map<String, Object>) JSONObject.parse(json);

		return sortMapToWaitSignStr(reqMap);
	}

	/**
	 * 支持多层嵌套map排序生成待签名的字符串
	 */
	public static String sortMapToWaitSignStr(Map<String, Object> reqMap) {
		/**
		 * 排序
		 */
		TreeMap<String, Object> dataTree = getDataTree(reqMap);
		/**
		 * 拼接签名的字符串
		 */
		StringBuffer sb = getDataTreeSign(dataTree);
		return sb.toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static TreeMap<String, Object> getDataTree(Map map) {
		Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
		TreeMap<String, Object> sortMap = new TreeMap<String, Object>(new MapKeyComparator());
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			if (CLASZ.equals(entry.getKey())) {
				continue;
			}
			if (entry.getValue() instanceof JSONArray) {
				sortMap.put(entry.getKey(), getDataJSONArray((JSONArray) entry.getValue()));
			} else if (entry.getValue() instanceof JSONObject) {
				sortMap.put(entry.getKey(), getDataTree((JSONObject) entry.getValue()));
			} else if (entry.getValue() instanceof Map) {
				sortMap.put(entry.getKey(), getDataTree((Map) entry.getValue()));
			} else if (entry.getValue() instanceof List) {
				sortMap.put(entry.getKey(), getDataArrayList((List) entry.getValue()));
			} else {
				sortMap.put(entry.getKey(), entry.getValue());
			}
		}
		return sortMap;
	}

	@SuppressWarnings("rawtypes")
	public static List<Object> getDataArrayList(List<Object> list) {
		List<Object> resultList = new ArrayList<Object>();
		for (Object object : list) {
			if (object instanceof Map) {
				resultList.add(getDataTree((Map) object));
			} else {
				resultList.add(object);
			}

		}
		return resultList;
	}

	public static Object getDataJSONArray(JSONArray jsonArray) {
		JSONArray newJSONArray = new JSONArray();
		for (Object ja : jsonArray) {
			if (ja instanceof JSONObject) {
				newJSONArray.add(getDataTree((JSONObject) ja));
			} else if (ja instanceof JSONArray) {
				newJSONArray.add(getDataJSONArray((JSONArray) ja));
			} else {
				newJSONArray.add(ja);
			}

		}
		return newJSONArray;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static StringBuffer getDataTreeSign(Map map) {
		Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
		StringBuffer params = new StringBuffer();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			Object entryObj = entry.getValue();
			if (entryObj instanceof Map) {
				params.append(entry.getKey()).append("=").append("{").append(getDataTreeSign((Map) entryObj).toString())
						.append("}").append("&");
			} else if (entryObj instanceof List) {
				List<Object> list = (List<Object>) entry.getValue();
				params.append(entry.getKey()).append("=").append("[");
				for (Object listItem : list) {
					if (listItem instanceof Map) {
						params.append("{");
						params.append(getDataTreeSign(getDataTree((Map) listItem)).toString());
						params.append("}&");
					} else {
						params.append(listItem).append(",");
					}
				}
				if (list.size() > 0) {
					params.deleteCharAt(params.length() - 1);
				}
				params.append("]").append("&");
			} else {
				// 值为空 key为 sign class
				if (null != entryObj && StringUtils.isNotBlank(String.valueOf(entryObj)) && !SIGN.equals(entry.getKey())
						&& !CLASZ.equals(entry.getKey())) {
					params.append(entry.getKey()).append("=").append(entryObj).append("&");
				}

			}
		}

		if (params.length() > 0) {
			params.deleteCharAt(params.length() - 1);
		}

		return params;
	}

	static class MapKeyComparator implements Comparator<String> {

		@Override
		public int compare(String str1, String str2) {

			return str1.compareTo(str2);
		}
	}
}
