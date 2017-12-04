package com.tz.jspstudy.framework.struts.servlet;

/**
 * 类描述：转发或者重定向的路径对象  
 * 类名称：com.tz.jspstudy.framework.struts.servlet.ActionForward       
 * 创建人：keven  
 * 创建时间：2016年7月30日 下午9:50:16     
 * @version   V1.0
 */
public class ActionForward {
	private boolean isForward = true;	//默认是转发
	private String path;
	
	public ActionForward(String path) {
		this.path = path;
	}
	
	public ActionForward(String path,boolean isForward) {
		this.path = path;
		this.isForward = isForward;	//传递false，则是做重定向
	}

	public boolean isForward() {
		return isForward;
	}

	public void setForward(boolean isForward) {
		this.isForward = isForward;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
