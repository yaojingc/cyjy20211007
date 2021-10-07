package nccloud.impl.WxServiceMsg;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.JSONTool;
import nc.utils.commonplugin.NoteDataUtils;
import nc.utils.miniprogram.AccessTokenUtil;
import nc.utils.miniprogram.CySHA1;
import nc.utils.miniprogram.pojo.MsgDetailContent;
import nc.utils.miniprogram.pojo.WxMsgReturnPojo;


/**
 * 
 * @author yao
 * 微信客服机器人
 *
 *微信获取token的请求地址
 *https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
 */
public class WxMsgHelper implements IHttpServletAdaptor {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		boolean isGet = request.getMethod().toLowerCase().equals("get");
		// 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		try {
			if (isGet) {
				String signature = request.getParameter("signature");
				// 时间戳
				String timestamp = request.getParameter("timestamp");
				// 随机数
				String nonce = request.getParameter("nonce");
				// 随机字符串
				String echostr = request.getParameter("echostr");
				//
				String hmh_token = "cyjy2021";

				String[] strArray = new String[] { hmh_token, timestamp, nonce };
				Arrays.sort(strArray);
				StringBuilder sb = new StringBuilder();
				for (String str : strArray) {
					sb.append(str);
				}
				String encrypt = CySHA1.encode(sb.toString());
				if (encrypt.equals(signature)) {
					response.getOutputStream().write(echostr.getBytes());
				}
			} else {
				// 进入POST聊天处理
				// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				// 接收消息并返回消息
                this.acceptMessage(request, response);
				// 响应消息
				PrintWriter out = response.getWriter();
				out.print("result is finished");
				out.close();
			}
		} catch (Exception ex) {
			NoteDataUtils.addLog("exception: "+ex.getMessage(), "exception", false, null);
		}

	}

	/**
	 * 聊天处理方法
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * 用户在小程序“客服会话按钮”进入客服会话时将产生如下数据包：
	 * {
		"ToUserName": "toUser",
		"FromUserName": "fromUser",
		"CreateTime": 1482048670,
		"MsgType": "event",
		"Event": "user_enter_tempsession",
		"SessionFrom": "sessionFrom"
		}
		
		用户在客服会话中发送文本消息时将产生如下数据包：
		{
		    "ToUserName": "toUser", 　　　　 //小程序的原始ID
		    "FromUserName": "fromUser",　　　//发送者的openid　
		    "CreateTime": 1482048670,　　　　//消息创建时间（整型）
		    "MsgType": "text",　　　　　　　 //消息类型
		    "Content": "this is a test",　　 //文本消息内容
		    "MsgId": 1234567890123456　　　　//消息id，64位整形
		}
		
	 */
	public void acceptMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		InputStream is = request.getInputStream(); 
		DataInputStream input = new DataInputStream(is); 
		String inputStreamParam = new String(input.readLine().getBytes("ISO-8859-1"),"utf-8");
		JSONObject resultJson= new JSONObject(inputStreamParam);		 
		String openid = resultJson.getString("FromUserName").trim();
		String msgType = resultJson.getString("MsgType").trim();
		
		
		
		
		
		String event = resultJson.getString("Event").trim();
		NoteDataUtils.addLog("openid: "+openid, "openid", false, null);
		// 判断是用户刚进入客服消息界面时
		if("event".equals(msgType) && "user_enter_tempsession".equals(event)) {
			request.setCharacterEncoding("UTF-8"); // 返回页面防止出现中文乱码
			WxMsgReturnPojo WxMsgReturnPojo = new WxMsgReturnPojo();
			WxMsgReturnPojo.setTouser(openid);
			WxMsgReturnPojo.setMsgtype("text");
			MsgDetailContent content = new MsgDetailContent();
			content.setContent(AppletsParams.WjfUrl);
			WxMsgReturnPojo.setText(content);
			
			String jsonStrs = JSONTool.getJsonByObj(WxMsgReturnPojo);
			NoteDataUtils.addLog("jsonstr: "+jsonStrs, "jsonstr", false, null);
			/* 获取token */
			String token = AccessTokenUtil.getAccessToken();
			NoteDataUtils.addLog("token: "+token, "token", false, null);
			/* 获取token */
			PrintWriter out = null;
			BufferedReader in = null;
			try {
				URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token);
				NoteDataUtils.addLog("pushmessageurl: "+url.toString(), "pushmessageurl", false, null);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.connect();
				OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
				writer.write(jsonStrs);
				writer.flush();
				InputStreamReader reader1 = new InputStreamReader(connection.getInputStream(), "UTF-8");
				BufferedReader breader = new BufferedReader(reader1);
				StringBuffer strb = new StringBuffer();
				String str = null;
				while (null != (str = breader.readLine())) {
					strb.append(str);
				}
				NoteDataUtils.addLog("returnresult: "+strb.toString(), "returnresult", false, null);
				writer.close();
				breader.close();
				connection.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			}
		}
	}
	
	

}
