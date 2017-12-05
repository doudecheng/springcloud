package com.xqt.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 使用dom4j解析xml
 * 
 * @author Andy
 *
 */
public class Dom4jXml {

	private static Object innerEntity;

	public static void xml2object(String xml, Object currObj)
			throws NoSuchMethodException, SecurityException, Exception {
		innerEntity = currObj;
		Document doc = DocumentHelper.parseText(xml);
		Element rootElt = doc.getRootElement(); // 获取根节点
		getNodes(rootElt);
		System.out.println(rootElt.getName());
	}

	/**
	 * 根据节点名称递归遍历所有的节点
	 * 
	 * @param node
	 * @throws Exception
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("unchecked")
	public static void getNodes(Element node) throws NoSuchMethodException, SecurityException, Exception {
		Class<?> cls = innerEntity.getClass();
		List<Element> childElements = node.elements();
		if (!childElements.isEmpty()) {
			for (Element ex : childElements) {
				String name = ex.attributeValue("name");
				String value = ex.attributeValue("value");
				if(null != name){
					Field field = cls.getDeclaredField(name);
					String s = field.getType().toString(); 
					// 如果类型是String
					if (s.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
					 // 拿到该属性的setter方法
					 Method m = (Method) cls.getMethod("set" + getMethodName(field.getName()),String.class);
					 m.invoke(innerEntity, value);
					}
				}
				System.out.println("namevalue:" + ex.attributeValue("name"));
				System.out.println("valvalue:" + ex.attributeValue("value"));
				getNodes(ex);
			}
		}

		/*List<Attribute> listAttr = node.attributes();// 当前节点的所有属性的list
		for (Attribute attr : listAttr) {// 遍历当前节点的所有属性
			String name = attr.getName();// 属性名称
			String value = attr.getValue();// 属性的值
			System.out.println("属性名称：" + name + "属性值：" + value);
		}
		// 递归遍历当前节点所有的子节点
		List<Element> listElement = node.elements();// 所有一级子节点的list
		for (Element e : listElement) {// 遍历所有一级子节点
			getNodes(e);// 递归
		}*/
	}

	// 把一个字符串的第一个字母大写、效率是最高的、
	private static String getMethodName(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}

	public static Object getInnerEntity() {
		return innerEntity;
	}

}
