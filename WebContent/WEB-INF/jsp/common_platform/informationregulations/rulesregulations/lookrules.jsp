<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：新闻管理--新闻内容极评论-详情
--%>
	
	
	
	<%--<div style="width: 900px;margin: 0 auto;" >		
	<table width="900px;" >	
		<tr>
			
			<td align="center"><c:out value="${requestScope.rules.rulestitle}" /></td>
		</tr>
		<tr>
			<td>发布人：<my:outTrueName id="${requestScope.rules.promulgator }"/>&nbsp;&nbsp;<fmt:formatDate value="${requestScope.rules.createdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<td style="margin:10px;background:#fff"><my:scriptEscape value="${rules.contents}"/></td>
		</tr>
	
	</table>
	
	
	</div>
--%>
<div class="pageContent" layoutH="2">	
	<div class="div_titlebold" ><c:out value="${requestScope.rules.rulestitle}" /></div>
	<div class="div_sendUser">发布人：<my:outTrueName id="${requestScope.rules.promulgator }"/>&nbsp;&nbsp;<fmt:formatDate value="${requestScope.rules.createdate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>	
	<div class="divider"></div>
	<div style="margin:10px;background:#fff"><my:scriptEscape value="${rules.contents}"/> </div>
	<div class="divider"></div>
	     
 </div>