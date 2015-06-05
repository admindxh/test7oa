<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--日程安排--详情
--%>

<div class="div_titlebold"><c:out value="${requestScope.s.schTitle }" /></div>
<div class="div_sendUser">	
	日程类型：<my:outSelectValue id="${requestScope.s.schType }"/><br/>
	开始时间：<fmt:formatDate value="${requestScope.s.startTime }" pattern="yyyy-MM-dd HH:mm:ss" /><br/>
	结束时间：<fmt:formatDate value="${requestScope.s.endTime }" pattern="yyyy-MM-dd HH:mm:ss" /><br/>
	提醒时间：<fmt:formatDate value="${requestScope.s.warnTime }" pattern="yyyy-MM-dd HH:mm:ss" />
</div>
<div class="divider"></div>
	
<div style="margin:10px;" >
	<my:scriptEscape value="${requestScope.s.schContent }" />
</div>

					
		