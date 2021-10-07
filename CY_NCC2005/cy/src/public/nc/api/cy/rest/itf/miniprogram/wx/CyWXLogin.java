package nc.api.cy.rest.itf.miniprogram.wx;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.api.cy.rest.itf.miniprogram.common.MiniProgramServlet;
import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.NoteDataUtils;
import nc.utils.swiftpass.XmlUtils;

/**
 * 小程序登陆
 * 
 * @param code
 * @return
 */
public class CyWXLogin extends MiniProgramServlet implements IHttpServletAdaptor {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			super.virtualLogin();
			InputStream is = request.getInputStream();
			DataInputStream input = new DataInputStream(is);
			String code = XmlUtils.parseRequst(request);
			
			BufferedReader in = null;
			StringBuffer result = null;

			// url请求中如果有中文，要在接收方用相应字符转码
			String params = AppletsParams.AppID + "&secret=" + AppletsParams.AppSecret + "&js_code=" + code
					+ "&grant_type=authorization_code";

			URI uri = new URI(AppletsParams.WxLoginUrl + params);
			URL url = uri.toURL();
			NoteDataUtils.addLog(url.toString(), "wxloginrequesturl", false, null);
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("Content-type", "application/json; charset=utf-8");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestProperty("contentType", "utf-8");

			connection.setDoOutput(true);
			connection.setDoInput(true);

			PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
			printWriter.print(params);
			printWriter.flush();

			connection.connect();
			result = new StringBuffer();
			// 读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			response.getOutputStream().write(result.toString().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
//		finally { 
//			try {
//				if (in != null) {
//					in.close();
//				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}

	}

}
