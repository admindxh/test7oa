<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%@include file="/WEB-INF/jsp/commons/jstl_message_tld.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<title><fmt:message key="app.name" bundle="${commonBundle}"/></title>
	<link href="favicon.ico" rel="SHORTCUT ICON" />
	<meta http-equiv=Content-Type content="text/html; charset=utf-8">
	
	<link type="text/css" rel="stylesheet" href="resource/images/login/login.css">
	
	
</head>

<body class="loginPageBody">
	
	<div class="LoginWrapbg ">
		<div style="width: 100%; height: 50%; left: 0; top: 0; z-index: -1;"></div>
		<div style="width: 442px; height: 440px; margin: 0 auto; margin-top: -260px;">
			<img src="resource/images/login/images/logo.png" style="margin-left: 50px;"/>
			<div class="welcome">&nbsp; </div>
			<div class="loginBar">
				
				<div >
					<div style="margin-left: 20px;margin-top: 50px;">
						<h3 >当前登陆用户：<my:outLoginInfo type="trueName" /></h3>
					</div>
					<div style="margin-top: 40px;margin-left: 100px;">
						<a href="main.do">直接进入</a><a href="sy_login/out.do" style="margin-left: 30px;">注销登陆</a>
					</div>
				</div>
					
			</div>
		</div>
		<div class="copyright">
			<div class="center">Copyright &copy; 2013-2014  <a href="<fmt:message key="app.name.url" bundle="${commonBundle}"/>" target="_blank"><fmt:message key="app.name" bundle="${commonBundle}"/></a></div>
		</div>
	</div>
</body>
</html>
