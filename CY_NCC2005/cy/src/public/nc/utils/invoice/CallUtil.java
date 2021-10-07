package nc.utils.invoice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

public class CallUtil {

	public String callTaxCloudApi(String url, Map<String, String> paramsMap) throws Exception {
		CertificateUtil certificateUtil = new CertificateUtil();
		HttpClient httpClient = certificateUtil.createSSLClientDefault(); // 信任所有https证书
		HttpPost httpPost = new HttpPost(url);

		// 构造POST请求体
		// 签名
		String requestData = "";
		if (paramsMap.containsKey("requestdatas")) {
			requestData = paramsMap.get("requestdatas");
		} else if (paramsMap.containsKey("fpqqlsh")) {
			requestData = paramsMap.get("fpqqlsh");
		}

		if (StringUtils.isEmpty(requestData)) {
			throw new Exception("请求的数据为空！");
		}
		String sign = certificateUtil.sign(requestData); // 针对开票数据做签名
		httpPost.addHeader("sign", sign);

		List<NameValuePair> list = new ArrayList<NameValuePair>();
		Iterator<Entry<String, String>> iterator = paramsMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> elem = iterator.next();
			list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
		}
		if (list.size() > 0) {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
			httpPost.setEntity(entity);
		}
		// 发送http post请求，并得到响应结果
		HttpResponse response = httpClient.execute(httpPost);
		String result = null;
		if (response != null) {
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				result = EntityUtils.toString(resEntity, "UTF-8");
				System.out.println(result);
			}
		}
		return result;
	}

}
