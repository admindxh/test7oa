<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--已发工作
--%>

<div  class="easyui-tabs"  fit="true" border="false" >
	<div   title="运行中" 	>
		 <%@ include file="/WEB-INF/jsp/workflow/process/mystart/running.jsp" %>
	</div>

	<div   title="历史记录"  href="work/mystart/his.do?rel=${param.rel }_his"	style="padding:2px; " >
	
	</div>

</div>
