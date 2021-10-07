package nc.utils.commonplugin;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class XmlParsers {
	public void ParseXml(String xmlStr) throws DocumentException {
		Document document = DocumentHelper.parseText(xmlStr);
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		for (Element child : childElements) {
			List<Attribute> attributeList = child.attributes();
			for (Attribute attr : attributeList) {
				System.out.println(attr.getName() + ": " + attr.getValue());
			}
			System.out.println("id: " + child.attributeValue("id"));

			List<Element> elementList = child.elements();
			for (Element ele : elementList) {
				System.out.println(ele.getName() + ": " + ele.getText());
			}
			System.out.println();

			System.out.println("title" + child.elementText("title"));
			System.out.println("author" + child.elementText("author"));
			System.out.println();
		}
	}

	public Object refectAttbute(Element ele, Object model)
			throws NoSuchMethodException, SecurityException, InvocationTargetException {
		Field[] field = model.getClass().getDeclaredFields();
		for (int j = 0; j < field.length; j++) {
			String fieldName = field[j].getName();
			String attvalue = ele.attributeValue(fieldName);
			if (attvalue != null && !"".equals(attvalue)) {
				attvalue = attvalue.trim();
			}
			try {
				String methodName = getMethodName(fieldName, "set");
				Method method = model.getClass().getDeclaredMethod(methodName, field[j].getType());
				method.invoke(model, attvalue);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}

		return model;

	}

	/**
	 * @param key        ������
	 * @param MethodType ��ȡ�������ͣ�set or get��
	 * @return �������ƣ�����ʹ��
	 */
	public static String getMethodName(String key, String MethodType) {
		String methodName = "";
		if (key != null && !"".equals(key)) {
			String[] arr = key.split("");
			for (int i = 0; i < arr.length; i++) {
				String temp = arr[i];
				if (i == 1) {
					methodName += temp.toUpperCase();
				} else {
					methodName += temp;
				}
			}
		}
		return MethodType + methodName;
	}

	/**
	 * ��ʽ��XML�ĵ�
	 * 
	 * @param document xml�ĵ�
	 * @param charset  �ַ����ı���
	 * @return ��ʽ����XML�ַ���
	 */
	public static String formatXML(Document document, String charset) {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(charset);
		StringWriter sw = new StringWriter();
		XMLWriter xw = new XMLWriter(sw, format);
		try {
			xw.write(document);
			xw.flush();
			xw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

}
