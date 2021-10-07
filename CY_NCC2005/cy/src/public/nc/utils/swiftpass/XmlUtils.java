/**
 * Project Name:pay-protocol
 * File Name:Xml.java
 * Package Name:cn.swiftpass.pay.protocol
 * Date:2014-8-10下午10:48:21
 *
*/

package nc.utils.swiftpass;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import com.alibaba.fastjson.JSON;

import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.NoteDataUtils;
import nc.utils.commonplugin.returnparam.TwoTuple;
import nc.vo.cy.creditcontract.pojo.CContractQuerydelmsgsPojo;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nccloud.itf.cy.cy.ICreditcontracthvoMaintain;

/**
 * ClassName:Xml Function: XML的工具方法
 * 
 * @author
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class XmlUtils {

	/**
	 * <一句话功能简述> <功能详细描述>request转字符串
	 * 
	 * @param request
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String parseRequst(HttpServletRequest request) {
		String body = "";
		try {
			ServletInputStream inputStream = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			while (true) {
				String info = br.readLine();
				if (info == null) {
					break;
				}
				if (body == null || "".equals(body)) {
					body = info;
				} else {
					body += info;
				}
			}
			NoteDataUtils.addLog(body, "payHttpServletRequest", false, null);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return body;
	}

	public static String parseXML(SortedMap<String, String> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"appkey".equals(k)) {
				sb.append("<" + k + ">" + parameters.get(k) + "</" + k + ">\n");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 从request中获得参数Map，并返回可读的Map
	 * 
	 * @param request
	 * @return
	 */
	public static TwoTuple<String, SortedMap> getParameterMap(String request) {
		String respStr = "";
		// 参数Map
		Map mapType = JSON.parseObject(request, Map.class);
		// 返回值Map
		SortedMap returnMap = new TreeMap();
		Iterator entries = mapType.entrySet().iterator();
		Map.Entry entry;
		String openid = "";
		String name = "";
		String value = "";
		String pk_detail = "";
		String planamount = "";
		Object valueObj = null;
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value.trim());
			String valueobj = (String) valueObj;
			openid = valueobj.split("#")[6].split("=")[1];
			String idcard = valueobj.split("#")[4].split("=")[1];
			try {
				// 查询到金额和表体pk
				CContractQuerydelmsgsPojo contmsgs = CyCommonUtils.getInstance(ICreditcontracthvoMaintain.class)
						.ccontractQuerymsgs(idcard);
				if (CyCommonUtils.isNotEmpty(contmsgs)) {
					pk_detail = contmsgs.getPk_detail();
					planamount = contmsgs.getPlanamount();
				} else {
					respStr = "没有可执行的付款计划";
				}
			} catch (BusinessException e) {
				respStr = e.getMessage();
			}
			returnMap.put(name, value.trim() + "#pk_detail=" + pk_detail);
		}

		returnMap.put("body", "商品描述");
		returnMap.put("charset", "UTF-8");
		returnMap.put("device_info", "123123");
		returnMap.put("is_minipg", "1");
		returnMap.put("is_raw", "1");
		returnMap.put("mch_create_ip", "106.55.18.239");// 商户ip
		returnMap.put("mch_id", AppletsParams.mch_id);
		returnMap.put("nonce_str", String.valueOf(new Date().getTime()));
		returnMap.put("notify_url", AppletsParams.notify_url);
		// 商户订单号，由NC系统生成
		returnMap.put("out_trade_no", CyCommonUtils.generatePK());
		returnMap.put("service", "pay.weixin.jspay");
		returnMap.put("sign_type", "RSA_1_256");
		returnMap.put("sub_appid", AppletsParams.AppID);
		// 用户openid
		returnMap.put("sub_openid", openid);

		String paymoney = new UFDouble(planamount).multiply(new UFDouble("100")).setScale(0,
				new UFDouble().ROUND_HALF_UP) + "";// 含税金额 +"";

		returnMap.put("total_fee", paymoney);// 总金额 实际金额用planamount*100
		returnMap.put("version", "2.0");

		return new TwoTuple(respStr, returnMap);
	}

	/**
	 * 转XMLmap
	 * 
	 * @author
	 * @param xmlBytes
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> toMap(byte[] xmlBytes, String charset) throws Exception {
		SAXReader reader = new SAXReader(false);
		InputSource source = new InputSource(new ByteArrayInputStream(xmlBytes));
		source.setEncoding(charset);
		Document doc = reader.read(source);
		Map<String, String> params = XmlUtils.toMap(doc.getRootElement());
		return params;
	}

	/**
	 * 转MAP
	 * 
	 * @author
	 * @param element
	 * @return
	 */
	public static Map<String, String> toMap(Element element) {
		Map<String, String> rest = new HashMap<String, String>();
		List<Element> els = element.elements();
		for (Element el : els) {
			rest.put(el.getName().toLowerCase(), el.getText());
		}
		return rest;
	}

	public static String toXml(Map<String, String> params) {
		StringBuilder buf = new StringBuilder();
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		buf.append("<xml>");
		for (String key : keys) {
			buf.append("<").append(key).append(">");
			buf.append("<![CDATA[").append(params.get(key)).append("]]>");
			buf.append("</").append(key).append(">\n");
		}
		buf.append("</xml>");
		return buf.toString();
	}
}
