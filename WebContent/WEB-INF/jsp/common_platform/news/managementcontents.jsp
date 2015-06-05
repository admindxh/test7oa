<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
	<%--
	模块：公各平台--新闻管理评论管理页面
    --%>
	<div id="${param.rel}_gl" class="easyui-panel" border="false">
			<%@ include file="/WEB-INF/jsp/common_platform/news/showmanagements.jsp" %> 
             
    </div>
	<div class="easyui-pagination" style="border:1px solid #ccc;"
        data-options="
           total: ${requestScope.total },showPageList:false,
           pageSize: ${requestScope.pageSize },
           pageNumber:${requestScope.pageNumber },
           onSelectPage: function(pageNumber, pageSize){
               $('#${param.rel }_gl').panel('refresh', 'news/mangementcontentload.do?id=${param.id}&rel=${param.rel}&page='+pageNumber+'&rows='+pageSize);
            }">

			
	 </div>