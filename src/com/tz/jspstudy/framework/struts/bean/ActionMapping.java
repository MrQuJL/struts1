package com.tz.jspstudy.framework.struts.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 类描述：  ActionMapping缓存了所有struts-config.xml的配置,并通过KEY建立了联系
 * 类名称：com.tz.htmlservlet.framework.struts.bean.ActionMapping       
 * 创建人：keven  
 * 创建时间：2016年7月30日 下午3:47:41     
 * @version   V1.0
 */
public class ActionMapping {
	//key是name
	private Map<String,FormBeanConfig> formMap = new HashMap<String,FormBeanConfig>();
	
	//key是path
	private Map<String,ActionConfig> actionMap = new HashMap<String,ActionConfig>();
	
	public void setFormBeanConfig(FormBeanConfig config) {
		formMap.put(config.getName(), config);
	}
	
	public void setActionConfig(ActionConfig config) {
		actionMap.put(config.getPath(), config);
	}
	
	public FormBeanConfig getFormBeanConfig(String name) {
		return formMap.get(name);
	}
	
	public ActionConfig getActionConfig(String path) {
		return actionMap.get(path);
	}
	
	
	
	
	
}
