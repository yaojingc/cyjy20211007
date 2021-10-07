package nc.utils.swiftpass;

import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import nc.utils.commonConstant.AppletsParams;

public class SignUtil {
    //private final static Logger log = LoggerFactory.getLogger(SignUtil.class);
	
	public static String  mchprivatekey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAg"
			+ "EAAoIBAQDTO00AJd5QLC3KjVrypolDHBKthbhbM2c5WXucy5Tqi31XNzxLHsJtZDoSFjRv"
			+ "FA2Z1HPvcH038GHB2nscVIr41o4moPWIYe8R4x9rqyfhtItFPC202tIvVJcwJBp4z/7yZC"
			+ "kUhjlScD9eE/JxuLpwA2x/j/w/ukNjNAmr1sj0NQ3tMbo4kbkWioqdEOAFBpzON82aGhso"
			+ "mWLPJy2UXFQIBbILGJrZq4cT94m1MXwtJBF3We3qWNGbNHselyhGaitKsUoMzYHO3yt5aO"
			+ "RwLjIcqcZkTohKHR0Qej3+NaMB5XG9jd7fsHidGak/9ezRk0swZuV6WeUfcH6sDsCJp317"
			+ "AgMBAAECggEAG968JyPX9E4P8KS79j4g6DjhAgIds/LRUNDMrYBy8otbC1HfY634S0SpIM/"
			+ "LNUi5Dq3oahQ2gVX3IAO6sJ87AuAUO2aLXuZFC+AiuisIBRC0O3tYMH+aseIAXLq2v2/6H"
			+ "HM4tFuNXqIuyjPpjjWoa7UX8LDHwITL9V/dUs3xD79e0be1XODeuFq4njlAaimbm2K/+eI"
			+ "Jf1OeKG744JqIjwYkWajqBWtUTeeSsc/zz3BMv9gYypDsuh/8pehFC6WrptXDUoVLw9WjK"
			+ "iniSsqEbQCONOIbeIhFew4/6sLm3+SILEUGyZpPTIhU1eVXqnYBKRieY91rlpWZ+NpBt3B"
			+ "7UQKBgQD2miKDtnK9LFd8HdTZTGYqoyllSV2jmbXj5clQsZ/WAxPUQqqqrSnSRDK59xEEt"
			+ "imrBqrbwxvGXfoGABe0S3382YLG6q5bQYp7QNHWDxu8z74eaLFkbgX7wfqYA09pZ8nvAZ4"
			+ "JFVSpM/5ayJrnIT0X8is2h1DWosXE4T/T5pgRcwKBgQDbSBb3m8hAW0AVfE6wQq1cYO2Qa"
			+ "YubQHW7uFX6gP7zvpS+2dRpqmEl9RrqpI7ZAPd+RLla2FkKyyqQ5HGRxBJdJRU5b7q4RGZ"
			+ "zp195DtVQ7eqktpgCAGT+GIkX5vrmiN1s+tfLLEqNkoGc2vGUc/sGp22z2X3HOd72lrmmk"
			+ "cPB2QKBgQCX1g+qSYwcLlEveq6znPbLCetTEfk14k68I5/wr21auEpdRy8OJ9C4RoV1O0q"
			+ "fPAor488PwPI5EWQAjc4KlPDd3o9HKvhv98rsWNzakDLj7oWA87VFwPiuKqHBN0iN/fm3z"
			+ "6cesKOQCxNxklLOPQkA1XsF+AsuHMrojkhekHzRQQKBgF775xbyX9egry2fXuZyu21cfwsi"
			+ "7NofHeBmhLex0L38O6o/6QasStCKT1J8XXQaOxpy4mc7d7Z5MsL4cUlWyS2Xto3em97dohN"
			+ "O2MgzaKtZo14dydJ0IUr/jcLQxrW9a0ydi4Pyd4jDgWlUXXiUlJPZSmexOg5mPvJJb7l7Qn"
			+ "RhAoGBAMRpvxHUd0Q8CtdoqEdro40m6qJ8fvLl8eqZQnVnxb3U6TQirRJ0EQdjlcXO21jYL"
			+ "uUV1q+y2kFoOV7ZQL84Rq4wKhz52ZYcVeD7B0SeF/tNLs2x7MeqlBVpeD3WWNDjhEso/NDI"
			+ "Ra0YvSebRDjb9gKHcmxoOA/aKlghstW3sNh2";
	
	
	public static String  platpublickey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA"
			+ "0ztNACXeUCwtyo1a8qaJQxwSrYW4WzNnOVl7nMuU6ot9Vzc8Sx7CbWQ6EhY0bxQNmdRz73B9N"
			+ "/Bhwdp7HFSK+NaOJqD1iGHvEeMfa6sn4bSLRTwttNrSL1SXMCQaeM/+8mQpFIY5UnA/XhPyc"
			+ "bi6cANsf4/8P7pDYzQJq9bI9DUN7TG6OJG5FoqKnRDgBQaczjfNmhobKJlizyctlFxUCAWyC"
			+ "xia2auHE/eJtTF8LSQRd1nt6ljRmzR7HpcoRmorSrFKDM2Bzt8reWjkcC4yHKnGZE6ISh0dE"
			+ "Ho9/jWjAeVxvY3e37B4nRmpP/Xs0ZNLMGblelnlH3B+rA7Aiad9ewIDAQAB";
	
	//请求时根据不同签名方式去生成不同的sign
    public static String getSign(String signType,String preStr){
    	if("RSA_1_256".equals(signType)){ 
        	try {
        		return SignUtil.sign(preStr,"RSA_1_256",mchprivatekey);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }else{
//        	return MD5.sign(preStr, "&key=" + AppletsParams.key, "utf-8");
        }
    	return null;
    }
    
    //对返回参数的验证签名
    public static boolean verifySign(String sign,String signType,Map<String,String> resultMap) throws Exception{
    	if("RSA_1_256".equals(signType)){
    		Map<String,String> Reparams = SignUtils.paraFilter(resultMap);
            StringBuilder Rebuf = new StringBuilder((Reparams.size() +1) * 10);
            SignUtils.buildPayParams(Rebuf,Reparams,false);
            String RepreStr = Rebuf.toString();
            if(SignUtil.verifySign(RepreStr,sign, "RSA_1_256", platpublickey)){
            	return true;
            }
    	}
//    	else if("MD5".equals(signType)){
//    		if(SignUtils.checkParam(resultMap, AppletsParams.key)){
//    			return true;
//    		}
//    	}
    	return false;
    }
	public static boolean verifySign(String preStr,String sign,String signType, String platPublicKey) throws Exception {
		// 调用这个函数前需要先判断是MD5还是RSA
		// 商户的验签函数要同时支持MD5和RSA
		RSAUtil.SignatureSuite suite = null;
		
		if ("RSA_1_1".equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA1;
		} else if ("RSA_1_256".equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA256;
		} else {
			throw new Exception("不支持的签名方式");
		}
        
		boolean result = RSAUtil.verifySign(suite, preStr.getBytes("UTF8"), Base64.decodeBase64(sign.getBytes("UTF8")),
                platPublicKey);
        
		return result;
    }

    public static String sign(String preStr, String signType, String mchPrivateKey) throws Exception {
		RSAUtil.SignatureSuite suite = null;
		if ("RSA_1_1".equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA1;
		} else if ("RSA_1_256".equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA256;
		} else {
			throw new Exception("不支持的签名方式");
		}
        byte[] signBuf = RSAUtil.sign(suite, preStr.getBytes("UTF8"),
                mchPrivateKey);
        return new String(Base64.encodeBase64(signBuf), "UTF8");
    }
}
