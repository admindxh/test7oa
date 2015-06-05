<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：公共平台--公告管理--系统公告查看
--%>


	
			<div class="div_titlebold" ><c:out value="${requestScope.xa.afficheTitle}" /></div>
			<div class="div_sendUser">发布人：<my:outTrueName id="${xa.userId }"/>&nbsp;&nbsp;<fmt:formatDate value="${requestScope.xa.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />	</div>	
			<div class="divider"></div>
				
			<div style="margin:10px;" >
				<my:scriptEscape value="${requestScope.xa.afficheContent}"/>
			</div>
		

										
						
	