package nc.utils.encode;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

/**
 * ClassName:SignUtils Function: 签名用的工具箱 Date: 2014-6-27 下午3:22:33
 * 
 * @author
 */
public class SignUtils {

	/**
	 * <一句话功能简述> <功能详细描述>验证返回参数
	 * 
	 * @param params
	 * @param key
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean checkParam(Map<String, String> params, String key) {
		boolean result = false;
		if (params.containsKey("sign")) {
			String sign = params.get("sign");
			params.remove("sign");
			StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
			SignUtils.buildPayParams(buf, params, false);
			String preStr = buf.toString();
			String signRecieve = MD5.sign(preStr, "&key=" + key, "utf-8");
			result = sign.equalsIgnoreCase(signRecieve);
		}
		return result;
	}

	/**
	 * 过滤参数
	 * 
	 * @author
	 * @param sArray
	 * @return
	 */
	public static Map<String, String> paraFilter(Map<String, String> sArray) {
		Map<String, String> result = new HashMap<String, String>(sArray.size());
		if (sArray == null || sArray.size() <= 0) {
			return result;
		}
		for (String key : sArray.keySet()) {
			String value = sArray.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
				continue;
			}
			result.put(key, value);
		}
		return result;
	}

	/**
	 * <一句话功能简述> <功能详细描述>将map转成String
	 * 
	 * @param payParams
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String payParamsToString(Map<String, String> payParams) {
		return payParamsToString(payParams, false);
	}

	public static String payParamsToString(Map<String, String> payParams, boolean encoding) {
		return payParamsToString(new StringBuilder(), payParams, encoding);
	}

	/**
	 * @author
	 * @param payParams
	 * @return
	 */
	public static String payParamsToString(StringBuilder sb, Map<String, String> payParams, boolean encoding) {
		buildPayParams(sb, payParams, encoding);
		return sb.toString();
	}

	/**
	 * @author
	 * @param payParams
	 * @return
	 */
	public static void buildPayParams(StringBuilder sb, Map<String, String> payParams, boolean encoding) {
		List<String> keys = new ArrayList<String>(payParams.keySet());
		Collections.sort(keys);
		for (String key : keys) {
			sb.append(key).append("=");
			if (encoding) {
				sb.append(urlEncode(payParams.get(key)));
			} else {
				sb.append(payParams.get(key));
			}
			sb.append("&");
		}
		sb.setLength(sb.length() - 1);
	}

	public static String urlEncode(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (Throwable e) {
			return str;
		}
	}

	/**
	 * MD5签名
	 * 
	 * @param map
	 * @param signKey
	 * @return
	 */
	public static String md5Sign(Map<String, String> map, String signKey) {
		Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((params.size() +1) * 10);
        SignUtils.buildPayParams(buf,params,false);
        String preStr = buf.toString();
        String sign = MD5.sign(preStr, "&key=" + signKey, "utf-8");
        return sign;
	}
	
	/**
	 * rsa签名
	 * @param preStr
	 * @param signType
	 * @param mchPrivateKey
	 * @return
	 * @throws Exception
	 */
	public static String rsaSign(String preStr, String signType, String mchPrivateKey) throws Exception {
		RSAUtil.SignatureSuite suite = null;
		if (SignTypeEnum.RSA_1_1.getCode().equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA1;
		} else if (SignTypeEnum.RSA_1_256.getCode().equals(signType) || "RSA".equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA256;
		} else {
			throw new Exception("不支持的签名方式");
		}
        byte[] signBuf = RSAUtil.sign(suite, preStr.getBytes("UTF8"),
                mchPrivateKey);
        return new String(Base64.encodeBase64(signBuf), "UTF8");
    }
	
	/**
	 * 验证签名
	 * @param sign
	 * @param signType
	 * @param resultMap
	 * @return
	 * @throws Exception
	 */
	public static boolean verifySign(String sign,String signType,
			Map<String,String> resultMap,String key) throws Exception{
    	if(SignTypeEnum.RSA_1_1.getCode().equals(signType)
    			|| SignTypeEnum.RSA_1_256.getCode().equals(signType) || "RSA".equals(signType)){
    		Map<String,String> Reparams = SignUtils.paraFilter(resultMap);
            StringBuilder Rebuf = new StringBuilder((Reparams.size() +1) * 10);
            SignUtils.buildPayParams(Rebuf,Reparams,false);
            String RepreStr = Rebuf.toString();
            if(SignUtils.verifySign(RepreStr,sign, signType, key)){
            	return true;
            }
    	}else if(SignTypeEnum.MD5.getCode().equals(signType)){
    		if(SignUtils.checkParam(resultMap, key)){
    			return true;
    		}
    	}
    	return false;
    }
	
	public static boolean verifySign(String preStr,String sign,String signType, String platPublicKey) throws Exception {
		// 调用这个函数前需要先判断是MD5还是RSA
		// 商户的验签函数要同时支持MD5和RSA
		RSAUtil.SignatureSuite suite = null;
		
		if ("RSA_1_1".equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA1;
		} else if ("RSA_1_256".equals(signType) || "RSA".equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA256;
		} else {
			throw new Exception("不支持的签名方式");
		}
        
		boolean result = RSAUtil.verifySign(suite, preStr.getBytes("UTF8"), Base64.decodeBase64(sign.getBytes("UTF8")),
                platPublicKey);
        
		return result;
    }
	
}
