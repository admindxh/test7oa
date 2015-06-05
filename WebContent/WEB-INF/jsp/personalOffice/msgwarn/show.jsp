<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--消息提醒 -- 查看
--%>

<div class="div_titlebold">系统消息提醒</div>
<div class="div_sendUser">&nbsp;&nbsp;<fmt:formatDate value="${requestScope.msg.msgTime }" pattern="yyyy-MM-dd HH:mm:ss" />	</div>	

<div class="divider"></div>
	
<div style="margin:10px;" >
	系统提醒您：
	
	<c:choose>
		<c:when test="${requestScope.msg.msgType==1}">
			<a href="notice/show.do?id=${msg.tableId }" target="dialog"  rel="${param.rel }" title="通知详情">
				您有新通知，请点击查看！
			</a>
		</c:when>
		<c:when test="${requestScope.msg.msgType==2}">
			<a href="meeting/find.do?id=${msg.tableId }" target="dialog" rel="${param.rel }" title="会议详情">
				您有新会议，请点击查看详情！
			</a>
		</c:when>
		<c:when test="${requestScope.msg.msgType==3}">
			<a href="news/look.do?id=${msg.tableId }" target="dialog" rel="${param.rel }" title="新闻详情">
				有最新新闻发布，请点击查看详情！
			</a>
		</c:when>
		<c:when test="${requestScope.msg.msgType==4}">
			<a href="journal/show.do?id=${msg.tableId }" target="dialog" rel="${param.rel }" title="日志详情">
				您有新共享日志，请点击查看详情！
			</a>
		</c:when>
		<c:when test="${requestScope.msg.msgType==5}">
			<a href="vote/tp.do?id=${msg.tableId }&rel=${param.rel}" target="dialog" rel="${param.rel }" title="投票">
				您有新投票，请点击查看详情！
			</a>
		</c:when>
		
		<c:when test="${requestScope.msg.msgType==60}">
			<a href="work/show.do?processInstanceId=${msg.tableId }" target="dialog" rel="${param.rel}" title="工作详情">
				您发起的工作过已完成，请点击查看详情！
			</a>
		</c:when>
		
		<c:when test="${requestScope.msg.msgType==20}">
			<a href="workPlan/show.do?id=${msg.tableId }" target="dialog" rel="${param.rel}" title="工作详情">
				您有新的工作计划，请点击查看详情！
			</a>
		</c:when>
		
		<c:when test="${requestScope.msg.msgType==31}">
			<a href="project/show.do?id=${msg.tableId }" target="dialog" rel="${param.rel}" title="工作详情">
				您有新的项目，请点击查看详情！
			</a>
		</c:when>
		
		<c:when test="${requestScope.msg.msgType==32}">
			<a href="projectTask/show.do?id=${msg.tableId }" target="dialog" rel="${param.rel}" title="工作详情">
				您有新的项目任务，请点击查看详情！
			</a>
		</c:when>
		
		<c:otherwise>无此类型：${requestScope.msg.msgType }</c:otherwise>

	</c:choose>
	
</div>


