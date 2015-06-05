<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--我的委托
--%>
<div  class="easyui-tabs"  fit="true" border="false" >
	<div   title="未完成" >
		 <%@ include file="/WEB-INF/jsp/workflow/task/entrust/query_run.jsp" %>
	</div>

	<div   title="已完成"  href="task/entrust/his/load.do?rel=${param.rel }_his" >
	
	</div>

</div>
