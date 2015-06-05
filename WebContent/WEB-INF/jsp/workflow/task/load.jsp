<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程 待办任务
--%>
<div  class="easyui-tabs"  fit="true" border="false" >
	<div   title="未收任务" 	>
		<%@ include file="/WEB-INF/jsp/workflow/task/nothave.jsp" %>
	</div>

	<div   title="任务列表"  href="task/todo/doing.do?rel=${param.rel }_tasklist"	>
	
	</div>

</div>