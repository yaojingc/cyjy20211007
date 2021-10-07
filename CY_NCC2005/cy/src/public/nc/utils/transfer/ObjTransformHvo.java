package nc.utils.transfer;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

import nc.vo.cy.pojo.RefPOJO;
import nc.vo.cy.pojo.SchoolPOJO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

/*
 * 将接收到的POJO转换成系统节点的HVO
 */
public class ObjTransformHvo {

	/**
	 * 参数vo转换成接口老师申请单vo
	 * 
	 * @param hvo2
	 * 
	 * @param class1
	 * 
	 * @param paramVO
	 * @param object
	 * @param thvo
	 * @throws IllegalAccessException
	 * @throws Exception
	 */
	public static Object dataTransform(Object obj, Object hvo) throws IllegalAccessException, Exception {
		Class object = obj.getClass();// 入参数据类
		Class hvoObj = hvo.getClass(); // 转换后数据类
		Field[] fields = object.getDeclaredFields();
		for (Field field : fields) {
			String fieldname = field.getName();
			field.setAccessible(true); // 私有属性必须设置访问权限
			String value = "";
			String typeName = field.getType().getName();
			if (null == field.get(obj) || "".equals(field.get(obj)) || typeName.equals("nc.vo.cy.pojo.UserInfoPOJO")) {
				continue;
			}
			if (typeName.equals("nc.vo.cy.pojo.RefPOJO")) {
				RefPOJO rf = (RefPOJO) field.get(obj);
				value = rf.getValue();
			} else if (typeName.equals("nc.vo.cy.pojo.SchoolPOJO")) {
				SchoolPOJO sc = (SchoolPOJO) field.get(obj);
				value = sc.getValue();
			} else if (fieldname.equals("dbilldate") && StringUtils.isNotBlank(field.get(obj).toString())) {
				value = field.get(obj).toString() + " 00:00:00";
			} else if ((fieldname.equals("startdate") || fieldname.equals("enddate") || fieldname.equals("visitdate"))
					&& StringUtils.isNotBlank(field.get(obj).toString())) {
				value = field.get(obj).toString() + " 00:00:00";
			} else {
				value = field.get(obj).toString();
			}

			Object objVal = fieldTypeTranslater(hvoObj, fieldname, value);

			if (null != objVal) {
				reflexToSetValue(hvo, fieldname, objVal);
			}
		}
		return hvo;
	}

	/**
	 * 根据目的vo字段类型翻译字段
	 * 
	 * @param hvoObj
	 * @param fieldname
	 * @param value
	 * @return
	 */
	public static Object fieldTypeTranslater(Class hvoObj, String fieldname, String value) {
		Object objVal = null;
		Field[] hf = hvoObj.getDeclaredFields();
		for (Field f : hf) {
			String fieldnamethvo = f.getName();
			f.setAccessible(true); // 私有属性必须设置访问权限
			if (fieldnamethvo.equals(fieldname)) {
				String transType = f.getType().getName();
				switch (transType) {
				case "nc.vo.pub.lang.UFDateTime":
					objVal = new UFDateTime(value);
					break;
				case "nc.vo.pub.lang.UFDate":
					objVal = new UFDate(value.toString());
					break;
				case "nc.vo.pub.lang.UFBoolean":
					objVal = new UFBoolean(value.toString());
					break;
				case "nc.vo.pub.lang.UFDouble":
					objVal = new UFDouble(value.toString());
					break;
				case "java.lang.Integer":
					objVal = new Integer(value);
					break;
				default:
					objVal = value;
				}
				break;
			}
		}
		return objVal;
	}

	/**
	 * 通过反射给属性赋值
	 * 
	 * @param obj       实体数据
	 * @param fieldName 字段名
	 * @return
	 */
	public static void reflexToSetValue(Object obj, String fieldName, Object value) throws Exception {
		Class object = obj.getClass();
		// 获取Bean的某个属性的描述符
		PropertyDescriptor proDescriptor = new PropertyDescriptor(fieldName, object);
		// 获得用于读取属性值的方法
		Method writeMethod = proDescriptor.getWriteMethod();
		// 读取属性值
		writeMethod.invoke(obj, value);
	}
}
