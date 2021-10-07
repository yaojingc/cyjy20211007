package nc.utils.commonplugin;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.PushRequest.PushParamPOJO;
import nc.utils.commonplugin.PushRequest.QueryUserPOJO;
import nc.utils.commonplugin.PushResponse.ResultPOJO;
import nc.utils.commonplugin.PushResponse.TokenRootClassVO;
import nc.utils.commonplugin.PushResponse.TokenVO;
import nc.utils.commonplugin.PushResponse.UserRootClassVO;
import nc.vo.bd.psn.PsndocVO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * 将数据推送到友空间
 * 
 * @author huangcong *
 */
public class PushDataUtil {

	private static String fileName = "CautionExpirylog.txt";

	/**
	 * 友空间推送数据
	 * 
	 * @param psndocVOs  进行消息推送的用户
	 * @param msgTitle   推送的信息标题
	 * @param msgContent 推送的信息内容
	 * @return
	 */
	public static  void pushData(List<PsndocVO> psndocVOs, String msgTitle, String msgContent) {
		NoteDataUtils.addLog(
				"****************************************************进到方法里*******************************************************************",
				fileName, false, null);
		Security.setProperty("ssl.SocketFactory.provider", "");
		Security.setProperty("ssl.ServerSocketFactory.provider", "");
		String accessToken = null;
		String result = null;

		NoteDataUtils.addLog(
				"****************************************************第一步获取token*******************************************************************",
				fileName, false, null);
		accessToken = getAccessToken();
		NoteDataUtils.addLog(
				"****************************************************第二步获取友空间用户信息***********************************************************",
				fileName, false, null);
		if (CyCommonUtils.isEmpty(accessToken)) {
			return;
		}
		
		NoteDataUtils.addLog(
				"****************************************************第二步获取友空间用户信息***********************************************************",
				fileName, false, null);
		List<String> listYYUserid = getYYUserid(psndocVOs, accessToken);
		if (listYYUserid == null || listYYUserid.size() == 0) {
			return;
		}

		NoteDataUtils.addLog(
				"****************************************************第三步给友空间用户推送信息*********************************************************",
				fileName, false, null);
		try {
			// 推送消息
			PushParamPOJO pushVO = new PushParamPOJO();
			pushVO.setTenantId(AppletsParams.YONYOU_tenantId);
			pushVO.setAppId(AppletsParams.YONYOU_appCode);
			pushVO.setTitle(msgTitle);
			pushVO.setContent(msgContent);
			pushVO.setSendScope("list");
			List<String> yhtUserIds = new ArrayList<String>();
			yhtUserIds.addAll(listYYUserid);
			pushVO.setYhtUserIds(yhtUserIds);

			NoteDataUtils.addLog("推送消息内容：" + "\n" + msgContent, fileName, false, null);

			String pushDataUrl = AppletsParams.YOUYOU_pushMsgUrl + "?access_token=" + accessToken;
			NoteDataUtils.addLog("开始调用接口，推送消息息请求url", fileName, false, pushDataUrl);
			
			String jsonByObj = JSONTool.getJsonByObj(pushVO);
			result = ErpItfDataPool.erpHttpPost(jsonByObj, pushDataUrl);
//			result = sendRequestToPushData(pushDataUrl, pushVO);
			NoteDataUtils.addLog("接口返回结果：" + result, fileName, false, null);

			@SuppressWarnings("rawtypes")
			ResultPOJO pojo=(ResultPOJO) JSONTool.getObjByJson(result, ResultPOJO.class);
			if (pojo != null && pojo.getCode().equals("200")) {
				NoteDataUtils.addLog("接口调用，成功，信息已成功推送到友空间用户", fileName, false, null);
			} else {
				NoteDataUtils.addLog("接口调用，失败，信息未推送到友空间用户", fileName, false, null);
			}
		} catch (Exception e) {
			String exceptionDetail = ExceptionDetailUtil.getExceptionDetail(e);
	    	NoteDataUtils.addLog("推送数据信息过程中出现异常:"+"\n"+exceptionDetail, fileName, false, null);
		}
	}

	/**
	 * 获取应用的access token
	 * 
	 * @return
	 * @throws Exception
	 */
	private static String getAccessToken() {
		String result = null;
		String accessToken = null;
		try {
			Map<String, Object> params = new HashMap<>();
			// 除签名外的其他参数
			params.put("appKey", AppletsParams.YONYOU_appKey);
			params.put("timestamp", String.valueOf(System.currentTimeMillis()));

			// 计算签名
			String signature = sign(params,AppletsParams.YONYOU_appSecret);
			params.put("signature", signature);

			String url =AppletsParams.YOUYOU_getTokenUrl+ "?timestamp=" + params.get("timestamp") + "&appKey="
					+ AppletsParams.YONYOU_appKey + "&signature=" + signature;
			NoteDataUtils.addLog("开始调用接口，获取token请求url", fileName, false, url);
			JSONObject token = ErpItfDataPool.erpHttpGet(url);
			TokenRootClassVO tokenRootVO = (TokenRootClassVO)token.toJavaObject(TokenRootClassVO.class);
		 	NoteDataUtils.addLog("接口返回结果：" + token.toString(), fileName, false, null);		 	
		 	TokenVO tokenvo=tokenRootVO.getData();
			NoteDataUtils.addLog("接口返回结果：" + tokenvo.getAccess_token(), fileName, false, null);

			if (!("00000").equals(token.getString("code"))) {
				NoteDataUtils.addLog("接口调用，失败，获取token失败：" + token.getString("message") + "，推送数据终止！", fileName, false, null);
			} else {
				accessToken = tokenvo.getAccess_token();
				NoteDataUtils.addLog("接口调用，成功，token为：" + accessToken, fileName, false, null);
			}
		} catch (Exception e) {
			String exceptionDetail = ExceptionDetailUtil.getExceptionDetail(e);
			NoteDataUtils.addLog("获取token过程中出现异常:" + "\n" + exceptionDetail, fileName, false, null);
		}
		return accessToken;
	}

	/**
	 * 根据预警配置的消息用户得到该些用户在友空间对应的信息
	 * <p>
	 * 备注：没有根据多手机号获取用户信息的接口，故此处循环调用接口，获取用户id
	 * </p>
	 * 
	 * @param psndocVOs
	 * @return
	 * @throws Exception
	 */
	private static  List<String> getYYUserid(List<PsndocVO> psndocVOs, String accessToken) {
		StringBuilder psnStr = new StringBuilder();
		List<String> listYYUserid = new ArrayList<String>();
		String url = AppletsParams.YOUYOU_searchUserUrl + "?access_token=" + accessToken;
		NoteDataUtils.addLog("开始调用接口，获取用户友空间信息请求url", fileName, false, url);
		try {
			for (PsndocVO psnVO : psndocVOs) {
				if (StringUtils.isBlank(psnVO.getMobile())) {
					NoteDataUtils.addLog("NC系统用户[" + psnVO.getName() + "]未维护手机号,无法对该用户进行消息推送!", fileName, false, null);
					continue;
				}

				// psnVO.setMobile("15270913146");

				QueryUserPOJO pojo = new QueryUserPOJO();
				pojo.setSearchcode(psnVO.getMobile());
	
				NoteDataUtils.addLog("获取NC系统用户[" + psnVO.getName() + ":" + psnVO.getMobile() + "]在友空间的用户信息", fileName,
						false, null);
				String jsonByObj = JSONTool.getJsonByObj(pojo);
				String result = ErpItfDataPool.erpHttpPost(jsonByObj, url);
//				String result = sendRequestToPushData(url, pojo);
				NoteDataUtils.addLog("接口返回结果：" + result, fileName, false, null);
//				UserRootClassVO userRootClassVO = JSON.parseObject(result, UserRootClassVO.class);
				UserRootClassVO userRootClassVO=(UserRootClassVO) JSONTool.getObjByJson(result, UserRootClassVO.class);

				if (!userRootClassVO.getCode().equals("200")) {
					NoteDataUtils.addLog("接口调用，失败，获取用户[" + psnVO.getName() + ":" + psnVO.getMobile()
							+ "]信息失败，不会给该用户往友空间推送消息：" + userRootClassVO.getMessage() + "", fileName, false, null);
				} else {
					listYYUserid.add(userRootClassVO.getData().getContent()[0].getUserId());
					psnStr.append("[").append(psnVO.getName() + ":" + psnVO.getMobile()).append("]");
					NoteDataUtils.addLog("接口调用，成功，获取用户[" + psnVO.getName() + ":" + psnVO.getMobile() + "]信息成功",
							fileName, false, null);
				}
			}
		} catch (Exception e) {
			String exceptionDetail = ExceptionDetailUtil.getExceptionDetail(e);
	    	NoteDataUtils.addLog("获取用户信息过程中出现异常:"+"\n"+exceptionDetail, fileName, false, null);
		} finally {
			if (psnStr.length() > 0) {
				NoteDataUtils.addLog("用户信息汇总完毕，开始给如下用户,往推送友空间信息" + "\n" + psnStr.toString(), fileName, false, null);
			} else {
				NoteDataUtils.addLog("当前无推送用户，推送消息终止！", fileName, false, null);
			}
		}

		return listYYUserid;
	}

	/**
	 * 计算签名
	 * @param params
	 * @param suiteSecret
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 */
	private static  String sign(Map<String, Object> params, String suiteSecret) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
	    Map<String, Object> treeMap;
	    if (params instanceof TreeMap) {
	        treeMap = params;
	    } else {
	        treeMap = new TreeMap<>(params);
	    }
	    StringBuilder stringBuilder = new StringBuilder();
	    for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
	        stringBuilder.append(entry.getKey()).append(entry.getValue());
	    }

	    Mac mac = Mac.getInstance("HmacSHA256");
	    mac.init(new SecretKeySpec(suiteSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
	    byte[] signData = mac.doFinal(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
	    byte[] base64String = Base64.encodeBase64(signData);
	    String s = new String(base64String);
	    return URLEncoder.encode(s, "UTF-8");
	}

	/**
	 * 推送数据
	 * 
	 * @param url
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	private static String sendRequestToPushData(String url, Object jsonData) throws Exception {
		URL url2 = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setAllowUserInteraction(true);
		connection.setInstanceFollowRedirects(true);
		connection.setUseCaches(false);
		// 格式
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.connect();
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		String json2 = JSON.toJSONString(jsonData);
		output.append(json2);
		output.flush();
		output.close();
		// 接收返回数据
		BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int len;
		byte[] arr = new byte[1024];
		while ((len = bis.read(arr)) != -1) {
			bos.write(arr, 0, len);
			bos.flush();
		}
		bos.close();

		if (null == bos.toString("utf-8") || "".equals(bos.toString("utf-8"))) {
			return null;
		} else {
			JsonParser parse = new JsonParser();

			JsonObject parse2 = (JsonObject) parse.parse(bos.toString("utf-8"));
			return parse2.toString();
		}
	}

	/**
	 * 发送请求得到token
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private static  String sendRequestByGetToken(String url) throws Exception {
		URL url2 = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
		connection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
		connection.setRequestProperty("Content-Type", "plain/text; charset=UTF-8");
		connection.setRequestMethod("GET");
		connection.connect();
		// 接收返回数据
		BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int len;
		byte[] arr = new byte[1024];
		while ((len = bis.read(arr)) != -1) {
			bos.write(arr, 0, len);
			bos.flush();
		}
		bos.close();
		if (null == bos.toString("utf-8") || "".equals(bos.toString("utf-8"))) {
			return null;
		} else {
			JsonParser parse = new JsonParser();
			JsonObject parse2 = (JsonObject) parse.parse(bos.toString("utf-8"));
			return parse2.toString();
		}
	}

	/**
	 * 通过appkey获取租户id
	 * 
	 * @return
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */

	public String getTenantId() throws IOException, InvalidKeyException, NoSuchAlgorithmException {
		Map<String, Object> params = new HashMap<>();
		// 除签名外的其他参数
		params.put("appKey", "2f2d7fb8d57a414792b6ed2c6d610ff6");
		params.put("timestamp", String.valueOf(System.currentTimeMillis()));
		// 计算签名
		 String signature = sign(params, "1363a2059851452eaca79b6a55a69a1e");
	
		params.put("signature", signature);

		// 请求
		String requestUrl = "https://api.diwork.com" + "/open-auth/selfAppAuth/getTenantId?timestamp="+params.get("timestamp")+"&appKey="+AppletsParams.YONYOU_appKey+"&signature="+signature;;
		
		try {
			String result =sendRequestByGetToken(requestUrl);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
//		throw new RuntimeException("请求开放平台接口失败, code: " + response.getCode() + ", message: " + response.getMessage());
	}



}
