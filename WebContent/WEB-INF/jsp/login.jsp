<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/commons/jstl_message_tld.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<title><fmt:message key="app.name" bundle="${commonBundle}"/></title>
	<meta http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="favicon.ico" rel="SHORTCUT ICON" />
	<link type="text/css" rel="stylesheet" href="resource/images/login/login.css">
	<script src="resource/js/jquery/jquery-1.10.2.min.js"></script>
	<script src="resource/js/artDialog/jquery.artDialog.js?skin=default"></script>
	<script src="resource/js/security/security.js"></script>
	<script src="resource/js/login.js"></script>
</head>

<body class="loginPageBody" >
	<!--拉开屏幕  -->
	<%--<div id="divlm_left" style="float:left;width:50%;height:100%;left: 0px; position: absolute; top: 0px; background-color: #0066ff;z-index:9999;"></div>
	<div id="divlm_right" style="float:right;width:50%;height:100%;right: 0px; position: absolute; top: 0px; background-color: #0066ff;z-index:9999;"></div>
	<script type="text/javascript"> 
		$("#divlm_left").animate({width:'-50%'}, 4000,function(){$(this).remove();});
		$("#divlm_right").animate({width:'-50%'}, 4000,function(){$(this).remove();});
	</script>
	
	--%><!-- 结束 -->
	<div class="LoginWrapbg ">
		<div style="width: 100%; height: 50%; left: 0; top: 0; z-index: -1;"></div>
		<div style="width: 442px; height: 440px; margin: 0 auto; margin-top: -260px;">
			<img src="resource/images/login/images/logo.png" style="margin-left: 50px;"/>
			<div class="welcome">&nbsp; </div>
			<div class="loginBar">
				<div id="divcenter_login" >
					<div class="fieldWrap" >
						<div class="lable" >用户名：</div>
						<div class="inputWrap">
							<input type="text"  id="username" class="inputText" value="admin">
						</div>
					</div>
					<div class="fieldWrap" >
						<div class="lable">密码：</div>
						<div class="inputWrap">
							<input type="password"  id="pwd" class="inputText"  value="123456">
						</div>
					</div>
					
					<%-- 
						<div id="verifyCodeWrap" class="fieldWrap verifyCodeWrap">
						 <div class="lable">验证码：</div>
						 <div class="inputWrap" style="width: 100px;">
							<input type="text"  id="vercode" class="inputText" name="vercode" style="width: 100px;" />
						 </div>
						 <div class="verifyCode" >
							<img  id="img_vercode" onfocus="this.src='sy_login/imgNum.do?_t='+new Date()"  width="100px;"/>
						</div>
					</div>
					--%> 
					<div id="loginBtnWrap" class="fieldWrap">
					  
					  <a id="loginBtn" class="inline-block" href="javascript:void(0);"  >登录</a>
					
					</div>
				</div>
				<div id="divcenter_loginin" style="display: none;">
					<div style="margin-left: 80px;margin-top: 40px;">
						<img src="resource/images/onload.gif" alt="正在登陆中..." style="margin-left: 40px;"/>
						<div style="margin-top: 20px;margin-left:10px;font-size: 20px;font-family: 楷体">正在登陆，请稍等...</div>
					</div>
				</div>
				
			</div>
		</div>
		<div class="copyright">
			<div class="center">Copyright &copy; 2013-2014  <a href="<fmt:message key="app.name.url" bundle="${commonBundle}"/>" target="_blank" ><fmt:message key="app.name" bundle="${commonBundle}"/></a></div>
		</div>
	</div>
	
</body>
</html>
