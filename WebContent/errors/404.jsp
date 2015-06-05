<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" session="false"%>
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
	<title>404 没有找到页面-<fmt:message key="app.name" bundle="${commonBundle}"/></title>
	<link href="favicon.ico" rel="SHORTCUT ICON" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="refresh" content="3;url=<%=basePath%>main.do">
	<style type="text/css">
		body {
				background: #f5f5f5;
			}
			.main {
				margin: 0px auto; width: 1000px;
			}
			.ivy960 {
				margin: 0px auto; width: 960px; overflow: hidden; text-align:center;
			}
			.m20 {
				margin-top:50px
			}
			.errorCon {
				padding-bottom: 0px; margin-top: 79px; padding-left: 340px; padding-right: 0px; background: url(resource/images/error/errorBg.png) no-repeat 0px 0px; height: 198px; padding-top: 104px;
			}
			.errorCon span {
				line-height: 24px; display: block;
			}
			.errorCon span a {
				font-family: 宋体; color: #2a78b4;
			}
			.errorCon span a:hover {
				color: #fd5151;
			}
	</style>
</head>

<body>
	<div class=main>
		<div class=errorCon>
			<p>
				<span class=sTime><em id=time_out>3</em>&nbsp;秒后&nbsp;页面自动跳转至
						<a href="main.do">首页</a>
				</span>
			</p>
		</div>
		<hr />
		<div class="ivy960 m20">
			<img src="resource/images/logo/logo.png">
		</div>
	</div>
</body>
</html>

