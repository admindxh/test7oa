<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人文档管理
--%>
<div  class="easyui-tabs"  fit="true" border="false" >
	<div  title="列表形式" 	style="padding:2px; " >
			<%@ include file="/WEB-INF/jsp/files/personal/query.jsp" %>
	
	</div>
	
	
	<div   title="树状结构"  href="personalFiles/tree/load.do?rel=${param.rel}_tree&wdSuperId=${param.wdSuperId}"	style="padding:2px; " >
		
					
	</div>
	

</div>