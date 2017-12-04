<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <title>struts1登陆Demo</title>	    
</head>
<body>
	<h1>struts1登陆Demo</h1>
	<form name="loginForm" method="post" action="login.do" >
		用户: <input type = "text" class = "form-control"  name="userName" >
		<br><br>
		密码: <input type = "password" class = "form-control" name="password">
		<br><br>
		<input type = "submit" value= "登陆">
	</form>  
</body>
</html>
