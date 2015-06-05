<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--消息类型判断 --%>

<c:choose>
	<c:when test="${m.msgType==1}">
		<a href="msgwarn/show.do?id=${m.id }&rel=${param.rel}_show" target="navTab" rel="${param.rel}_show">
			您有新通知，请及时查看！
		</a>
	</c:when>
	<c:when test="${m.msgType==2}">
		<a href="msgwarn/show.do?id=${m.id }&rel=${param.rel}_show" target="navTab" rel="${param.rel}_show">
			您有新会议，请及时查看！
		</a>
	</c:when>
	<c:when test="${m.msgType==3}">
		<a href="msgwarn/show.do?id=${m.id }&rel=${param.rel}_show" target="navTab" rel="${param.rel}_show">
			有新发布的新闻，请及时查看！
		</a>
	</c:when>
	<c:when test="${m.msgType==4}">
		<a href="msgwarn/show.do?id=${m.id }&rel=${param.rel}_show" target="navTab" rel="${param.rel}_show">
			您有新共享日志，请及时查看！
		</a>
	</c:when>
	<c:when test="${m.msgType==60}">
		<a href="msgwarn/show.do?id=${m.id }&rel=${param.rel}_show" target="navTab" rel="${param.rel}_show">
			您有新的工作流程消息，请及时查看！
		</a>
	</c:when>
	<c:otherwise>无此类型</c:otherwise>
</c:choose>