package com.tz.jspstudy.framework.struts.servlet;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.tz.jspstudy.framework.struts.bean.ActionConfig;
import com.tz.jspstudy.framework.struts.bean.ActionMapping;
import com.tz.jspstudy.framework.struts.bean.FormBeanConfig;

/**
 * 类描述： 采用DOM4J解析struts-config.XML文件，把数据封装到ActionMapping中
 * 类名称：com.tz.htmlservlet.framework.struts.servlet.XMLParser       
 * 创建人：keven  
 * 创建时间：2016年7月30日 下午3:45:17     
 * @version   V1.0
 */
public class XMLParser {
	
	public ActionMapping parseXML(String realPath) {
		ActionMapping mapping = new ActionMapping();
		
		//构造SAX解析器
		SAXReader reader = new SAXReader();
		
		try {
			//获得文档对象和根元素
			Document doc = reader.read(realPath+"/struts-config.xml");
			Element root = doc.getRootElement();
			
			//读取form-beans标签
			Element formBeans = root.element("form-beans");
			List<Element> formBeanList = formBeans.elements();
			
			//循环封装对象
			for (Element formBean : formBeanList) {
				String name = formBean.attributeValue("name");
				String type = formBean.attributeValue("type");				
				FormBeanConfig config = new FormBeanConfig();
				config.setName(name);
				config.setType(type);				
				//数据存入ActionMapping
				mapping.setFormBeanConfig(config);
			}
			
			//读取action-mappings
			Element actions = root.element("action-mappings");
			List<Element> actionList = actions.elements();
			for (Element action : actionList) {
				String name = action.attributeValue("name");
				String path = action.attributeValue("path");
				String type = action.attributeValue("type");
				
				ActionConfig config = new ActionConfig();
				config.setName(name);
				config.setPath(path);
				config.setType(type);
				
				//数据封装到mapping中
				mapping.setActionConfig(config);
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return mapping;
	}
}
