package com.tz.jspstudy.framework.struts.bean;

/**
 * 
 * 类描述：  ActionConfig对应的是struts-config.xml中的配置文件action配置
 * 类名称：com.tz.jspstudy.framework.struts.bean.ActionConfig       
 * 创建人：keven  
 * 创建时间：2016年8月1日 下午7:17:19     
 * @version   V1.0
 */
public class ActionConfig {
	
	private String path;
	private String type;
	private String name;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
