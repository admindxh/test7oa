<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：人事管理-考勤管理-考勤设置-查找带回
--%>

<div class="easyui-layout"  fit="true">
    
  	
	<div  region="center" border="false">
		<div id="${param.rel }_box" class="easyui-panel" fit="true"  title="登记时间设置查询">
			
			<c:choose>
				<c:when test="${param.type==1 }">
					<%--单选--%>
					 <%@ include file="/WEB-INF/jsp/personnel/attendance/lookup_query.jsp"%>
				</c:when>
				<c:when test="${param.type==2 }">
					<%--多选--%>
					 <%@ include file="/WEB-INF/jsp/personnel/attendance/lookup_more_query.jsp"%>
				</c:when>
				 
				
			</c:choose>
		
		
		</div>
	</div>
   
</div>

 