<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--消息提醒
--%>
<div  class="easyui-tabs"  fit="true" border="false" >
	<div   title="未读" 	style="padding:2px; " >
		<%@ include file="/WEB-INF/jsp/personalOffice/msgwarn/query_notread.jsp" %>
	</div>

	<div   title="已读"  href="msgwarn/load/isread.do?rel=${param.rel }_isread"	style="padding:2px; " >
	
	</div>

</div>