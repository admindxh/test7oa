<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--即时消息--查看消息
--%>

<div style="margin-top: 10px;margin-left: 10px;">
	<c:choose>
		<c:when test="${param.type==1 }">
			<span style="font-weight: bold;">发送人:</span>
				<a  href="instantMessage/addPage.do?userId=${requestScope.msg.sendUid }&rel=${param.rel}" target="dialog"  rel="${param.rel}" title="回复消息">
					<my:outTrueName id="${requestScope.msg.sendUid}"/>
				</a>
		</c:when>
		<c:otherwise>
			<span style="font-weight: bold;">接收人:</span>
			<a  href="instantMessage/addPage.do?userId=${requestScope.msg.sendUid }&rel=${param.rel}" target="dialog"  rel="${param.rel}" title="发送即时沟通">
				<my:outTrueName id="${requestScope.msg.sendUid}"/>
			</a>
		</c:otherwise>
	</c:choose>
	<div class="div_sendUser">
		<span style="font-weight: bold;">发送时间：</span><fmt:formatDate value="${requestScope.msg.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
	</div>
	
</div>
<div class="divider"></div>
<div style="margin-left: 10px;margin-right: 10px;">
	<my:scriptEscape value="${requestScope.msg.messageContent}" />
</div>
		
	
				