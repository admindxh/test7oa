<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"	+ request.getServerName() + ":" + request.getServerPort()	+ path + "/";
%>
<%--
	模块：工作流程--流程表单查看页面	
--%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>工作流程查看</title>
	
	<script type="text/javascript" src="resource/js/jquery/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="resource/js/android/flowForm_show.js"></script>
	
	<style type="text/css">
		html {
		  font-size: 100%;
		  -webkit-text-size-adjust: 100%;
		      -ms-text-size-adjust: 100%;
		}
		body {
		  margin: 0;
		  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
		  font-size: 14px;
		  line-height: 20px;
		  color: #333333;
		  background-color: #ffffff;
		}
		table {
		  max-width: 100%;
		  background-color: transparent;
		  border-collapse: collapse;
		  border-spacing: 0;
		}
		/* 流程表单样式   */
		table.flowtable{
			width: 600px;
			border-collapse:collapse;
			margin: 0 auto 10px;
			border: 1px solid black;
			height: auto;
		}
		table.flowtable caption{
			font-size: 20px;
			font-weight: bold;
			padding: 10px;
		}
		table.flowtable th{
			border: 1px solid black;
			padding: 10px;
			font-weight: bold;
			text-align:center;
		}
		table.flowtable td{
			border: 1px solid black;
			padding: 10px;
		}
		
		table.flowtable textarea{
			width: 90%;
			height: 30px;
		}
		table.flowtable input{
			width: 90%;
		}
		/* fieldset */
		fieldset.fieldset{padding:3px; margin:5px; border:1px dotted #B8D0D6;}
		fieldset.fieldset legend {font-size:12px ;width:auto;margin-bottom:0;line-height:normal;   padding:2px; border:1px dotted #B8D0D6; font-weight: bold;}
		fieldset.fieldset legend:hover {background-color: #dddddd;}
		
		/* 删除按钮 */
		.btnDel{
			background:url(./resource/images/small/Delete_16x16.png) no-repeat; 
			display:block; 
			width:22px;
			height:20px; 
			margin-top: 5px;
			margin-left: 5px;
			
		}
	
	</style>
	
</head>
<body>
	
	 <c:if test="${!empty requestScope.flowFormHtml }">
		
			<div   id="flowForm"  ><my:scriptEscape value="${requestScope.flowFormHtml }"/></div>
		
	 </c:if> 
	 <c:if test="${empty requestScope.flowFormHtml }">
	 		<div style="width: 150px;margin: 50px auto;font-weight: bold;color: red;">
	 				流程表单不存在
	 		</div>
	 </c:if>		 	
	
</div>

</body>
</html>



									