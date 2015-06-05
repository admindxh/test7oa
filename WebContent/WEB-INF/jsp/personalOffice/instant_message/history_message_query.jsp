<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--即时消息--历史消息
--%>

<div style="width:99%;margin: 10px auto;">
<c:forEach var="m" items="${requestScope.msgs }" >
	
	<div style="margin-left: 10px;margin-top: 2px;margin-bottom: 2px;">
		<span style="color: blue;"><my:outTrueName id="${m.sendUid}"/>:</span>
		<span class="label">
	   	 <fmt:formatDate value="${m.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
		</span>
	</div>	

	<div class="well well-small">
		<my:scriptEscape value="${m.messageContent}" />
	</div>
	
		
	
		
</c:forEach>
</div>



