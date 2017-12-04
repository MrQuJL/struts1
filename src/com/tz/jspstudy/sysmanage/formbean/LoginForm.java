package com.tz.jspstudy.sysmanage.formbean;

import com.tz.jspstudy.framework.struts.servlet.ActionForm;

public class LoginForm extends ActionForm{
	private String userName;
	private String password;
	
	/**  
	 * @return the userName  
	 */
	public String getUserName() {
		return userName;
	}
	/**  
	 * @param userName the userName to set  
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**  
	 * @return the password  
	 */
	public String getPassword() {
		return password;
	}
	/**  
	 * @param password the password to set  
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}