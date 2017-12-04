package com.tz.jspstudy.framework.struts.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tz.jspstudy.framework.struts.bean.ActionConfig;
import com.tz.jspstudy.framework.struts.bean.ActionMapping;
import com.tz.jspstudy.framework.struts.bean.FormBeanConfig;
 

/**
 * 类描述：  请求分发处理器: 根据路径选择控制器，处理请求并且返回
 * 类名称：com.tz.htmlservlet.framework.struts.servlet.RequestProcessor       
 * 创建人：keven  
 * 创建时间：2016年7月30日 下午4:11:05     
 * @version   V1.0
 */
public class DispatchProcessor {
	private ActionMapping mapping = null;

	// 这个MAP是缓存所有的Action的实例的,因为Action写成单例模式
	private Map<String, Action> actionMap = new HashMap<String, Action>();

	public DispatchProcessor() {
	}
	
	public void setMapping(ActionMapping mapping) {
		this.mapping = mapping;
	}

	public void processor(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//http://localhost:8080/studyJsp/login.do
		//login
		// 获得Action
		// 获得请求的路径地址
		System.out.println("进入请求分发器DispatchProcessor--解析请求方法,去ActionConfig对象匹配");

		String path = null;
		String uri = request.getRequestURI(); // 得到/工程名/xxx.do
		String contenPath = request.getContextPath(); // 得到 /工程名
		uri = uri.substring(contenPath.length()); // 去掉工程名得到 /xxx.do
		path = uri.substring(0, uri.length() - 3); // 去掉.do 获得/xxx
		System.out.println("进入请求分发器DispatchProcessor--解析完请求方法,请求的路径为"+path);

		// 根据路径获取ActionConfig对象
		ActionConfig config = mapping.getActionConfig(path);

		// config为空则说明没有配置这个路径
		if (config == null) {
			throw new ServletException("struts-config.xml没有配置" + path + "路径");
		}

		Action action = actionMap.get(path);
		// 用路径在缓存中找action的实例，没找到说明是第一次用户请求这个Action,则反射创建缓存
		if (action == null) {
			// 反射的代码
			action = this.createAction(config);
			// 缓存
			actionMap.put(path, action);
		}

		// 获得ActionForm设置参数
		ActionForm form = null;
		// 获得ActionConfig的name，如果有就设置参数
		if (config.getName() != null) {
			// 用name查找出对应的form-bean标签
			FormBeanConfig cfg = mapping.getFormBeanConfig(config.getName());
			if (cfg == null) {
				throw new ServletException(
						"action标签的name必须匹配一个form-bean标签的name");
			}

			// 反射创建ActionForm的实例
			form = this.createActionForm(cfg);
			// 反射设置好参数
			this.setParameter(form, request);
		}

		// 调用Action的execute获得ActionForward转发/重定向
		try {
			System.out.println("进入请求分发器--准备调用对应的业务处理Action");
			ActionForward forward = action.execute(form, request, response);
			
			if (forward.isForward()) {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			} else {
				response.sendRedirect(forward.getPath());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setParameter(ActionForm form, HttpServletRequest request) {
		// 得到所有的参数名称
		Enumeration<String> enu = request.getParameterNames();
		// 迭代所有名称
		while (enu.hasMoreElements()) {
			String name = enu.nextElement();
			
			//过滤掉method参数
			if ("method".equals(name)) {
				continue;
			}
			
			// 根据名称拼接set方法
			String setMethodName = "set" + name.substring(0, 1).toUpperCase()
					+ name.substring(1);

			// 得到参数的值，反射调用form的set方法传递值
			String[] array = request.getParameterValues(name);
			Class clz = form.getClass();
			Method method = null;
			try {
				if (array.length > 1) {
					method = clz.getMethod(setMethodName, String[].class);
					method.invoke(form, array);
				} else {
					method = clz.getMethod(setMethodName, String.class);
					method.invoke(form, array[0]);
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

		}

	}

	private ActionForm createActionForm(FormBeanConfig config) {
		String type = config.getType();
		ActionForm form = null;
		try {
			form = (ActionForm) Class.forName(type).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return form;
	}

	private Action createAction(ActionConfig config) {
		String type = config.getType();
		Action action = null;
		try {
			action = (Action) Class.forName(type).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return action;
	}

}
