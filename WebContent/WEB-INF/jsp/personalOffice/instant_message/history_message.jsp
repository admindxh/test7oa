<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--即时消息--历史消息
--%>

<div id="${param.rel }_hmlist" class="easyui-panel" border="false">

	<%@ include file="/WEB-INF/jsp/personalOffice/instant_message/history_message_query.jsp" %>

</div>
<div class="easyui-pagination" style="border:1px solid #ccc;"
        data-options="
           total: ${requestScope.total },showPageList:false,
           pageSize: ${requestScope.pageSize },
           pageNumber:${requestScope.pageNumber },
           onSelectPage: function(pageNumber, pageSize){
               $('#${param.rel }_hmlist').panel('refresh', 'instantMessage/selectHistoryMessage/query.do?userId=${param.userId }&page='+pageNumber+'&rows='+pageSize);
            }">
</div>

