<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--日程安排
--%>

<div  class="easyui-tabs"  fit="true" border="false" >
	<div   title="日程安排"  	style="padding:2px; " >
			<%@ include file="/WEB-INF/jsp/personalOffice/schedule/query_view.jsp" %>
	</div>

	<div  title="日程管理"  href="schedule/manage/load.do?rel=${param.rel }_gl"	style="padding:2px; " >
	
	</div>

</div>