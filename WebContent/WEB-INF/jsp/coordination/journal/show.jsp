<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：协同办公--日志管理--详情
--%>

<div class="div_titlebold"><c:out value="${requestScope.j.journalTitle}" /></div>
<div class="div_sendUser"><my:outSelectValue id="${j.journalType }"/> &nbsp;&nbsp;<fmt:formatDate value="${requestScope.j.journalTime }" pattern="yyyy-MM-dd HH:mm:ss" />	</div>	

<div class="divider"></div>
	
<div style="margin:10px;" >
	<my:scriptEscape value="${requestScope.j.journalContent}"/>
</div>

					
				


	
										
						
	