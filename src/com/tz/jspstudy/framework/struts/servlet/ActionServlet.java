package com.tz.jspstudy.framework.struts.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tz.jspstudy.framework.struts.bean.ActionMapping;

/**
 * 类描述：  整个框架的前端控制器，用来接收所有的请求，然后给到请求处理器
 * 类名称：com.tz.jspstudy.framework.struts.servlet.ActionServlet       
 * 创建人：keven  
 * 创建时间：2016年7月30日 下午3:27:30     
 * @version   V1.0
 */
public class ActionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private ActionMapping mapping = null;
	private DispatchProcessor dispatchProcessor = new DispatchProcessor();
	
	public ActionServlet() {
		super();
	}
	
	public void destroy() {
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		//请求传递给RquestProcessor处理
		System.out.println("已经进入ActionServlet sevice方法-准备分发请求");
		dispatchProcessor.processor(request, response);	
	}

	//系统启动的时候 就会初始化ActionSevelet 
	//加载我们的struts-config配置文件
	public void init() throws ServletException {
		System.out.println("初始化ActionServlet(所有的请求入口-准备加载struts-config配置文件)");
		//读取配置文件
		//获得项目的部署路径
		String realPath = this.getServletContext().getRealPath("/WEB-INF");		
		//得到封装到的mapping
		mapping = new XMLParser().parseXML(realPath);
		dispatchProcessor.setMapping(mapping);
		System.out.println("初始化ActionServlet(所有的请求入口-加载struts-config配置文件完毕)");
	}

}
