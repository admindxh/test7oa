
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--菜单管理 --菜单图标查找带回
--%>

<div style="width: 98%;margin: 5px auto;">
		<c:forEach var="i" items="${requestScope.icons }" >
			<a href="javascript:;" onclick="$.bringBack({url:'${i}'})" ><img src="<c:out value='${i}' />"  style="padding: 15px;"/></a>
		</c:forEach>
		
</div>	
	

										
						
					
		

	

