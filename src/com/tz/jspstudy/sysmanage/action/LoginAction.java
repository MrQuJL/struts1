package com.tz.jspstudy.sysmanage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tz.jspstudy.framework.struts.servlet.Action;
import com.tz.jspstudy.framework.struts.servlet.ActionForm;
import com.tz.jspstudy.framework.struts.servlet.ActionForward;
import com.tz.jspstudy.sysmanage.formbean.LoginForm;  

/**
 * 类描述：  登陆和用户管理的控制器
 *		   注意这里不是一个servlet ，是一个普通的java类 extends Action
 * 类名称：com.tz.htmlservlet.sysmanage.action.LoginAction       
 * 创建人：keven  
 * 创建时间：2016年7月30日 下午7:26:42     
 * @version   V1.0
 */
public class LoginAction extends Action {

	
	public ActionForward execute(ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		LoginForm loginForm = (LoginForm)form;
		String userName = loginForm.getUserName();
		String password = loginForm.getPassword();
		
		System.out.println("已经进入了登陆验证业务处理类LoginAction，进行用户名和密码的验证");
		System.out.println("loginForm.getUserName()----"+loginForm.getUserName());
		System.out.println("loginForm.getPassword()---"+loginForm.getPassword());
		System.out.println("已经进入了登陆验证业务处理类LoginAction，验证完毕，准备forward跳转");

 		if(userName.equals("admin")&&password.equals("admin")){
			return new ActionForward("success.html",false);
		}else{
			return new ActionForward("error.html");
		}
		 
	}
	
	
}
