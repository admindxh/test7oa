<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%--系统登录超时--%>
<%
	//判断需要返回是页面 还是json格式消息
	boolean isJson=false;//是否需要返回json格式数据
	String accept=request.getHeader("Accept");
	System.out.println("登陆超时===url:"+request.getRequestURI());
	System.out.println("登陆超时===accept:"+accept);
	if(accept==null){
		isJson=true;
	}else{
		if(accept.contains("application/json")){
			isJson=true;
		}
	}
	if(isJson){
		response.setContentType("text/plain;charset=UTF-8");
%>{
	"statusCode":"301", 
	"message":"登陆超时！请重新登陆！",
	"rows":[],
	"total":0
}	
<%
	}else{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">
    <title>登陆超时</title>
   	<link href="favicon.ico" rel="SHORTCUT ICON" />
    <meta http-equiv=content-type content="text/html; charset=utf-8">

</head>	
<body>	
	<div id="maindiv" style="margin: 0px;font-size: 16px;	font-family: 楷体,sans-serif;width: 100%;	height: 100%;  ">
		
		<div style="margin: 200px auto; 
			width: 400px;
			text-align: center;
			background-color: #EEEEEE;
		    border-radius: 6px;
		    color: inherit;
		    font-size: 18px;
		    font-weight: 200;
		    line-height: 30px;
		    margin-bottom: 30px;
		    padding: 60px;">
			<span style="font-weight: bold;">登陆超时！<a href="sy_login.do">重新登陆</a></span>
		</div>
		
	</div>
	<script type="text/javascript">
	<!--
		if(typeof(androidWebObject) != "undefined"){
			androidWebObject.overtime();
			
			document.getElementById("maindiv").innerHTML="<span style='color:red'>登陆超时</span>";
		}else{
			if(confirm("登陆超时！请重新登陆！")){
				window.location.href = "sy_login.do";
				
			}
		}
	//-->
	</script>
</body>
</html>
<%} %>
