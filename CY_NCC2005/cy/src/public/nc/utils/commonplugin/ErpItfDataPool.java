package nc.utils.commonplugin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ErpItfDataPool {

	public static String erpHttpPost(String request, String strurl) {

		byte[] parameter = request.getBytes();
		try {
			URL url = new URL(strurl);
			URLConnection http_url_connection = url.openConnection();
			HttpURLConnection HttpUrlConnection = (HttpURLConnection) http_url_connection;

			HttpUrlConnection.setDoOutput(true);
			HttpUrlConnection.setDoInput(true);
			HttpUrlConnection.setAllowUserInteraction(true);
			HttpUrlConnection.setInstanceFollowRedirects(true);
			HttpUrlConnection.setUseCaches(false);
			HttpUrlConnection.setRequestMethod("POST");// ��������ʽ��������delete put post get
			HttpUrlConnection.setRequestProperty("Content-Length", String.valueOf(parameter.length));// �������ݵĳ���
			HttpUrlConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");// ���ñ����ʽ
			HttpUrlConnection.setRequestProperty("Accept", "application/json");// ���ý��շ��ز�����ʽ
			HttpUrlConnection.connect();

			OutputStreamWriter output = new OutputStreamWriter(HttpUrlConnection.getOutputStream(), "UTF-8");
			output.write(request);
			output.flush();
			output.close();

			InputStreamReader input_stream_reader = new InputStreamReader(HttpUrlConnection.getInputStream(), "utf-8");
			BufferedReader buffered_reader = new BufferedReader(input_stream_reader);

			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = buffered_reader.readLine()) != null) {
				buffer.append(line);
			}
			String response = buffer.toString();
			return response;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JSONObject erpHttpGet(String strurl) {
		String accessToken = null;
		JSONObject demoJson = null;
		try {
			// 设置链接
			URL urlGet = new URL(strurl);
			// 启动链接
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			// 设置链接参数与要求
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30�?
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30�?
			// 链接
			http.connect();
			// 获取返回值json字节流
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			// 转化成字符串
			String message = new String(jsonBytes, "UTF-8");
			// 转化成json对象然后返回accessToken属性的值
			demoJson = JSON.parseObject(message);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return demoJson;

	}

	@SuppressWarnings("unused")
	public static String toSingle(String xml) {
		// TODO 结算单用
		String groupcode = "CYJY"; // 集团编码
		String account = "CYJY"; // 账套编码
		try {
			String url = getPfxxUrl(account, groupcode);
			HttpURLConnection connection = null;
			BufferedOutputStream out = null;
			BufferedInputStream input = null;
			try {
				URL realURL = new URL(url);
				connection = (HttpURLConnection) realURL.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-type", "text/xml");
				connection.setRequestMethod("POST");

				out = new BufferedOutputStream(connection.getOutputStream());
				input = new BufferedInputStream(new ByteArrayInputStream(xml.getBytes("UTF-8")));
				int length;
				byte[] buffer = new byte[1000];
				while ((length = input.read(buffer, 0, 1000)) != -1) {
					out.write(buffer, 0, length);
				}
			} catch (Exception e) {
				throw new IOException(e.getMessage());
			} finally {
				if (input != null) {
					input.close();
				}
				if (out != null) {
					out.close();
				}
				if (connection != null) {
					connection.disconnect();
				}
			}

			// 获取回执
			InputStream in = connection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			StringBuilder temp = new StringBuilder();
			String line = bufferedReader.readLine();
			while (line != null) {
				temp.append(line).append("\r\n");
				line = bufferedReader.readLine();
			}
			String result = new String(temp);
			bufferedReader.close();
			System.out.println(result);
			DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docbuilder = docfactory.newDocumentBuilder();
			InputSource source = new InputSource(new ByteArrayInputStream(result.getBytes("UTF-8")));
			Document backMsg = docbuilder.parse(source);

			NodeList resultElements = backMsg.getElementsByTagName("resultcode");
			NodeList resultdescElements = backMsg.getElementsByTagName("resultdescription");
			NodeList contentElements = backMsg.getElementsByTagName("content");

			Element resultElement = (Element) resultElements.item(0);
			Element resultdescElement = (Element) resultdescElements.item(0);
			Element contentElement = (Element) contentElements.item(0);
			String resultcode = resultElement.getTextContent();
			String resultdescription = resultdescElement.getTextContent();
			String content = contentElement.getTextContent();
			return content;
		} catch (Exception ex) {
			return null;
		}
	}

	// 获取接口地址
	private static String getPfxxUrl(String account, String groupCode) {
		// return "http://106.55.18.239/service/XChangeServlet?account=A2&groupcode=cy
		// 测试"http://159.75.81.78/service/XChangeServlet?account=CYJY&groupcode=CYJY
		return "http://159.75.81.78/service/XChangeServlet?account=" + account + "&groupcode=" + groupCode;
	}

}
